@echo off

set originTag=%1
set targetTag=%2
set sourceDir=%3

REM :: TARGET DIR NAME BASED ON FULL BUILD OR DELTA BUILD
set targetStructureDirName=%4

REM :: CURRENT DIRECTORY LOCATION OF DEPLOY.BAT
set currentDirLocation=%~dp0

REM :: DIRECTORY OF JAR FILES
set jarFilesLocation=%currentDirLocation%\tools\lib

REM :: COPYING CHECKEDOUT CODE TO DISTRIB DIRECTORY EXCEPT SPINNER FOLDER
echo.
call ant -buildfile %currentDirLocation%\build.xml copy
echo.

REM :: NORMALIZING SPINNER FILES & DELTA EXTRACTION OF CHECKEDOUT CODE TO DISTRIB/DELTA DIRECTORY
echo.
echo ------------ DELTA EXTRACTION : STARTED ------------
	java -jar %jarFilesLocation%/PWCDeltaExtractor.jar master %originTag% %targetTag% %sourceDir%
echo ------------ DELTA EXTRACTION : ENDS    ------------

REM :: TRANSFORMING TO TARGET STRUCTURE FROM DISTRIB/DELTA DIRECTORY
echo.
echo -------------------- TRANSFORMATION STARTS ------------------
	for /f "delims=" %%A in ( ' java -jar %jarFilesLocation%/PWCTransformation.jar %repoBranchName% %originTag% %targetTag% %sourceDir%\ ' ) do set retvalue=%%A
	set %retvalue%
echo -------------------- TRANSFORMATION ENDS   ------------------
echo.

REM :: BUILDING THE JAR FILE FOR ALL SOURCE_CODE MODULES
echo -------------------- JAR CREATION STARTS --------------------
echo.
	call ant -buildfile %currentDirLocation%/build.xml jar -DjarSrcCodeDir=%sourceDir%/distrib/%targetTag%/sourcecode/java -DjarDestDir=%targetStructureDirName%
echo.
echo -------------------- JAR CREATION ENDS   --------------------