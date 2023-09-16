currentBuild.displayName = "main"
base_git_url = "https://github.com/aL686/ui-tests.git"


node {
    stage("Checkout Branch") {
        getProject("$base_git_url", "main")
    }
    try {
        parallel runTestWithTag()
    } finally {
        stage("Allure") {
            generateAllure()
        }
    }
}

def runTestWithTag() {
    try {
        labelledShell(label: "Run ", script: "chmod +x gradlew \n./gradlew -x test")
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
