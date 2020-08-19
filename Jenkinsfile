pipeline {
  agent any
  stages {
    stage('Code Checkout') {
      steps {
        bat ' %workingDir%/create_build.bat --branchName=%branchName% --targetTag=%targetTag% --originTag="%originTag%" --sourceDir=%workingDir%'
      }
    }

    stage('Code Quality Gate') {
      steps {
        bat(script: 'echo "Executing Quality gate"', label: 'Static Code Analysis')
      }
    }

    stage('Packaging') {
      steps {
        bat(script: '%workingDir%/%targetTag%/buildscripts/build_engine/build.bat %targetTag% %workingDir% %originTag% ', label: 'Delta Extraction')
        bat(script: '%workingDir%/%targetTag%/buildscripts/build_engine/build_transformation.bat %branchName% %targetTag% %workingDir% %originTag%', label: 'Transformation')
        bat(script: '%workingDir%/%targetTag%/buildscripts/build_engine/build_compilation.bat %branchName% %targetTag% %workingDir% %originTag%', label: 'Compilation')
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

    stage('Schema Update') {
      steps {
        bat(script: '%workingDir%/deploy_build.bat unified_typing', label: 'Unified Typing')
        bat(script: 'echo "Execute Spinner"', label: 'Execute Spinner')
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

    stage('') {
      parallel {
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

    stage('Functionality Quality Gate') {
      parallel {
        stage('Functionality Quality Gate') {
          steps {
            echo 'Regression Tests'
          }
        }

        stage('') {
          steps {
            echo 'Functional Tests'
          }
        }

      }
    }

  }
  environment {
    branchName = 'master'
    targetTag = '20200630120009'
    originTag = '20200630120004'
    workingDir = 'C:\\Apps\\jenkins\\workspace\\SCM_BlueOcean'
  }
}