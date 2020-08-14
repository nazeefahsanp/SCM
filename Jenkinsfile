pipeline {
  agent any
  stages {
    stage('error') {
      steps {
        bat ' %sourceDir%/create_build.bat --branchName=%branchName% --targetTag=%targetTag% --originTag="%originTag%" --sourceDir=%sourceDir%'
      }
    }

  }
  environment {
    branchName = 'master'
    targetTag = '20200630120002'
    originTag = '20200630120002'
    sourceDir = 'C:\\Apps\\jenkins\\workspace\\SCMBuild'
  }
}