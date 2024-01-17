package org.wbukowsk.output;

import org.wbukowsk.cronexpression.CronExpression;

public record OutputData(CronExpression cronExpression, String command) {}
