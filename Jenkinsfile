pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './gradlew clean build'
      }
    }

    stage('Test') {
      steps {
        sh './gradlew test' 
      }
    }

    stage('Deploy') {
      steps {
        sleep 5
        echo 'Deploy'
      }
    }

  }
}
