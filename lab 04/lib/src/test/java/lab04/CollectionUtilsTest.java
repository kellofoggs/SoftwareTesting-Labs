package lab04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class CollectionUtilsTest {

    @ParameterizedTest
    @MethodSource("generator")
    void containsAny(String description, boolean expected, final Collection<?> collection1, final Collection<?> collection2){

        //System.out.println((int)(((ArrayList)collection1).get(0)));
        assertEquals(expected, CollectionUtils.containsAny(collection1,collection2));




    }
    private static Stream<Arguments> generator(){

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 4;
        Integer e = 5;
        Integer f = 6;
        return Stream.of(

                Arguments.of("Identical arrays", true, new ArrayList<>(Arrays.asList(new Integer[]{a,b,c})), new ArrayList<>(Arrays.asList(new Integer[]{a,b,c}) )),
                Arguments.of("Collection one one is smaller and has the same things in it", true, new ArrayList<>(Arrays.asList(new Integer[]{a})), new ArrayList<>(Arrays.asList(new Integer[]{a,b,c}) )),
                Arguments.of("Collection one one is smaller and has nothing in common with collection", false, new ArrayList<>(Arrays.asList(new Integer[]{f})), new ArrayList<>(Arrays.asList(new Integer[]{a,b,c}) ))

               //Arguments.of("Collection one one is smaller and has the same things in it"),
               //Arguments.of("Completely different arrays"),
               //Arguments.of("Completely different arrays"),
               //Arguments.of("Completely different arrays"),
               //Arguments.of("Completely different arrays"),
               //Arguments.of("Completely different arrays"),
                //Arguments.of("Completely different arrays", false, new char[]{'a', 'b', 'c'}, new char[]{'d','e','c'})






                );

    }

}