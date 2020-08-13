pipeline {
  agent any
  stages {
    stage('Source_Code_Checkout') {
      steps {
        bat ' %sourceDir%/create_build.bat --branchName=%branchName% --targetTag=%targetTag% --originTag=%originTag% --sourceDir=%sourceDir%'
      }
    }

  }
  environment {
    branchName = 'master'
    targetTag = '202006161400'
    originTag = '202006140230'
    sourceDir = 'C:\\Users\\nazeefahsanp\\Documents\\PWC_SCM'
  }
}