// Powered by Infostretch 

timestamps {

node () {

	stage ('Source Code Check-out - Build') {
 			// Batch build step
bat """ 
%sourceDir%/create_build.bat --branchName=%branchName% --targetTag=%targetTag% --originTag="%originTag%" --sourceDir=%sourceDir% 
 """ 
	}
	stage ('Source Code Analysis - Build') {
 	
withEnv(["JAVA_HOME=${ tool '"+JDK+"' }", "PATH=${env.JAVA_HOME}/bin"]) { 

// Unable to convert a build step referring to "hudson.plugins.sonar.SonarRunnerBuilder". Please verify and convert manually if required. 
	}
}
	stage ('Delta Extraction and Spinner Normalization - Build') {
 	
// Unable to convert a build step referring to "hudson.tasks.AntWrapper". Please verify and convert manually if required.		// Batch build step
bat """ 
%sourceDir%/%targetTag%/buildscripts/build_engine/build.bat %targetTag% %sourceDir% %originTag% 
 """ 
	}
	stage ('Build Transformation - Build') {
 			// Batch build step
bat """ 
%sourceDir%/%targetTag%/buildscripts/build_engine/build_transformation.bat %branchName% %targetTag% %sourceDir% %originTag% 
 """		// Batch build step
bat """ 
echo dependency outside 2: %dependencies% 
 """ 
	}
	stage ('Build Compilation - Build') {
 	
// Unable to convert a build step referring to "hudson.tasks.AntWrapper". Please verify and convert manually if required.		// Batch build step
bat """ 
%sourceDir%/%targetTag%/buildscripts/build_engine/build_compilation.bat %branchName% %targetTag% %sourceDir% %originTag% 
 """ 
	}
}
}