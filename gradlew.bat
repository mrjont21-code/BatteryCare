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

@if "%DEBUG%"==" echo off
@REM enable echoing by setting GRADLE_DEBUG=1 before running your script.
if "%GRADLE_DEBUG%"=="1" goto debugMode

setlocal
setlocal enableextensions
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

for /f "tokens=1,*" %%a in ('findstr /b /v "^@if" "%FILE%"') do (
    if "%%a"=="@rem" (
        REM skip
    ) else if "%%a"=="@setlocal" (
        setlocal enableextensions
    ) else if "%%a"=="@echo" (
        @echo off
    ) else (
        echo %%a %%b
    )
)

if not "%JAVA_HOME%" == "" (
    set "JAVA_EXE=%JAVA_HOME%/bin/java.exe"
    if not exist "!JAVA_EXE!" (
        echo.
        echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
        echo.
        echo Please set the JAVA_HOME variable in your environment to match the
        echo location of your Java installation.
        echo.
        goto fail
    )
) else (
    set "JAVA_EXE=java.exe"
    %"JAVA_EXE%" -version >nul 2>&1
    if errorlevel 1 (
        echo.
        echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
        echo.
        echo Please set the JAVA_HOME variable in your environment to match the
        echo location of your Java installation.
        echo.
        goto fail
    )
)

setlocal enabledelayedexpansion

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

for /f "usebackq delims=" %%a in ("%APP_HOME%\gradle.properties") do (
    if "%%a" neq "" (
        set "PROP_LINE=%%a"
        setlocal enabledelayedexpansion
        for /f "tokens=1* delims==" %%b in ("!PROP_LINE!") do (
            if "%%b"=="org.gradle.jvmargs" (
                set "DEFAULT_JVM_OPTS=!DEFAULT_JVM_OPTS! %%c"
            )
        )
        endlocal
    )
)

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:fail
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" (
    endlocal
)
exit /b %ERRORLEVEL%

:omega
