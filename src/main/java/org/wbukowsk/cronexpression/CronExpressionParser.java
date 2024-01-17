package org.wbukowsk.cronexpression;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CronExpressionParser {
    /**
     * @param argument Part of cron expression you want to be parsed
     * @param argType Type of argument you want to be parsed
     * @return list of output values for given argument and it's type
     */
    public static List<Integer> parseTimeUnitExpression(String argument, CronTimeUnit argType) {
        List<String> argumentsList = List.of(argument.split(","));

        return argumentsList.stream().map(arg -> parseArg(arg, argType)).flatMap(List::stream).collect(Collectors.toList());
    }

    private static List<Integer> parseArg(String arg, CronTimeUnit argType) {
        // *
        if (arg.equals("*")) {
            return  IntStream.range(argType.getMinValue(),argType.getMaxValue()+1).boxed().collect(Collectors.toList());
        }

        // XY-ZV
        if (arg.matches("\\d{1,2}-\\d{1,2}")) {
            return parseRange(arg, argType);
        }

        // */XY
        if (arg.matches("\\*/\\d{1,2}")) {
            return parseStarIncrement(arg, argType);
        }

        // Z/XY
        if (arg.matches("\\d{1,2}/\\d{1,2}")) {
            return parseNumberIncrement(arg, argType);
        }

        // XY
        int argValue = Integer.parseInt(arg);
        checkArgumentInRange(argValue, argType);

        return List.of(argValue);
    }

    private static List<Integer> parseStarIncrement(String arg, CronTimeUnit argType) {
        int increment = Integer.parseInt(arg.substring(arg.indexOf("/")+1));
        checkArgumentInRange(increment, argType);

        return IntStream.iterate(argType.getMinValue(), n -> n <= argType.getMaxValue(), n -> n + increment)
                .boxed()
                .collect(Collectors.toList());
    }

    private static List<Integer> parseNumberIncrement(String arg, CronTimeUnit argType) {
        int startingNumber = Integer.parseInt(arg.substring(0, arg.indexOf("/")));
        int increment = Integer.parseInt(arg.substring(arg.indexOf("/")+1));
        checkArgumentInRange(startingNumber, argType);
        checkArgumentInRange(increment, argType);

        return IntStream.iterate(startingNumber, n -> n <= argType.getMaxValue(), n -> n + increment)
                .boxed()
                .collect(Collectors.toList());
    }

    private static List<Integer> parseRange(String arg, CronTimeUnit argType) {
        List<String> splitByComma = List.of(arg.split("-"));
        int leftSide = Integer.parseInt(splitByComma.get(0));
        int rightSide = Integer.parseInt(splitByComma.get(1));
        if (rightSide <= leftSide) {
            throw new IllegalArgumentException(String.format("In argument %s right side must be greater than left side.", arg));
        }

        checkArgumentInRange(leftSide, argType);
        checkArgumentInRange(rightSide, argType);

        return IntStream.range(leftSide, rightSide + 1).boxed().collect(Collectors.toList());
    }

    private static void checkArgumentInRange(int argValue, CronTimeUnit argType) {
        if (argValue < argType.getMinValue() || argValue > argType.getMaxValue()) {
            throw new IllegalArgumentException(String.format("Argument %s out of expected range: [%d, %d]", argType.getDescription(), argType.getMinValue(), argType.getMaxValue()));
        }
    }

}
