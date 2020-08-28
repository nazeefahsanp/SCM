pipeline {
  agent any
  stages {
    stage('TestCompleteTest') {
      steps {
        testcompletetest()
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