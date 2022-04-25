package edu.gonzaga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayerTest {
    @Test
    void nextTurnTest() {
        Player player = new Player("name", new GameConfiguration());
        int oldTurn = player.getTurn();
        player.newTurn();
        assertNotEquals(oldTurn, player.getTurn());
    }

    @Test
    void testGetHand() {
        
    }

    @Test
    void testGetLowerScorecard() {
        
    }

    @Test
    void testGetName() {
        
    }

    @Test
    void testGetTurn() {
        
    }

    @Test
    void testGetUpperScorecard() {
        
    }

    @Test
    void testIsFull() {
        
    }

    @Test
    void testNewTurn() {
        
    }

    @Test
    void testPropertyChange() {
        
    }

    @Test
    void testRemovePropertyChangeListener() {
        
    }

    @Test
    void testScore() {
        
    }

    @Test
    void testSetLowerScorecard() {
        
    }

    @Test
    void testSetUpperScorecard() {
        
    }

    @Test
    void testTotalScore() {
        
    }
}
