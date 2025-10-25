@echo off
echo Starting CODEXA Backend...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    pause
    exit /b 1
)

REM Check if MySQL is running (optional)
echo Checking MySQL connection...
mysql -u appuser -papp_password -e "SELECT 1;" jewellery >nul 2>&1
if %errorlevel% neq 0 (
    echo WARNING: MySQL connection failed. Make sure MySQL is running and database 'jewellery' exists.
    echo You can continue anyway, but database operations may fail.
    echo.
)

REM Start the backend
echo Starting Spring Boot application...
echo Backend will be available at: http://localhost:8088
echo.
echo Press Ctrl+C to stop the server
echo.

java -jar target/CODEXA-backend-0.0.1-SNAPSHOT.jar

pause

