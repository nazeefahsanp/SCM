
@echo off
REM setlocal enabledelayedexpansion

echo **************************************************************
echo ######################## deploy.bat ##########################
echo[

set repoBranchName=%1
set deploymentType=%2
set targetTag=%3
set originTag=%4
set currentDir=%cd%

REM Calling ant deploy.xml
call ant -buildfile %currentDir%/deploy_engine/deploy_windows.xml -DrepoBranchName="%repoBranchName%" -Ddeployment.type="%deploymentType%" -Dinput.origin.tag="%originTag%" -Dtarget.tag="%targetTag%"
echo **************************************************************