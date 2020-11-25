# MySTARS

This is the group assignment for CZ2002, by Lab Group SE1 Group 1. Instructions on how to use it are given below.

## Setting Up Environment in Intellij

Prerequisites: JDK 11 (Could work on other versions, but mostly tested on JDK 11), update Intellij to the latest
version.

1. Import the project into Intellij as follows:
    1. Open IntelliJ (if you are not in the welcome screen, click File → Close Project to close the existing project
       dialog first).
    1. Click `Open`.
    1. Select the project directory `SE3-grp1_MySTARS`, and click `OK`.
    1. Accept the defaults if there are any further prompts.
1. Set up the correct JDK version, as follows:
    1. Click `File` > `Project Structure` > `Project`.
    1. Under Project SDK, select the correct JDK version.
    1. Click `OK`.
1. Add library to project
    1. Click `File` > `Project Structure` > `Modules` and then `Dependencies`.
    1. Select `+` icon and then `JARs or Directories...`.
    1. Locate the `lib` folder and select it.
    1. Click `OK`.
1. After the above steps are completed, locate the `src/main/java/mystars/MyStars.java` file, right-click it, and
   choose `Run MyStars.main()`. If the setup is correct, you should see something like this:
   ```
   ------------------------------------------------------------
   
    _______         _______________________ _______ _______ 
   (       )\     /(  ____ \__   __(  ___  |  ____ |  ____ \
   | () () ( \   / ) (    \/  ) (  | (   ) | (    )| (    \/
   | || || |\ (_) /| (_____   | |  | (___) | (____)| (_____ 
   | |(_)| | \   / (_____  )  | |  |  ___  |     __|_____  )
   | |   | |  ) (        ) |  | |  | (   ) | (\ (        ) |
   | )   ( |  | |  /\____) |  | |  | )   ( | ) \ \_/\____) |
   |/     \|  \_/  \_______)  )_(  |/     \|/   \__|_______)
                                                         
   Welcome!
   Enter Username:
   ```

## Running Program in Terminal

* In Windows
    1. Locate the _project root directory,_ whose path ends with `MySTARS`.
    1. Type `javac -cp .\lib\*;.\src\main\java -Xlint:none -d .\bin .\src\main\java\mystars\MyStars.java` and press
       enter to compile the code.
    1. Now, type `java -classpath .\lib\*;.\bin mystars.MyStars` and press enter to run the program.
* In UNIX
    1. Locate the _project root directory,_ whose path ends with `MySTARS`.
    1. Type `javac -cp ./lib/*:./src/main/java -Xlint:none -d ./bin ./src/main/java/mystars/MyStars.java` and press
       enter to compile the code.
    1. Now, type `java -classpath ./lib/*:./bin mystars.MyStars` and press enter to run the program.

If the setup is correct, you should see something like this:

   ```
   ------------------------------------------------------------
   
    _______         _______________________ _______ _______ 
   (       )\     /(  ____ \__   __(  ___  |  ____ |  ____ \
   | () () ( \   / ) (    \/  ) (  | (   ) | (    )| (    \/
   | || || |\ (_) /| (_____   | |  | (___) | (____)| (_____ 
   | |(_)| | \   / (_____  )  | |  |  ___  |     __|_____  )
   | |   | |  ) (        ) |  | |  | (   ) | (\ (        ) |
   | )   ( |  | |  /\____) |  | |  | )   ( | ) \ \_/\____) |
   |/     \|  \_/  \_______)  )_(  |/     \|/   \__|_______)
                                                         
   Welcome!
   Enter Username:
   ```

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests, navigate to the `ui-test` and run the `test(.bat/.sh)` script.

### JUnit tests

* JUnit tests are included and can be located at `src/test/java/mystars`.

### User accounts and passwords for testing

* The database's password is hashed.
* As such, the login username and un-hashed password for both students and admins can be found at `accounts.txt`.
  
### Other things to take note
* Please use command line instead of IDE to run the program, or else the password will not be masked.
* Alternatively, run the jar file on the other folder (with an extra `_jar` in the folder name).
    * This will ensure the password is masked.
    * A `start.bat` and `start.sh` file has been included for starting the jar file.
    * The database for the jar will be separate from the source code i.e. changing one will not affect the other.