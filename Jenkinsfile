pipeline {
  agent any
  stages {
    stage('Source_Code_Checkout') {
      steps {
        bat ' %sourceDir%/create_build.bat --branchName=%branchName% --targetTag=%targetTag% --originTag="%originTag%" --sourceDir=%sourceDir%'
      }
    }

    stage('Source_Code_Analysis') {
      steps {
        withSonarQubeEnv(installationName: 'SCM SonarQube Scanner', credentialsId: 'SCM')
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