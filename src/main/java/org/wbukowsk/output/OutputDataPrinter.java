package org.wbukowsk.output;

import org.wbukowsk.cronexpression.CronExpression;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.wbukowsk.cronexpression.CronTimeUnit.*;

public class OutputDataPrinter {
    private static final String stringFormat = "%-14s";
    private static final String commandString = "command";


    public static void printOutput(OutputData outputData) {
        CronExpression cronExpression = outputData.cronExpression();
        System.out.println(String.format(stringFormat, MINUTE.getDescription()) + formatCronExpression(cronExpression.minutes()));
        System.out.println(String.format(stringFormat, HOUR.getDescription()) + formatCronExpression(cronExpression.hours()));
        System.out.println(String.format(stringFormat, DAY_OF_MONTH.getDescription()) + formatCronExpression(cronExpression.dayOfMonth()));
        System.out.println(String.format(stringFormat, MONTH.getDescription()) + formatCronExpression(cronExpression.month()));
        System.out.println(String.format(stringFormat, DAY_OF_WEEK.getDescription()) + formatCronExpression(cronExpression.dayOfWeek()));
        System.out.println(String.format(stringFormat, commandString) + outputData.command());
    }

    private static String formatCronExpression(List<Integer> input) {
        return new TreeSet<>(input).stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
