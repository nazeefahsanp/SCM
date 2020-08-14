pipeline {
  agent any
  stages {
    stage('') {
      steps {
        bat ' %sourceDir%/create_build.bat --branchName=%branchName% --targetTag=%targetTag% --originTag="%originTag%" --sourceDir=%sourceDir%'
      }
    }

  }
}