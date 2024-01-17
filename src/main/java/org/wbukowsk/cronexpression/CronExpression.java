package org.wbukowsk.cronexpression;

import java.util.List;

public record CronExpression(
        List<Integer> minutes, List<Integer> hours, List<Integer> dayOfMonth, List<Integer> month, List<Integer> dayOfWeek
){};
