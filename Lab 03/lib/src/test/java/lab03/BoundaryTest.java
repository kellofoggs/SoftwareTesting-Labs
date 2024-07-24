package lab03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BoundaryTest {

    @Test
    void isUnsafe() {
        assertTrue(Boundary.isUnsafe(86));
    }

    @Test
    void isNotUnsafe() {
        assertFalse(Boundary.isUnsafe(85));
    }

    @Test
    void isComfortable(){
        assertTrue(Boundary.isComfortable(5) && Boundary.isComfortable(20));

    }

    @Test
    void isNotComfortable(){
        assertFalse(Boundary.isComfortable(4) && Boundary.isComfortable(21));
    }
    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "2, 1",
            "6, 2"

    })
    void elevatorsRequired(int storeys, int elevators){
        assertEquals(Boundary.elevatorsRequired(storeys), elevators);
    }
    @Test
    void elevatorsNotRequired(){
    }

    @ParameterizedTest
    @CsvSource({
            "49, 'F'",
            "59, 'D'",
            "64, 'C'",
            "69.5, 'C+'",
            "72.5, 'B-'",
            "76.8, 'B'",
            "79, 'B+'",
            "80, 'A-'",
            "89, 'A'",
            "100, 'A+'"

    })
    void testPercentToLetterGrade(double percent, String letter){
        assertEquals(Boundary.percentageToLetterGrade(percent), letter);
    }


}