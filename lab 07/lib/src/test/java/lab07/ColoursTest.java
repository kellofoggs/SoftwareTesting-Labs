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

class ColoursTest {

    /*
     * Create property tests that test combinations of valid and invalid values.
     */
    @Property
    void validValues(@ForAll @IntRange(min = 0, max = 255)int r,@ForAll @IntRange(min = 0, max = 255)int g,@ForAll @IntRange(min = 0, max = 255)int b){
        String[] rgb = {Integer.toHexString(r),
                Integer.toHexString(g),
                Integer.toHexString(b)};

        for (int i = 0; i < 3 ;i++){
            if (rgb[i].length()<2){
                rgb[i] = "0"+rgb[i];
            }
        }
        String expectedHexString =rgb[0]+rgb[1]+rgb[2];
        int expected = Integer.parseInt(expectedHexString, 16);
        System.out.println(expectedHexString);
        assertThat( Colours.rgbBytesToInt(r, g, b)).isEqualTo(expected);

    }
    @Property
    void invalidR(@ForAll("invalidRange")int r, @ForAll @IntRange(min = 0, max = 255)int g,@ForAll @IntRange(min = 0, max = 255)int b){
        assertThrows(IllegalArgumentException.class, ()-> Colours.rgbBytesToInt(r, g, b));

    }
    @Property
    void invalidG(@ForAll @IntRange(min = 0, max = 255)int r,@ForAll("invalidRange") int g,@ForAll @IntRange(min = 0, max = 255)int b){
        assertThrows(IllegalArgumentException.class, ()-> Colours.rgbBytesToInt(r, g, b));

    }
    @Property
    void invalidB(@ForAll @IntRange(min = 0, max = 255)int r, @ForAll @IntRange(min = 0, max = 255)int g,@ForAll("invalidRange")int b){
        assertThrows(IllegalArgumentException.class, ()-> Colours.rgbBytesToInt(r, g, b));

    }
    @Property
    void invalidRG(@ForAll("invalidRange")int r, @ForAll @IntRange(min = 0, max = 255)int g,@ForAll("invalidRange")int b){
        assertThrows(IllegalArgumentException.class, ()-> Colours.rgbBytesToInt(r, g, b));

    }
    @Property
    void invalidBG(@ForAll @IntRange(min = 0, max = 255)int r, @ForAll("invalidRange")int g,@ForAll("invalidRange")int b){
        assertThrows(IllegalArgumentException.class, ()-> Colours.rgbBytesToInt(r, g, b));

    }

    @Property
    void invalidRB(@ForAll("invalidRange")int r, @ForAll @IntRange(min = 0, max = 255)int g,@ForAll("invalidRange")int b){
        assertThrows(IllegalArgumentException.class, ()-> Colours.rgbBytesToInt(r, g, b));

    }


    @Property
    void invalidAll(@ForAll("invalidRange")int r, @ForAll("invalidRange")int g,@ForAll("invalidRange")int b){
        assertThrows(IllegalArgumentException.class, ()-> Colours.rgbBytesToInt(r, g, b));

    }

    @Provide
    private Arbitrary<Integer> invalidRange(){
        return Arbitraries.oneOf(Arbitraries.integers().lessOrEqual(-1), Arbitraries.integers().greaterOrEqual(256));

    }


}