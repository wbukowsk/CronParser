# Cron Expression Parser
The Cron Expression Parser is a command-line application written in Java, designed to parse and expand cron strings into a human-readable format. This application focuses on standard cron format with five time fields: minute, hour, day of month, month, and day of week, plus a command. It transforms the cron syntax into a detailed breakdown of execution times.

## Features
Parses standard cron strings with five time fields.
Outputs a formatted table showing expanded execution times.
Handles a single cron string + command as an argument. 
Handled expressions:
- '\*'- any (all) values for given unit
- '\-' range, e.g. 1-4
- '\/' increment, either starting with \* or initial value, e.g. 3/3 for month unit would produce 3,6,9
- numeric value, e.g. 4


## Limitations

Application parses each cron unit separately, that means that it does not validate if given month has given number of days

Operators L, W, #, ? and operators like @yearly are not supported

## System Requirements
Java Runtime Environment (JRE) - Version 17 or above.
## Installation
This application does not require any special installation. Ensure that Java is installed and configured on your system.

---

# Instructions to Run the Project

## Running the Application

1. **Compile the Application**:
    - Navigate to the project's root directory.
    - Run `mvn clean install` to build the project. This will generate a `CronParser-1.0-SNAPSHOT.jar` in the `target` directory.

2. **Execute the JAR**:
    - Use the Java command to run the JAR file with the cron expression as an argument.
    - Command format: `java -jar target/CronParser-1.0-SNAPSHOT.jar "your-cron-expression"`
    - Example: `java -jar target/CronParser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"`

## Expected Output

- The application will parse the cron expression and output a table. For example, the cron string `*/15 0 1,15 * 1-5 /usr/bin/find` will produce:
  minute        0 15 30 45
  hour          0
  day of month  1 15
  month         1 2 3 4 5 6 7 8 9 10 11 12
  day of week   1 2 3 4 5
  command       /usr/bin/find

## Running Tests

- Execute `mvn test` in the project root directory to run the unit tests.
