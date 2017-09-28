pipeline {
  agent any
  stages {
    stage('stage 1') {
      steps {
        parallel(
          "stage 1": {
            sh '''ls -la
'''
            
          },
          "": {
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