@ECHO OFF

REM Create bin and db directory if it does not exist
if not exist ..\bin mkdir ..\bin
if not exist ..\ui-test\db mkdir ..\ui-test\db

REM Create temporary test database from source database
XCOPY /s /y ..\db ..\ui-test\db

REM Compile all source files into bin folder
javac -cp ..\lib\*;..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\mystars\MyStars.java
IF ERRORLEVEL 1 (
    echo ********** Compilation Error **********
    exit /b 1
)

REM Run the program, feed input and write output
java -classpath ..\lib\*;..\bin mystars.MyStars < input.txt > output.txt

REM Compare output with expected output
FC output.txt expected.txt