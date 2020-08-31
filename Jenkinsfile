pipeline {
  agent any
  stages {
    stage('Test Complete') {
      steps {
        node(label: 'TestComplete-Slave') {
          testcompletetest(suite: '\\TestComplete 14 Projects\\TestComplete_SCM\\TestComplete_SCM.pjs', useActiveSession: true)
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