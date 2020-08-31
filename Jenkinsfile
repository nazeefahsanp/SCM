pipeline {
  agent any
  stages {
    stage('TestCompleteTest') {
      steps {
        testcompletetest(suite: '\\TestComplete 14 Projects\\TestComplete_SCM\\TestComplete_SCM.pjs', useActiveSession: true, unit: '01-SCM_Command_test')
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