package org.wbukowsk;

import org.junit.jupiter.api.Test;
import org.wbukowsk.output.OutputData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainServiceTest {

    @Test
    void shouldProduceCorrectOutputData() {
        // given
        String inputExpression = "*/15 0 1,15 * 1-5 /usr/bin/find";

        // when
        OutputData outputData = MainService.produceOutputData(inputExpression);

        // then
        assertEquals(outputData.command(), "/usr/bin/find");
        assertEquals(outputData.cronExpression().minutes(), List.of(0, 15, 30, 45));
        assertEquals(outputData.cronExpression().hours(), List.of(0));
        assertEquals(outputData.cronExpression().dayOfMonth(), List.of(1,15));
        assertEquals(outputData.cronExpression().month(), List.of(1,2,3,4,5,6,7,8,9,10,11,12));
        assertEquals(outputData.cronExpression().dayOfWeek(), List.of(1,2,3,4,5));
    }
}
