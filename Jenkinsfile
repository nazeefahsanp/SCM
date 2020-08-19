pipeline {
  agent any
  stages {
    stage('Code Checkout') {
      steps {
        bat ' %workingDir%/create_build.bat --branchName=%branchName% --targetTag=%targetTag% --originTag="%originTag%" --sourceDir=%workingDir%'
      }
    }

    stage('Static Code Analysis') {
      steps {
        bat(script: 'echo "Executing Quality gate""', returnStdout: true)
      }
    }

    stage('Delta Extraction') {
      steps {
        bat '%workingDir%/%targetTag%/buildscripts/build_engine/build.bat %targetTag% %workingDir% %originTag% '
      }
    }

    stage('Transformation') {
      steps {
        bat '%workingDir%/%targetTag%/buildscripts/build_engine/build_transformation.bat %branchName% %targetTag% %workingDir% %originTag%'
      }
    }

    stage('Compilation') {
      steps {
        bat '%workingDir%/%targetTag%/buildscripts/build_engine/build_compilation.bat %branchName% %targetTag% %workingDir% %originTag%'
      }
    }

    stage('PreConfig') {
      steps {
        bat '%workingDir%/deploy_build.bat execute.preconfig_scripts'
      }
    }

    stage('Platform Management') {
      steps {
        bat '%workingDir%/deploy_build.bat platform_management.configuration'
        bat '%workingDir%/deploy_build.bat  platform_management.PnO'
      }
    }

    stage('Unified Typing') {
      steps {
        bat '%workingDir%/deploy_build.bat unified_typing'
      }
    }

    stage('Execute Spinner') {
      steps {
        bat '@echo off echo ******************************************************* echo ############### Spinner Execution Starts ################ echo. echo Spinner Execution completed ! echo. echo ############### Spinner Execution Ends ################'
      }
    }

    stage('PostConfig') {
      steps {
        bat '%workingDir%/deploy_build.bat execute.postconfig_scripts'
      }
    }

    stage('Register Custo') {
      steps {
        bat '%workingDir%/deploy_build.bat register.customization'
      }
    }

    stage('Generate WAR') {
      steps {
        bat '%workingDir%/deploy_build.bat custom_war'
      }
    }

    stage('Compile JPOs') {
      steps {
        bat '%workingDir%/deploy_build.bat  compile.JPOs'
      }
    }

    stage('Indexing') {
      steps {
        bat '%workingDir%/deploy_build.bat import.3DSpaceIndex'
      }
    }

    stage('Distribute WAR') {
      steps {
        bat '%workingDir%/deploy_build.bat  create.output_dir'
        bat '%workingDir%/deploy_build.bat create.output_package'
      }
    }

  }
  environment {
    branchName = 'master'
    targetTag = '20200630120007'
    originTag = '20200630120004'
    workingDir = 'C:\\Apps\\jenkins\\workspace\\SCMBuild'
  }
}