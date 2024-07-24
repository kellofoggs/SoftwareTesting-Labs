package lab04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class WordUtilitiesTest {

    @ParameterizedTest
    @CsvSource({
            "'Hello', 'hELLO', mixed single word",
            "'WORLD', 'world', all caps to all lower case",
            "'print', PRINT, all lower case to all caps",
            "'Hello World', 'hELLO wORLD', a case where there is a phrase with white space",
            "'', '', an empty string"


    })
    void swapCaseTest( final String str, String expected){
        assertEquals(expected, WordUtilities.swapCase(str));


    }

}