package assignment1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class parametrizedTesting {

    @ParameterizedTest
    @CsvSource({
            " 'lemon', 5",
            " 'matrix', 6",
            " 'run', 3"
            })
    void testCorrectLength(String string, int expected){


        assertEquals(Assignment1.getLength(string), expected);

    }

    @ParameterizedTest
    @CsvSource({
            "15, 'FizzBuzz!'",
            "8,  '8!'",
            "6,  'Fizz!'",
            "25, 'Buzz!'"


    })
    void fizzBuzzTest(int number, String expected){
        assertEquals(expected, Assignment1.fizzBuzz(number));

    }

    /**
     * Tests that valid roman numeral numbers are being translated to the proper arabic form.
     * */
    @ParameterizedTest
    @CsvSource({
            "'X', 10,   Tests whether a singular numeral number is properly translated",
            "'XI', 11, Case of multiple numeral number with additive notation",
            "'VL', 45, Case of multiple numeral subtractive notation",


    })
    void validRomanNumeralTest(String input, int output){
        RomanNumeral r = new RomanNumeral();
        assertEquals( output, r.convert(input) );

    }

    /**
     * Tests out invalid roman numeral test cases to see if an appropriate error is thrown.
     * */
    @ParameterizedTest
    @CsvSource({
            "'XXXXXL, Case where the user entered values incorrectly, having multiple smaller vals before larger vals ",
            "'ABCD', Case where user entered values that are not roman numeral letters"
    })
    void invalidRomanNumeralTest(String input){
        RomanNumeral r = new RomanNumeral();

        assertThrows(IllegalArgumentException.class, () -> {r.convert(input);});
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 1, on points",
            "0, 0, 0, off points",
            "3, 4, 5, off points",
            "5, 5, 8, off points"

    })
    void equilateralTest(int x, int y, int z){
        assertEquals(       Assignment1.Triangle.equilateral ,new Assignment1().categorise(x, y, z));


    }

}
