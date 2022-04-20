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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Class to calculate and store data for the lower section of the scorecard */
public class LowerSectionScoring {
    private Hand hand;
    private Map<String, Integer> sectionData;
    private List<Integer> counts;

    public LowerSectionScoring(Hand hand){
        sectionData = new HashMap<>();
        counts = new ArrayList<>();
        for(int i = 0; i<hand.getDice().get(0).getNumSides(); i++){
            counts.add(0);
        }
        updateSection(hand);
    }

    /**
     * Re-calculates the scorecard section with a new hand.
     *
     * @param hand the hand to calculate scorecard section with.
     */
    public void updateSection(Hand hand){
        this.hand = hand;
        countDie();
        calculateOfAKind();
        calculateStraight();
        calculateChance();
    }

    /**
     * Returns a map where each String key corresponds to the data on the line of the scorecard with
     * the same name. For example, the value at key "3k" would be the score on the three-of-a-kind
     * line in the scorecard.
     *
     * @return A map with a String-Integer key-value pair as described above.
     */
    public final Map<String, Integer> getSectionData() {
        return sectionData;
    }

    /**
     * Populates the counts list with the count of each number of die in the hand. For example,
     * if a hand was all 1's the list would contain 5 in the 0 index and 0 in the rest of the indices.
     */
    private void countDie(){
        List<Die> dice = hand.getDice();
        for(Die die : dice){
            counts.set(die.getSideUp()-1, counts.get(die.getSideUp()-1)+1);
        }
    }

    /**
     * Calculates and updates the data map for a "full house" and all 3 "of a kinds".
     */
    private void calculateOfAKind() {
        if (counts.contains(3)) {
            if (counts.contains(2)) {
                sectionData.put("fh", 25);
            }
            int sum = calculateSum();
            sectionData.put("3k", sum);
        }
        if (counts.contains(4)) {
            int sum = calculateSum();
            sectionData.put("3k", sum);
            sectionData.put("4k", sum);
        }
        if (counts.contains(5)) {
            int sum = calculateSum();
            sectionData.put("3k", sum);
            sectionData.put("4k", sum);
            sectionData.put("y", 50);
        }
    }

    /**
     * Calculates and updates the data map with "small straight" and "large straight"
     */
    private void calculateStraight(){
        int numConsec = 1;
        for(int i = 0; i<counts.size()-1; i++){
            if(counts.get(i) != 0 && counts.get(i+1) != 0){
                numConsec++;
            } else if((counts.get(i) == 0 || counts.get(i+1) == 0) && numConsec < 4) {
                numConsec = 1;
            }

            switch (numConsec){
                case 4:
                    sectionData.put("ss", 30);
                    break;
                case 5:
                    sectionData.put("ss", 30);
                    sectionData.put("ls", 40);
                    break;
            }
        }

    }

    /**
     * Calculates and updates the data map with the "chance" line.
     */
    private void calculateChance(){
        int sum = 0;
        for(int i = 1; i<=counts.size(); i++){
            sum += i*counts.get(i-1);
        }
        sectionData.put("c", sum);
    }

    /**
     * Calculates the sum of all die in the hand.
     *
     * @return the sum of all die in the hand.
     */
    private int calculateSum(){
        int sum = 0;
        for(Die die : hand.getDice()){
            sum += die.getSideUp();
        }
        return sum;
    }
}
