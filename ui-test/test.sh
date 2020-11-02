# Create bin directory if it does not exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# Create temporary test database from source database
cp -R "../db" "../ui-test/db"

# Compile all source files into bin folder
if ! javac -cp "../lib/*;../src/main/java" -Xlint:none -d ../bin ../src/main/java/mystars/MyStars.java
then
    echo "********** Compilation Error **********"
    exit 1
fi

# Run the program, feed input and write output
java -classpath ../bin mystars.MyStars < input.txt > output.txt

# Compare output with expected output (ignoring trailing line separators)
if diff --strip-trailing-cr output.txt expected.txt
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi