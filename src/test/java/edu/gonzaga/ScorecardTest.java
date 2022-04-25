package edu.gonzaga;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScorecardTest {
    @Test
    void testDefaultIsFull() {
        boolean expectedValue = false;
        Scorecard scorecard = new UpperScoreCard(new GameConfiguration());
        assertEquals(expectedValue, scorecard.isFull());
    }

    @Test
    void testIsFullUpper(){
        boolean expectedValue = true;
        Scorecard scorecard = new UpperScoreCard(new GameConfiguration());
        for(ScorecardLine line : scorecard.getLines()){
            line.score();
        }
        assertEquals(expectedValue, scorecard.isFull());
    }

    @Test
    void testNotIsFullUpper() {
        boolean expectedValue = false;
        Scorecard scorecard = new UpperScoreCard(new GameConfiguration());
        ArrayList<ScorecardLine> lines = scorecard.getLines();
        for(int i = 0; i<lines.size()-1; i++) {
            lines.get(i).score();
        }
        assertEquals(expectedValue, scorecard.isFull());
    }

    @Test
    void testIsFullLower(){
        boolean expectedValue = true;
        Scorecard scorecard = new LowerScoreCard(new GameConfiguration());
        for(ScorecardLine line : scorecard.getLines()){
            line.score();
        }
        assertEquals(expectedValue, scorecard.isFull());
    }

}

