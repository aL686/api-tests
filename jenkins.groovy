def branch_cutted = "main"
base_git_url = "https://gitlab.com/epickonfetka/cicd-threadqa.git"


node {
    withEnv(["branch=${branch_cutted}"]) {
        stage("Checkout Branch") {
            if (!"$branch_cutted".contains("main")) {
                try {
                    getProject("$base_git_url", "$branch_cutted")
                } catch (err) {
                    echo "Failed get branch $branch_cutted"
                    throw ("${err}")
                }
            } else {
                echo "Current branch is main"
            }
        }

        try {
            stage("Checkout Branch") {
                parallel runTestWithTag()
            }
        } finally {
            stage("Allure") {
                generateAllure()
            }
        }
    }
}


def runTestWithTag() {
    try {
        labelledShell(label: "Run ", script: "chmod +x gradlew \n./gradlew -x clean test")
    } finally {
        echo "some failed tests"
    }
}

def getProject(String repo, String branch) {
    cleanWs()
    checkout scm: [
            $class           : 'GitSCM', branches: [[name: branch]],
            userRemoteConfigs: [[
                                        url: repo
                                ]]
    ]
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'build/allure-results']]
    ])
}


