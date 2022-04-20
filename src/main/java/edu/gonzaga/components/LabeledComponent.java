/**
 * This program plays a game of Yahtzee.
 * CPSC 224, Spring 2022
 * HW4
 * No sources to cite.
 *
 * @author Tyler CH
 * @version v1.0 4/7/2022
 */
package edu.gonzaga.components;

import javax.swing.*;
import java.awt.*;

/**
 * Class creates a component with a label to its left.
 */
public class LabeledComponent extends JPanel {

    JLabel label;
    JComponent component;

    public LabeledComponent(String labelText, JComponent component, int width){
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        label = new JLabel(labelText);
        this.component = component;


        this.add(label);
        this.add(component);
    }

    public LabeledComponent(String labelText, JComponent component){
        this(labelText, component, 500);
    }

    /**
     * Gets the component part of this structure.
     * @return the JComponent.
     */
    public JComponent getComponent() {
        return component;
    }
}
