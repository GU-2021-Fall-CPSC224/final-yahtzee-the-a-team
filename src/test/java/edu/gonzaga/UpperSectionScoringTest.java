package edu.gonzaga;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpperSectionScoringTest {
    @Test
    void testLine6() {
        int expectedValue = 30;
        int dieSides = 6;
        Hand hand = new Hand(5,dieSides,3);
        List<Die> dice = new ArrayList<>();
        dice.add(new Die(dieSides, 6));
        dice.add(new Die(dieSides, 6));
        dice.add(new Die(dieSides, 6));
        dice.add(new Die(dieSides, 6));
        dice.add(new Die(dieSides, 6));
        hand.setHand(dice);
        UpperSectionScoring score = new UpperSectionScoring(hand);
        assertEquals(expectedValue, score.getSectionData().get(5));
    }
    @Test
    void testAdding35(){
        int expectedValue = 100;
        Hand hand = new Hand(5, 4, 3);
        ScorecardLine test = new ScorecardLine("test");
        UpperScorecard card = new UpperScorecard(new GameConfiguration());
        card.setTotalLine(65);

        UpperSectionScoring score = new UpperSectionScoring(hand);
        test.setValue(score.Add35(card));
        assertEquals(expectedValue, test.getValue());
    }
}

