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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.util.*;

/** Class to store the upper and lower sections of the scorecard. */
public abstract class Scorecard implements PropertyChangeListener {
    private ArrayList<ScorecardLine> lines;

    boolean isFull;

    private GameConfiguration configuration;

    private ScorecardLine totalLine;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Scorecard(){
        lines = new ArrayList<>();
        isFull = false;
        totalLine = new ScorecardLine("TOTAL");
    }

    public Scorecard(GameConfiguration configuration){
        this();
        this.configuration = configuration;
    }

    /**
     * Registers a PropertyChangeListener to this class.
     * @param listener the listener to register.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener to this class.
     * @param listener the listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    
    @Override
    public void propertyChange(PropertyChangeEvent evt) { //When a property is changed in a scoreline
        if(evt.getPropertyName().equals("scored")){
            pcs.firePropertyChange("scored", evt.getOldValue(), evt.getNewValue());
            boolean emptyLine = false;
            for(ScorecardLine line : lines) { //Reset un-scored lines after calculating score values.
                if(!line.isScored()) {
                    line.setValue(0);
                }

                calcNewTotal();

                if(!line.isScored()) {
                    emptyLine = true;
                }
            }
            isFull = !emptyLine;
        }
    }

    /**
     * Test if all lines in scorecard are full.
     * @return True if full, false if not full.
     */
    public boolean isFull(){
        for(ScorecardLine line : getLines()) {
            if(!line.isScored()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the data of all lines in this scorecard.
     * @return The list of lines in this scorecard.
     */
    public ArrayList<ScorecardLine> getLines() {
        return lines;
    }

    /**
     * Calculates all the possible scores for a hand and presents them to the user.
     *
     * @param hand the hand to score.
     */
    public abstract void scoreNewHand(Hand hand);

    /**
     * Calculates a new total score value.
     */
    public void calcNewTotal(){
        int old = totalLine.getValue();
        int sum = 0;
        for(ScorecardLine line : getLines()){
            sum += line.getValue();
        }

        totalLine.setValueWithEvent(sum);
        pcs.firePropertyChange("total", old, sum);
    }

    /**
     * Gets the total line model for this scorecard.
     * @return the total line model.
     */
    public ScorecardLine getTotalLine() {
        return totalLine;
    }
}
