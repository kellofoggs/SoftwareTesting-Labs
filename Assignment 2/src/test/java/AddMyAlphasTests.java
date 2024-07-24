import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
public class AddMyAlphasTests {
    private AddMyAlphas testedClass;

    @BeforeEach void init(){
        //Method signature did not specify static, chose to create object instead
        testedClass = new AddMyAlphas();
    }
    @Test
    void addNoNumbers(){
        assertEquals( 0, testedClass.add(""));
    }

    @Test
    void addOneNumbers() {
            assertEquals( 1, testedClass.add("1"));
    }

    @Test
    void addTwoNumbers(){
        assertEquals( 3, testedClass.add("1,2"));
    }
    @Test
    void addUnknownAmountOfNumbers(){
        Random rand = new Random();
        //Generate an unknown number which is how many numbers are in the list
        int upperBound = rand.nextInt(100);
        int expected = 0;
        String input = "";
        for ( int i = 0; i < upperBound; i++){
            //Each number in string is a random number
            int currNum = rand.nextInt(upperBound);
            if (i != upperBound-1){
            input = input +  currNum+",";
            }
            else{
                input = input + currNum;
            }
            //Expected sum tracked
            expected = expected+currNum;
        }
        assertEquals(expected,testedClass.add(input));
    }
    @Test
    void addNewLines(){
        assertEquals(10, testedClass.add("1,2,3\n4,0"));
    }
    @Test
    void noNegatives(){
        assertThrows(IllegalArgumentException.class, ()->{testedClass.add("1,-5,-4");});
    }
    @ParameterizedTest
    @CsvSource({"'1,2,1001', 3",
                "'1,2,1000', 1003"

    })
    void greaterThanOneThousand(String input, int expected){
        assertEquals(expected,testedClass.add(input));
    }

    @Test
    void allowCustomDelimiters(){
        assertEquals(15,testedClass.add("1a2,3,4,5"));
    }
}
