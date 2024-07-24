package lab03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class SpecificationTest {
    @ParameterizedTest
    @CsvSource({
            "0",
            "1.5",
            "1",
            "2"



    })
    void insideDisplayArea(double multiplier){
        if(multiplier >= 1){
            assertFalse(Specification.insideDisplayArea((int)(multiplier*Specification.width), (int)(multiplier*Specification.height)));

        }else {
            assertTrue(Specification.insideDisplayArea((int) (multiplier * Specification.width), (int) (multiplier * Specification.height)));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "'CANADAA', 'false'",
            "'VALID', 'true'"
    })
    void validMessage(String message, boolean bike){
        assertTrue(Specification.messageIsValid(message, bike));

    }

    @ParameterizedTest
    @CsvSource({
            "'WAS2134', 'true'",
            "'123456', false"


    })
    void invalidMessage(String message, boolean bike){
        assertFalse(Specification.messageIsValid(message, bike));
    }


}