@echo off
REM Usage: test_sprt.bat <base_version> <changed_version> [preset_name]
REM Example: test_sprt.bat 1.0 1.1 default

set INPUT_ARG1=%1
set INPUT_ARG2=%2
set PRESET=%3
if "%PRESET%"=="" set PRESET=default

set WORK_DIR=%cd%
set ENGINE1=%WORK_DIR%\engines\Hackathon-ChessEngine-%INPUT_ARG1%.jar
set ENGINE2=%WORK_DIR%\engines\Hackathon-ChessEngine-%INPUT_ARG2%.jar
set PGNOUT=%WORK_DIR%\games.pgn
set CONFIG_FILE=%WORK_DIR%\sprt_presets.ini

if exist "%ENGINE1%" (
    echo ✓ Engine 1 found
) else (
    echo ✗ Engine 1 missing: %ENGINE1%
    exit /b 1
)

if exist "%ENGINE2%" (
    echo ✓ Engine 2 found
) else (
    echo ✗ Engine 2 missing: %ENGINE2%
    exit /b 1
)

if exist "%CONFIG_FILE%" (
    echo ✓ Config file found
) else (
    echo ✗ Config file missing: %CONFIG_FILE%
    exit /b 1
)

echo. > "%PGNOUT%"
echo ✓ Cleared %PGNOUT%

REM Function to read values from the .ini file
for /f "tokens=1,2 delims==" %%A in ('findstr /r "^\[%PRESET%\]" "%CONFIG_FILE%"') do (
    set "found=1"
)
if not defined found (
    echo ✗ Failed to read preset: %PRESET%
    exit /b 1
)

for /f "tokens=1,2 delims==" %%A in ('findstr /r "^\[%PRESET%\]" "%CONFIG_FILE%"') do (
    set %%A=%%B
)

echo Running SPRT with preset: %PRESET%
