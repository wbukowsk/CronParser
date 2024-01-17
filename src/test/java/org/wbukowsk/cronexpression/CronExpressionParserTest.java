package org.wbukowsk.cronexpression;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.wbukowsk.cronexpression.CronExpressionParser.parseTimeUnitExpression;

class CronExpressionParserTest {

    @ParameterizedTest
    @MethodSource("provideStarArguments")
    void shouldParseStarArgument(String argument, CronTimeUnit argType, List<Integer> expected) {
        List<Integer> result = parseTimeUnitExpression(argument, argType);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideDirectArguments")
    void shouldParseDirectArguments(String argument, CronTimeUnit argType, List<Integer> expected) {
        List<Integer> result = parseTimeUnitExpression(argument, argType);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideRangeArguments")
    void shouldParseRangeArguments(String argument, CronTimeUnit argType, List<Integer> expected) {
        List<Integer> result = parseTimeUnitExpression(argument, argType);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideIncrementArguments")
    void shouldParseIncrementArguments(String argument, CronTimeUnit argType, List<Integer> expected) {
        List<Integer> result = parseTimeUnitExpression(argument, argType);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideSimpleCommaSeparatedArguments")
    void shouldParseSimpleCommaSeparatedArguments(String argument, CronTimeUnit argType, List<Integer> expected) {
        List<Integer> result = parseTimeUnitExpression(argument, argType);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("provideComplexCommaSeparatedArguments")
    void shouldParseComplexCommaSeparatedArguments(String argument, CronTimeUnit argType, List<Integer> expected) {
        List<Integer> result = parseTimeUnitExpression(argument, argType);
        assertEquals(expected, result);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForWrongRange() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> parseTimeUnitExpression("4-2", CronTimeUnit.MINUTE)
        );

        assertNotNull(exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForArgumentOutsideOfRange() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> parseTimeUnitExpression("66", CronTimeUnit.MINUTE)
        );

        assertNotNull(exception.getMessage());
    }

    private static Stream<Arguments> provideStarArguments() {
        return Stream.of(
                Arguments.of("*", CronTimeUnit.MINUTE, IntStream.range(0,60).boxed().collect(Collectors.toList())),
                Arguments.of("*", CronTimeUnit.HOUR, IntStream.range(0,24).boxed().collect(Collectors.toList())),
                Arguments.of("*", CronTimeUnit.DAY_OF_MONTH, IntStream.range(1,32).boxed().collect(Collectors.toList())),
                Arguments.of("*", CronTimeUnit.MONTH, IntStream.range(1,13).boxed().collect(Collectors.toList())),
                Arguments.of("*", CronTimeUnit.DAY_OF_WEEK, IntStream.range(1,8).boxed().collect(Collectors.toList()))
        );
    }

    private static Stream<Arguments> provideDirectArguments() {
        return Stream.of(
                Arguments.of("1", CronTimeUnit.MINUTE, List.of(1)),
                Arguments.of("22", CronTimeUnit.HOUR,  List.of(22)),
                Arguments.of("5", CronTimeUnit.DAY_OF_MONTH,List.of(5)),
                Arguments.of("2", CronTimeUnit.MONTH, List.of(2)),
                Arguments.of("4", CronTimeUnit.DAY_OF_WEEK, List.of(4))
        );
    }

    private static Stream<Arguments> provideRangeArguments() {
        return Stream.of(
                Arguments.of("15-20", CronTimeUnit.MINUTE, List.of(15,16,17,18,19,20)),
                Arguments.of("3-5", CronTimeUnit.HOUR,  List.of(3,4,5)),
                Arguments.of("1-5", CronTimeUnit.DAY_OF_MONTH,List.of(1,2,3,4,5)),
                Arguments.of("9-11", CronTimeUnit.MONTH, List.of(9,10,11)),
                Arguments.of("2-4", CronTimeUnit.DAY_OF_WEEK, List.of(2,3,4))
        );
    }

    private static Stream<Arguments> provideSimpleCommaSeparatedArguments() {
        return Stream.of(
                Arguments.of("1,2,3", CronTimeUnit.MINUTE, List.of(1,2,3)),
                Arguments.of("4,22", CronTimeUnit.HOUR,  List.of(4,22)),
                Arguments.of("1,3,5", CronTimeUnit.DAY_OF_MONTH,List.of(1,3,5)),
                Arguments.of("1,2,11", CronTimeUnit.MONTH, List.of(1,2,11)),
                Arguments.of("4,5", CronTimeUnit.DAY_OF_WEEK, List.of(4,5))
        );
    }

    private static Stream<Arguments> provideComplexCommaSeparatedArguments() {
        return Stream.of(
                Arguments.of("1,2-4", CronTimeUnit.MINUTE, List.of(1,2,3,4)),
                Arguments.of("2-3,5-6", CronTimeUnit.HOUR,  List.of(2,3,5,6)),
                Arguments.of("1,3-4,5-6", CronTimeUnit.DAY_OF_MONTH,List.of(1,3,4,5,6)),
                Arguments.of("1,3/2", CronTimeUnit.MONTH, List.of(1,3,5,7,9,11)),
                Arguments.of("1,*/3", CronTimeUnit.DAY_OF_WEEK, List.of(1,1,4,7))
        );
    }

    public static Stream<Arguments> provideIncrementArguments() {
        return Stream.of(
                Arguments.of("10/10", CronTimeUnit.MINUTE, List.of(10,20,30,40,50)),
                Arguments.of("*/5", CronTimeUnit.HOUR,  List.of(0,5,10,15,20)),
                Arguments.of("6/12", CronTimeUnit.DAY_OF_MONTH,List.of(6,18,30)),
                Arguments.of("*/3", CronTimeUnit.MONTH, List.of(1,4,7,10)),
                Arguments.of("*/3", CronTimeUnit.DAY_OF_WEEK, List.of(1,4,7))
        );
    }
}
