package a3;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.io.IOException;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class A3Test {
    private StudentDatabaseConnection connection;
    private Registration reg;
    private String[] idNums;
    private String[] names;

    /**Question 1 part 1
     * The code under test is the two functions in the registration class:
     * (name(String idNUmber) and isValidIDNumber(String idNumber) )
     */

    @BeforeEach
    void setup() {
        //Create a mock connection and create a registration object with mocked connection
        connection = mock(StudentDatabaseConnection.class, withSettings().verboseLogging());
        reg = new Registration(connection);
        idNums = new String[]{"V11111111", "V22222222", "V33333333", "V44444444"};
        names = new String[] {"Andy Apple", "Betty Banana", "Cathy Cantaloupe", "Donald Durian"};



    }

    // Implement your tests below.  Feel free to modify the function signatures
    // as necessary - use parameterized testing as you see fit.

    // test for correct behaviour when the database is not connected.

    @Test
    void testNoConnection() {
        //Use test double to ensure that no connection is established when registration is called
        for (String input: idNums) {
            when(connection.isConnected()).thenReturn(false);
            //No need for parameterized testing here as database isn't even connected to check for multiple names
            //Used loop to make sure that entire given input domain was covered
            //Make sure correct exception is thrown
            assertThrows(DatabaseNotConnected.class, () -> { reg.name(input); } );


        }
        //Verify connection is only checked once per each id
        verify(connection, times(idNums.length)).isConnected();

    }

    // test for correct behaviour when the database is connected, but invalidly
    //    formatted IDs are submitted.  

    //Not interested in how invalidIdNumber works ( or if it does in the first place)
    //However we can't mock static methods with standard mockito
    //Instead we will run the test with the input being things we know are invalid ids
    //They will not start with v, some may be null, some may have a length other than 9
    //
    @ParameterizedTest
    @CsvSource({
            "'A12345678', Doesn't start with a V but is 9 chars and has rest numbers",
            "null, is null",
            "'V12345678910', starts with a v and isn't null but is over 8 digits for number portion",
            "'V213zxchj', has 8 digits and starts with a v but contains non numbers in numbers portion"


    })
    void invalidIDs(String input) {
        when(connection.isConnected()).thenReturn(true);
        System.out.println(input);

        assertThrows(InvalidIDNumberException.class, ()->{reg.name(input);});

        //Verify connection is only checked once per each id
        verify(connection, times(1)).isConnected();
        //verify(reg, times(1)).name(input);

    }

    // test for correct behaviour when the database is connected and students in
    //     the database are searched for using the correct IDs.
    
    @ParameterizedTest
    @CsvSource({
            "'V11111111', 'Andy Apple',      'Andy Apple' ",
            "'V22222222', 'Betty Banana',    'Betty Banana'",
            "'V33333333', 'Cathy Cantaloupe','Cathy Cantaloupe'",
            "'V44444444', 'Donald Durian',   'Donald Durian'"
    })
    void validIDs(String input, String name, String expected) {
        //Assume nameFromIDNumber will give us the name in database
        when(connection.nameFromIDNumber(input)).thenReturn(name);
        //Assume connection is working

        when(connection.isConnected()).thenReturn(true);
        assertEquals(reg.name(input), expected);
        //Make sure only 1 call is made for each input
        verify(connection, times(1)).isConnected();
        verify(connection, times(1)).nameFromIDNumber(input);

    }

    // test for correct behaviour when the database is connected and IDs are provided but
    // although they are the correct format (correct length, etc), there is no corresponding
    // student in the database.

    @ParameterizedTest
    @CsvSource({
            "'V51111111', 'Ahndy Apple' ",
            "'V52222222', 'Bhetty Banana'",
            "'V53333333', 'Chathy Cantaloupe'",
            "'V54444444', 'Dhonald Durian'"
    })
    void notInDatabase(String input, String name) {



        //Assume connection is working
        when(connection.isConnected()).thenReturn(true);

        //If the ID number does not correspond to any student in the database,
        // Then a StudentNotFound exception is raised.
        when(connection.nameFromIDNumber(input)).thenThrow(new StudentNotFoundException());

        assertThrows(StudentNotFoundException.class, ()->{reg.name(input);});

        //Check methods for interface are only called once per input
        verify(connection, times(1)).isConnected();
        verify(connection,times(1)).nameFromIDNumber(input);
    }

    // Test whether isValidIDNumber() accepts 1000 properly-formatted ID numbers

    //@IntRange(max = 1000) int me
    @Property

    void thousandValidNumbers(@ForAll("createNumbersWithinRange") int inputBeforeV) {

        String input = "V"+inputBeforeV;

        System.out.println(input);
        assertTrue(Registration.isValidIDNumber(input));
        //We care about numbers between 10000000 and 99999999 as they are 8 digits and will have V slapped in front of them

    }

    // Test whether isValidIDNumber() accepts 1000 ID numbers which are correctly formatted
    // except that their first letter is a lowercase letter rather than an uppercase V.
    @Property
    void thousandInvalidLeadingLetters(@ForAll("createNumbersWithinRange") int inputBeforeLetter, @ForAll @LowerChars char the_char) {

        String input = ""+the_char+inputBeforeLetter;
        System.out.println(input);
        assertFalse(Registration.isValidIDNumber(input));
    }

    //Creates numbers between the acceptable digit range for a student number (8 digit number)
    @Provide
    Arbitrary<Integer> createNumbersWithinRange(){

        return Arbitraries.integers().filter(n -> n>=10000000 && n<=99999999);
    }

}
