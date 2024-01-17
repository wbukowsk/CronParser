package org.wbukowsk.cronexpression;

public enum CronTimeUnit {
    MINUTE("minute", 0, 59),
    HOUR("hour", 0, 23),
    DAY_OF_MONTH("day of month", 1, 31),
    MONTH("month", 1, 12),
    DAY_OF_WEEK("day of week", 1, 7);

    private final String description;
    private final int minValue;
    private final int maxValue;

    CronTimeUnit(String description, int minValue, int maxValue) {
        this.description = description;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getDescription() {
        return description;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public String toString() {
        return description + " (Range: " + minValue + " to " + maxValue + ")";
    }
}
