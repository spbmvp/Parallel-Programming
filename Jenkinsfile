pipeline {
  agent any
  stages {
    stage('stage 1') {
      steps {
        parallel(
          "paral 1": {
            sh 'ls -la'
          },
          "paral 2": {
            echo 'Tets'
            
          }
        )
      }
    }
    stage('stage2') {
      steps {
        echo 'tets'
        retry(count: 3) {
          echo '3'
        }
        
      }
    }
  }
}
