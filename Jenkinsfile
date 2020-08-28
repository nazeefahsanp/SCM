pipeline {
  agent any
  stages {
    stage('TestCompleteTest') {
      steps {
        testcompletetest(suite: 'C:\\Users\\nazeefahsanp\\Documents\\TestComplete 14 Projects\\3DEX_2020x_Sprint_test2\\3DEX_2020x_Sprint_test2.pjs', routine: '01-SCM_Command_test', useActiveSession: true, unit: '01-SCM_Command_test')
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