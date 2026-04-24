@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

@rem Java not found, try to download and install Adoptium JDK 17
echo Java not found. Attempting to download Adoptium JDK 17...

@rem Create temp directory for JDK download
set JDK_TEMP=%TEMP%\jdk17
if not exist "%JDK_TEMP%" mkdir "%JDK_TEMP%"

@rem Download JDK 17 MSI installer
set JDK_MSI_URL=https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.9%2B9/OpenJDK17U-jdk_x64_windows_hotspot_17.0.9_9.msi
set JDK_MSI_FILE=%JDK_TEMP%\jdk17.msi

echo Downloading JDK 17 from %JDK_MSI_URL%...
powershell -Command "Invoke-WebRequest -Uri '%JDK_MSI_URL%' -OutFile '%JDK_MSI_FILE%' -UseBasicParsing"
if %ERRORLEVEL% neq 0 (
    echo Failed to download JDK 17. Please install Java manually.
    goto fail
)

@rem Install JDK silently
echo Installing JDK 17...
msiexec /i "%JDK_MSI_FILE%" /quiet /norestart
if %ERRORLEVEL% neq 0 (
    echo Failed to install JDK 17. Please install Java manually.
    goto fail
)

@rem Find the installed JDK path
set JDK_INSTALL_DIR=C:\Program Files\Eclipse Adoptium\jdk-17.0.9.9-hotspot
if exist "%JDK_INSTALL_DIR%" (
    set JAVA_HOME=%JDK_INSTALL_DIR%
    set JAVA_EXE=%JAVA_HOME%\bin\java.exe
    echo JDK 17 installed successfully at %JAVA_HOME%
    goto execute
)

@rem Try to find JDK in Program Files
for /d %%i in ("C:\Program Files\Eclipse Adoptium\*") do (
    if exist "%%i\bin\java.exe" (
        set JAVA_HOME=%%i
        set JAVA_EXE=%%i\bin\java.exe
        echo JDK found at %%i
        goto execute
    )
)

@rem If still not found, try to find in Program Files (x86)
for /d %%i in ("C:\Program Files (x86)\Eclipse Adoptium\*") do (
    if exist "%%i\bin\java.exe" (
        set JAVA_HOME=%%i
        set JAVA_EXE=%%i\bin\java.exe
        echo JDK found at %%i
        goto execute
    )
)

echo Could not find installed JDK. Please install Java manually.
goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar


@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:end
@rem End local scope for the variables with windows NT shell
if %OS%==Windows_NT endlocal

:omega
