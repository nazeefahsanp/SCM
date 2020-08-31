pipeline {
  agent any
  stages {
    stage('Test Complete') {
      steps {
        node(label: 'TestComplete-Slave') {
          testcompletetest(suite: 'C:\\Apps\\jenkins\\workspace\\Sprint_Pipeline1_TestJenkinsRepo\\TestComplete 14 Projects\\3DEX_2020x_Sprint_test2\\3DEX_2020x_Sprint_test2.pjs', useActiveSession: true)
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