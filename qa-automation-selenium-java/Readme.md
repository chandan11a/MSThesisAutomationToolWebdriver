# (Sulis Test)
The provided repository contains an automation scripts for 5 test cases written on Sulis. https://sulis.ul.ie/



# Notes
- Update the src/test/resources/test.properties file to replace dummy credentials and chromedriver path before you run the tests. 

- DO NOT change the project structure and use Gradle only



# Prerequisites:
- ChromeDriver 2.44 , JDK 8+
- Any IDE

# Development Environment:
- Modify src/test/resources/test.properties to point to ChromeDriver's path on your system
- On any terminal, move to the project's root folder and execute the following command:
    - ./gradlew clean test

