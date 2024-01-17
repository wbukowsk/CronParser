package org.wbukowsk;

import org.wbukowsk.cronexpression.CronExpression;
import org.wbukowsk.cronexpression.CronTimeUnit;
import org.wbukowsk.output.OutputData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.wbukowsk.cronexpression.CronExpressionParser.parseTimeUnitExpression;
import static org.wbukowsk.cronexpression.CronTimeUnit.*;

public class MainService {
    private static final int commandPosition = 5;

    // assumes inputExpression in format "*/15 0 1,15 * 1-5 /usr/bin/find"
    public static OutputData produceOutputData(String inputExpression) {
        List<String> splitArguments = List.of(inputExpression.split("\\s+"));
        Map<CronTimeUnit, String> cronUnitToValue = buildCronExpressionMap(splitArguments.subList(0, commandPosition));

        CronExpression cronExpression = new CronExpression(
                parseTimeUnitExpression(cronUnitToValue.get(MINUTE), MINUTE),
                parseTimeUnitExpression(cronUnitToValue.get(HOUR), HOUR),
                parseTimeUnitExpression(cronUnitToValue.get(DAY_OF_MONTH), DAY_OF_MONTH),
                parseTimeUnitExpression(cronUnitToValue.get(MONTH), MONTH),
                parseTimeUnitExpression(cronUnitToValue.get(DAY_OF_WEEK), DAY_OF_WEEK));

        return new OutputData(cronExpression, splitArguments.get(commandPosition));

    }

    private static Map<CronTimeUnit, String> buildCronExpressionMap(List<String> cronExpressionUnits) {
        HashMap<CronTimeUnit, String> cronExpressionMap = new HashMap<>();
        cronExpressionMap.put(MINUTE, cronExpressionUnits.get(0));
        cronExpressionMap.put(HOUR, cronExpressionUnits.get(1));
        cronExpressionMap.put(DAY_OF_MONTH, cronExpressionUnits.get(2));
        cronExpressionMap.put(MONTH, cronExpressionUnits.get(3));
        cronExpressionMap.put(DAY_OF_WEEK, cronExpressionUnits.get(4));
        return cronExpressionMap;
    }
}
