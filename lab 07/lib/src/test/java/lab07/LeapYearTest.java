
package lab07;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.*;
import net.jqwik.api.*;
import net.jqwik.api.arbitraries.*;
import net.jqwik.api.constraints.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class LeapYearTest {
    @Property
    void validTrueTest(@ForAll("leapYearSource") int year){
        System.out.println(year);
        assertTrue(LeapYear.isLeapYear(year));
    }
    @Property
    void validFalseTest(@ForAll("normalYearSource") int year){

        assertFalse(LeapYear.isLeapYear(year));

    }


    @Property
    void invalidTest(@ForAll @IntRange(min = Integer.MIN_VALUE, max = 0)int year){
        assertThrows(IllegalArgumentException.class, () -> LeapYear.isLeapYear(year));


    }

    @Provide
    Arbitrary<Integer> leapYearSource(){
        //Did not include %400 case as if something is divisible cleanly by 400 it is also divisible by 4
        return Arbitraries.integers().filter(n -> n%4 ==0 && n > 1 && n%100 !=0 );

    }
    @Provide
    Arbitrary<Integer> normalYearSource(){
        return Arbitraries.oneOf(Arbitraries.integers().filter(n -> n%4 !=0 && n > 1 ),
                Arbitraries.integers().filter(n -> n%100 == 0  && n > 1 && n %400!=0  )) ;

    }



}