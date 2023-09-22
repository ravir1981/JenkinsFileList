def workspace

node {
    stage('Checkout the source code') {
        checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ravir1981/SampleProject.git']])
        workspace=pwd()
    }
    stage('Code Review') {
        withSonarQubeEnv('sonar9') {
            sh 'mvn sonar:sonar'
        }
    }
    stage('Code Compile') {
        build job: 'Code Compile', parameters: [string(name: 'workspace', value: workspace)]
    }
    stage('Code Unit Test') {
        build job: 'Code Unit Test', parameters: [string(name: 'workspace', value: workspace)]
    }
    stage('Package the Code') {
        build job: 'Package the Code', parameters: [string(name: 'workspace', value: workspace)]
    }
    stage('Deploy the Code') {
        build 'Deploy the Code'
    }
}