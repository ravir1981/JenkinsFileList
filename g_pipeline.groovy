@Library("my-shared-library") _

pipeline {
    agent {
        label 'slave4'
    }
    stages {
        stage('Hello') {
            steps {
                helloWorld()
            }
        }
    }
}

/*
https://github.com/ravir1981/Jenkins-Zero-To-Hero.git
Configure this in System -> Global Pipeline Libraries -> my-shared-library
*/