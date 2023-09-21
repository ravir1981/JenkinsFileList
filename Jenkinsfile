def workspace;

node('slave4') {
    stage('Checkout the source code') {
        echo "Hello Ravindran"
        checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ravir1981/spring-boot-app.git']])
        workspace=pwd()
    }
    stage('Print Environment Variables') {
        echo sh(script: 'env|sort', returnStdout: true)
    }
    stage('Build the Code') {
        echo "Build the code"
    }
    stage('Unit Testing') {
        echo "Unit testing of the code"
    }
}
