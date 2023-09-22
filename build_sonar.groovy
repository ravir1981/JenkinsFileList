def workspace

node('slave4') {
    stage('Checkout the source code') {
        checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ravir1981/java-springboot-sonarqube.git']])
        workspace=pwd()
        echo "Workspace - ${workspace}"
    }
    stage('Print Environment Variables') {
        echo sh(script: 'env|sort', returnStdout: true)
    }
    stage('Build the Project') {
        sh 'mvn clean package'
    }
    stage('Code Review') {
        withSonarQubeEnv('sonar9') {
            sh 'mvn sonar:sonar'
        }
        timeout(time: 1, unit: 'HOURS') {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
            } else {
                echo "Sonar Quality Gateway succeeded"
            }
	    }
    }
}