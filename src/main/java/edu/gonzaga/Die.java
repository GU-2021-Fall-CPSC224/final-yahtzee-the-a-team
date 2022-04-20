/* (C)2021 */
/**
 * This program plays a game of Yahtzee.
 * CPSC 224, Spring 2022
 * HW4
 * No sources to cite.
 *
 * @author
 * @version v1.0 4/7/2022
 */
package edu.gonzaga;

/*
* Class for a Die used in Yahtzee.
*/

import edu.gonzaga.dialogs.ParameterDialog;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/** Class to store the state of a single die. */
public class Die implements Comparable<Die> {
    private Integer sideUp; // Current die 'value' in range 1..numSides
    private Integer numSides; // Sides on the die (should be 1...INF integer)
    private static final Integer DEFAULT_NUM_SIDES = 6;
    private static final Integer DEFAULT_SIDE_UP = 1;

    private boolean locked;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Die() {
        locked = false;
        this.numSides = DEFAULT_NUM_SIDES;
        this.sideUp = DEFAULT_SIDE_UP;
    }

    public Die(Integer numSides) {
        locked = false;
        this.numSides = numSides;
        this.sideUp = DEFAULT_SIDE_UP;
    }

    public Die(Integer numSides, Integer startingSide) {
        locked = false;
        this.numSides = numSides;
        this.sideUp = startingSide;
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

    /**
     * Locks the die and fires an event.
     */
    public void lock(){
        boolean prev = locked;
        locked = true;
        pcs.firePropertyChange("lock", prev, true);
    }

    /**
     * Unlocks the die and fires an event.
     */
    public void unlock(){
        boolean prev = locked;
        locked = false;
        pcs.firePropertyChange("lock", prev, false);
    }

    /**
     * Check if the
     * @return true if the die is locked, false if unlocked.
     */
    public boolean isLocked(){
        return locked;
    }

    /** Rolls the die once, getting new random value. */
    public void roll() {
        if(!locked){
            Random rand = new Random();
            setNewValue(rand.nextInt(this.numSides) + 1);
        }
    }

    /**
     * Sets a new value for the die, fires an event.
     * @param newValue the new value to set.
     */
    private void setNewValue(int newValue) {
        int oldValue = sideUp;
        sideUp = newValue;
        pcs.firePropertyChange("sideUp", oldValue, newValue);
    }

    /**
    * Returns current die value (the side that's up).
    *
    * @return Integer Current Die's Side Up
    */
    public Integer getSideUp() {
        return this.sideUp;
    }

    /**
    * Returns quantity of sides on the die.
    *
    * @return Integer number of sides on the die
    */
    public Integer getNumSides() {
        return this.numSides;
    }

    /**
    * Provides the ability to convert the Die object into a string. representation, both with
    * .toString(), but also in System.out.println()
    *
    * @return String of whatever you want this die to say for itself
    */
    @Override
    public String toString() {
        String ret = "";
        ret += this.sideUp.toString();
        return ret;
    }

    /**
    * Makes two dice comparable using <, ==, >, etc. based on sideUp values.
    *
    * @param otherDie The die we're comparing to this one (two objects)
    * @return int -1, 0, 1 for less than, equal, greater than
    */
    @Override
    public int compareTo(Die otherDie) {
        return this.sideUp.compareTo(otherDie.sideUp);
    }
}
