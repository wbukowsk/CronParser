package org.wbukowsk.input;

import java.util.List;

public class ArgumentParser {
    public static String parse(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("No argument found. Correct input example: \"*/15 0 1,15 * 1-5 /usr/bin/find\"");
            throw new UnsupportedOperationException("One argument expected.");
        }

        String mainArgument = arguments[0];
        List<String> validationErrors = InputValidator.validate(mainArgument);

        if (validationErrors.size() > 0) {
            System.out.println("Your input: " + mainArgument);
            System.out.println("Your input is incorrect. Details: ");
            for (String validationError : validationErrors) {
                System.out.println(validationError);
            }

            throw new IllegalArgumentException("Incorrect input argument.");
        }

        return mainArgument;

    }
}
