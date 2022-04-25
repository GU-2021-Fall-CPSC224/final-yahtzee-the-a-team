/**
 * This program plays a game of Yahtzee.
 * CPSC 224, Spring 2022
 * HW4
 * No sources to cite.
 *
 * @author Tyler CH
 * @version v1.0 4/7/2022
 */
package edu.gonzaga;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UpperScorecard extends Scorecard {

    private final String[] titles = {
            "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
            "Sevens", "Eights", "Nines", "Tens", "Elevens", "Twelves"
    };

    public UpperScorecard(GameConfiguration config){
        super(config);

        for(int i = 0; i<config.getNumDieSides(); i++){
            getLines().add(new ScorecardLine(titles[i]));
            getLines().get((getLines().size()-1)).addPropertyChangeListener(this::propertyChange);
        }
    }

    /**
     * @Author Tyler C
     * @Date created: 
     * Date last modified:
     * @Description 
     * @param 
     * @return 
     * @pre 
     * @post 
     **/
    @Override
    public void scoreNewHand(Hand hand) {
        UpperSectionScoring scoring = new UpperSectionScoring(hand);
        List<Integer> data = scoring.getSectionData();
        for(int i = 0; i<data.size(); i++){
            if(!getLines().get(i).isScored()) {
                getLines().get(i).setValue(data.get(i));
            }
        }
    }
}
