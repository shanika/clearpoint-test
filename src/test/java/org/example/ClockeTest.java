package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClockeTest {

    @ParameterizedTest
    @CsvSource({
            "1230, 165.0",
            "0245, 187.5",
            "1445, 187.5",
            "1754, 147",
            "1710, 265",
            "1200, 0",
    })
    public void testCalculate(String time, double expectedAngle) {
        Clocke clocke = new Clocke();

        double angle = clocke.calculate(time);

        assertEquals(expectedAngle, angle, 0.1, "The angle for " + time + " should be " + expectedAngle + " degrees.");
    }
}
