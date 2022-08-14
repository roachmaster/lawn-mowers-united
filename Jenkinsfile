node {
    stage("Clone"){
        git branch: 'main', url: 'git@github.com:roachmaster/lawn-mowers-united.git'
    }

    stage("Build"){
        sh "env"
        sh "mvn clean install"
        cucumber buildStatus: 'null', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/Cucumber-*.json', jsonReportDirectory: 'build/reports/tests/acceptanceTest', pendingStepsNumber: -1, reportTitle: 'WeddingRsvpRegistry', skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
    }

    stage("Docker Build"){
        withCredentials([usernamePassword(credentialsId: '87e61f11-079d-4052-b083-ea5859f0f85b', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
            def dockerVersion = '0.0.1-SNAPSHOT'
            dockerBuild(dockerName:"${DOCKER_USERNAME}/lawn-mowers-united:${dockerVersion}",
                        dockerOpt:"--build-arg JAR_FILE=lawn-mowers-united-service/target/lawn-mowers-united-service-${dockerVersion}.jar",
                        DOCKER_PASSWORD: "${DOCKER_PASSWORD}",
                        DOCKER_USERNAME:"${DOCKER_USERNAME}")
        }
    }

    stage("K3S Deployment"){
        withCredentials([usernamePassword(credentialsId: '8047ae57-cfa7-4ee1-86aa-be906b124593', passwordVariable: 'credPw', usernameVariable: 'credName')]) {
            k3sSecret name:"mysql-pass", credPw: "${credPw}"
        }
        k3sDeployment name: "lawn-mowers-united"
        k3sService name: "lawn-mowers-united"
        //waitForPodToBeReady name:"lawn-mowers-united", maxNumOfAttempts: 30
    }
}
