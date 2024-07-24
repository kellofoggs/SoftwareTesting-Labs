package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * A very simple (and not particularly useful)
 * test class to have a starting point where to put tests.
 *
 * @author Arie van Deursen
 */


public class DirectionTest {
    /**
     * Do we get the correct delta when moving north?
     */
    @Test
    void testNorth() {
        Direction north = Direction.valueOf("NORTH");
        assertThat(north.getDeltaY()).isEqualTo(-1);
    }
    /**
     * Do we get the correct delta when moving in any direction
     *
     * */
    @ParameterizedTest
    @CsvSource({
            "'NORTH', -1",
            "'SOUTH', 1"
    })
    void testVerticalDirections(String direction, int expected){
        Direction dir = Direction.valueOf(direction);
        assertThat(dir.getDeltaY()).isEqualTo(expected);

    }

    @ParameterizedTest
    @CsvSource({
            "'WEST', -1",
            "'EAST', 1"
    })
    void testHorizontalDirections(String direction, int expected){
        Direction dir = Direction.valueOf(direction);
        assertThat(dir.getDeltaX()).isEqualTo(expected);

    }
}
