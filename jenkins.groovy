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
            stage("Run tests") {
                parallel runTestWithTag()
            }
        } finally {
            stage("Generate report") {
                generateAllure()
            }
        }
    }
}


def runTestWithTag() {
    labelledShell(label: "Run main", script: "chmod +x gradlew \n./gradlew -x clean test")
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


