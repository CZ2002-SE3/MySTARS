# MySTARS

This is the group assignment for CZ2002. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11 (use the exact version), update Intellij to the most recent version.

1. **Configure Intellij for JDK 11**, as described [here](https://se-education.org/guides/tutorials/intellijJdk.html).
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Add library to project
   1. Click `Project Structure` > `Modules` and then `Dependencies`.
   1. Select `+` icon and then `JARs or Directories...`
   1. Locate the `lib` folder and select it.
   1. Click `OK`
1. After the above steps are completed, locate the `src/main/java/mystars/MyStars.java` file, right-click it, and choose `Run MyStars.main()`. If the setup is correct, you should see something like the below:
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

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `ui-test` and run the `test(.bat/.sh)` script.

### JUnit tests

* A skeleton JUnit test (`src/test/java/mystars/MyStarsTest.java`) is provided with this project template. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).
