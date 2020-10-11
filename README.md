# Currency Exchange Application

**This application needs the an installation of Java (version 11 or later) to build and run.**

## Building the application
To build this application, pull the source code and ```cd``` into the root directory of the cloned repository. Then, run ```./gradlew build``` (or ```./gradlew.bat build``` if you are on Windows). This will build the project and test it as well.

## Testing the application
If you only want to test the application, run ```./gradlew test``` in the root directory of the cloned repository. This will execute the tests and generate reports. The test results can be found in ```<path to root directory>/build/reports/tests/test``` for the test report and ```<path to root directory>/build/reports/jacoco/test``` for the code coverage report. Opening the index.html file in these paths will show you the results in your browser.

## Running the application
To run the application, execute ```./gradlew run``` (or ```./gradlew.bat run``` if you are on Windows) in the root directory of the cloned repository after building the applcation. This should open up the app window and then you may use it.
