package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testCalculate_validInputs(String time, double expectedAngle) {
        Clocke clocke = new Clocke();

        double angle = clocke.calculateAngle(time);

        assertEquals(expectedAngle, angle, 0.1, "The angle for " + time + " should be " + expectedAngle + " degrees.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"25", "12345", "2400", "2360", "ab30", "1a30", "12:30"})
    public void testCalculate_invalidInputs(String invalidInput) {
        Clocke clock = new Clocke();

        assertThrows(IllegalArgumentException.class, () -> clock.calculateAngle(invalidInput),
                "Expected IllegalArgumentException for invalid input: " + invalidInput);
    }
}
