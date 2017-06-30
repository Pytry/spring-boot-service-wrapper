@echo off
rem This file is based off of scripts written for Apache Tomcat and
rem copyrighted by the Apache Software Foundation.
rem
rem     http://tomcat.apache.org/
rem     http://www.apache.org/licenses/LICENSE-2.0
rem
rem This will uninstall the Spring Boot Application that was packaged
rem by the WIJEE plugin.

setlocal

set "WIJEE_APP_NAME=${WIJEE_APP_NAME}"
set "EXECUTABLE=bin\%WIJEE_APP_NAME%.exe"
set "SERVICE_NAME=%WIJEE_APP_NAME%"
set "CLASSPATH=${WIJEE_CLASSPATH}"
set "WIJEE_MAIN_CLASS=${WIJEE_MAIN_CLASS}"
set "DISPLAY_NAME=%SERVICE_NAME%"
set "SELF=%~dp0install.cmd"
:checkWijeeAppName
if not "%WIJEE_APP_NAME%" == "" goto checkExecutable
echo The WIJEE_APP_NAME variable was not defined properly
echo The WIJEE_APP_NAME variable is needed to run this program
goto end
:checkExecutable
echo "Installing %WIJEE_APP_NAME%"
if exist "%EXECUTABLE%" goto checkForExistingService
echo The %WIJEE_APP_NAME%.exe was not found...
echo The %WIJEE_APP_NAME%.exe executable is needed to run this program
goto end
:checkForExistingService
sc query ftpsvc > nul
if errorlevel 1060 goto checkWijeeMainClass
echo %WIJEE_APP_NAME% is already installed.
echo Exiting Installation Script
goto end
:checkWijeeMainClass
if not "%WIJEE_MAIN_CLASS%" == "" goto checkJavaHome
rem CD to the upper dir
echo ERROR
echo "  The WIJEE_MAIN_CLASS variable is not defined correctly."
echo "  This variable is needed to run this program."
goto end
:checkJavaHome
if not "%JAVA_HOME%" == "" goto gotJdkHome
if not "%JRE_HOME%" == "" goto gotJreHome
echo Neither the JAVA_HOME nor the JRE_HOME environment variable is defined
echo Service will try to guess them from the registry.
goto okJavaHome
:gotJreHome
if not exist "%JRE_HOME%\bin\java.exe" goto noJavaHome
if not exist "%JRE_HOME%\bin\javaw.exe" goto noJavaHome
goto checkUser
:gotJdkHome
if not exist "%JAVA_HOME%\jre\bin\java.exe" goto noJavaHome
if not exist "%JAVA_HOME%\jre\bin\javaw.exe" goto noJavaHome
if not exist "%JAVA_HOME%\bin\javac.exe" goto noJavaHome
if not "%JRE_HOME%" == "" goto okJavaHome
set "JRE_HOME=%JAVA_HOME%\jre"
goto checkUser
:noJavaHome
echo The JAVA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
echo NB: JAVA_HOME should point to a JDK not a JRE
goto end
:checkUser
if "x%1x" == "x/userx" goto runAsUser
if "x%1x" == "x--userx" goto runAsUser
shift
if "x%1x" == "xx" goto doInstall
goto checkUser
:runAsUser
shift
if "x%1x" == "xx" goto displayUsage
set SERVICE_USER=%1
shift
runas /env /savecred /user:%SERVICE_USER% "%COMSPEC% /K \"%SELF%\" install %SERVICE_NAME%"
goto end
:doInstall
rem Install the service
echo Installing the service '%SERVICE_NAME%' ...
echo Using JAVA_HOME:        "%JAVA_HOME%"
echo Using JRE_HOME:         "%JRE_HOME%"
rem Try to use the server jvm
set "JVM=%JRE_HOME%\bin\server\jvm.dll"
if exist "%JVM%" goto foundJvm
rem Try to use the client jvm
set "JVM=%JRE_HOME%\bin\client\jvm.dll"
if exist "%JVM%" goto foundJvm
echo Warning: Neither 'server' nor 'client' jvm.dll was found at JRE_HOME.
set JVM=auto
:foundJvm
echo Using JVM:              "%JVM%"
if "%SERVICE_STARTUP_MODE%" == "" set SERVICE_STARTUP_MODE=manual
if "%JvmMs%" == "" set JvmMs=128
if "%JvmMx%" == "" set JvmMx=512

"%EXECUTABLE%" //IS//%SERVICE_NAME% ^
    --Description "%WIJEE_APP_NAME% Wijee Service Wrapper " ^
    --DisplayName "%DISPLAY_NAME%" ^
    --Install "%EXECUTABLE%" ^
    --LogPath "logs" ^
    --StdOutput auto ^
    --StdError auto ^
    --Classpath "%CLASSPATH%" ^
    --Jvm "%JVM%" ^
    --StartMode jvm ^
    --StopMode jvm ^
    --StartPath ".\" ^
    --StopPath ".\" ^
    --StartClass "%WIJEE_MAIN_CLASS%" ^
    --StopClass "%WIJEE_MAIN_CLASS%" ^
    --StartParams start ^
    --StopParams stop ^
    --JvmOptions "-Djava.io.tmpdir=temp;%JvmArgs%" ^
    --Startup "%SERVICE_STARTUP_MODE%" ^
    --JvmMs "%JvmMs%" ^
    --JvmMx "%JvmMx%"
if not errorlevel 1 goto installed
echo Failed installing '%SERVICE_NAME%' service
goto end
:installed
echo The service '%SERVICE_NAME%' has been installed.

:end
echo DONE
