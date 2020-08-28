pipeline {
  agent any
  stages {
    stage('TestCompleteTest') {
      steps {
        testcompletetest(suite: 'C:\\Users\\nazeefahsanp\\Documents\\TestComplete 14 Projects\\3DEx_2020x_Sprint')
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