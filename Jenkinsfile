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

    stage('PreConfig') {
      steps {
        bat '%sourceDir%/deploy_build.bat execute.preconfig_scripts'
      }
    }

    stage('Platform Management') {
      steps {
        bat '%sourceDir%/deploy_build.bat platform_management.configuration'
        bat '%sourceDir%/deploy_build.bat  platform_management.PnO'
      }
    }

    stage('Unified Typing') {
      steps {
        bat '%sourceDir%/deploy_build.bat unified_typing'
      }
    }

    stage('Execute Spinner') {
      steps {
        bat '@echo off echo ******************************************************* echo ############### Spinner Execution Starts ################ echo. echo Spinner Execution completed ! echo. echo ############### Spinner Execution Ends ################'
      }
    }

    stage('PostConfig') {
      steps {
        bat '%sourceDir%/deploy_build.bat execute.postconfig_scripts'
      }
    }

    stage('Register Custo') {
      steps {
        bat '%sourceDir%/deploy_build.bat register.customization'
      }
    }

    stage('Generate WAR') {
      steps {
        bat '%sourceDir%/deploy_build.bat custom_war'
      }
    }

    stage('Compile JPOs') {
      steps {
        bat '%sourceDir%/deploy_build.bat  compile.JPOs'
      }
    }

    stage('Indexing') {
      steps {
        bat '%sourceDir%/deploy_build.bat import.3DSpaceIndex'
      }
    }

    stage('Distribute WAR') {
      steps {
        bat '%sourceDir%/deploy_build.bat  create.output_dir'
        bat '%sourceDir%/deploy_build.bat create.output_package'
      }
    }

  }
  environment {
    branchName = 'master'
    targetTag = '20200630120006'
    originTag = '20200630120004'
    WorkingDir = 'C:\\Apps\\jenkins\\workspace\\SCMBuild'
  }
}