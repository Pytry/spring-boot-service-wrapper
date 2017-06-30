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
echo Removing the service %WIJEE_APP_NAME% ...
set "EXECUTABLE=%WIJEE_APP_NAME%.exe"
if exist "%EXECUTABLE%" goto removeInstance
echo Error removing services. Could not find executable.
goto end
:removeInstance
"%EXECUTABLE%" //DS//%WIJEE_APP_NAME% ^
    --LogPath "logs"
if not errorlevel 1 goto removed
echo Error Removing Service. The uninstall executable failed.
goto end
:removed
echo The service '%SERVICE_NAME%' and all nodes have been removed
:end
echo DONE
