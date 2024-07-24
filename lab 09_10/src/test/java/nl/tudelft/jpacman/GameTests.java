package nl.tudelft.jpacman;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.SinglePlayerGame;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    private MapParser parser;

    /**
     * Set up the map parser.
     */
    Game game;
    @BeforeEach
    void setup(){
        Launcher launcher = new Launcher();
        launcher.makeGame();
         game= launcher.getGame();
    }
    /**
     * Validates following user story "Suspend the game"
     * As a player
     * I want to be able to suspend the game
     * So that I can pause and do something else
     * Scenario 1:
     * Given the game has started
     * When the player clicks the "Stop"button:
     * Then the game is not in progress
     * */
    @Test
    void verifySuspendGame(){

        game.start();
        assertTrue(game.isInProgress());
        game.stop();
        assertFalse(game.isInProgress());

    }
    /**
     * Achieve 100% branch coverage for start game method using parametrized test
     * Want game in progress true
     */
    @ParameterizedTest
    @CsvSource({
            "'true','false','0', 'true'",
            "'false', 'true', 1,, 'false'",
            "'false', 'false', 0",
            "'false', 'true', 0",
            "'false', 'false', 1"

    })
    void startGameTest(boolean inProgress, boolean anyPlayerAlive, int pellets){
        //Uses partial mocks instead of standard mock
        Game spy = spy(game);
        when(spy.isInProgress()).thenReturn(inProgress);
        Level spy_two = spy(game.getLevel());
        when(spy.getLevel()).thenReturn(spy_two);
        when(spy.getLevel().isAnyPlayerAlive()).thenReturn(anyPlayerAlive);
        when(spy.getLevel().remainingPellets()).thenReturn(pellets);
        spy.start();
        assertTrue(spy.isInProgress());
    }

    @Test
    void ParseMapTest(){
        PacManSprites sprites = new PacManSprites();
        LevelFactory levelFactory = new LevelFactory(
                sprites,
                new GhostFactory(sprites),
                mock(PointCalculator.class));

        parser = new MapParser(levelFactory, new BoardFactory(sprites));
        parser.parseMap(List.of("#"));


    }


}
