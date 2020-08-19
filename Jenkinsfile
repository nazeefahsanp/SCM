pipeline {
  agent any
  stages {
    stage('Code Checkout') {
      steps {
        bat ' %sourceDir%/create_build.bat --branchName=%branchName% --targetTag=%targetTag% --originTag="%originTag%" --sourceDir=%sourceDir%'
      }
    }

    stage('Static Code Analysis') {
      steps {
        bat(script: 'echo "Executing Quality gate""', returnStdout: true)
      }
    }

    stage('Delta Extraction') {
      steps {
        bat '%sourceDir%/%targetTag%/buildscripts/build_engine/build.bat %targetTag% %sourceDir% %originTag% '
      }
    }

    stage('Transformation') {
      steps {
        bat '%sourceDir%/%targetTag%/buildscripts/build_engine/build_transformation.bat %branchName% %targetTag% %sourceDir% %originTag%'
      }
    }

    stage('Compilation') {
      steps {
        bat '%sourceDir%/%targetTag%/buildscripts/build_engine/build_compilation.bat %branchName% %targetTag% %sourceDir% %originTag%'
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