@ECHO OFF

REM Create bin directory if it does not exist
if not exist ..\bin mkdir ..\bin

REM Compile all source files into bin folder
javac -cp ..\lib\*;..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\mystars\MyStars.java
IF ERRORLEVEL 1 (
    echo ********** Compilation Error **********
    exit /b 1
)

REM Run the program, feed input and write output
java -classpath ..\bin mystars.MyStars < input.txt > output.txt

REM Compare output with expected output
FC output.txt expected.txt