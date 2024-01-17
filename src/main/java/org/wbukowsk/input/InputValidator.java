package org.wbukowsk.input;

import java.util.ArrayList;
import java.util.List;

public class InputValidator {
    private static final int expectedArgumentsCount = 6;

    public static List<String> validate(String inputArgument) {
        ArrayList<String> validationErrors = new ArrayList<>();
        List<String> splitArguments = List.of(inputArgument.split("\\s+"));

        if (splitArguments.size() != expectedArgumentsCount) {
            validationErrors.add(String.format("Incorrect argument count. " +
                    "Expected 6 comma-separated values, found %d values. " +
                    "Correct input example: \"*/15 0 1,15 * 1-5 /usr/bin/find\"", splitArguments.size()));
        }

        return validationErrors;
    }
}
