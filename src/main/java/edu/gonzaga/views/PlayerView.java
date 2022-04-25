/**
 * This program plays a game of Yahtzee.
 * CPSC 224, Spring 2022
 * HW4
 * No sources to cite.
 *
 * @author Tyler CH
 * @version v1.0 4/7/2022
 */
package edu.gonzaga.views;

import edu.gonzaga.*;
import edu.gonzaga.components.LabeledComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class PlayerView extends JPanel implements PropertyChangeListener {
    private Player player;
    private CenterView centerView;

    private HandView handView;

    private JButton scoreButton;
    private JLabel turnLabel;

    // private LowerScorecard lowerScorecard;
    private ScorecardView lowerScorecardView;

    // private UpperScorecard upperScorecard;
    private ScorecardView upperScorecardView;
    private JScrollPane upperScrollPane;

    private LabeledComponent totalLabel;

    private JDialog scoringDialog;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * The main game class.
     */
    public PlayerView(Player player) {
        setLayout(new BorderLayout());

        this.player = player;

        player.addPropertyChangeListener(this::propertyChange);

        handView = new HandView(player.getHand(), false);
        upperScorecardView = new ScorecardView(player.getUpperScorecard(), "Upper Scorecard");
        lowerScorecardView = new ScorecardView(player.getLowerScorecard(), "Lower Scorecard");

        upperScrollPane = new JScrollPane(upperScorecardView);
        upperScrollPane.setPreferredSize(new Dimension(250, 316));

        JTextField field = new JTextField("0");
        field.setEditable(false);
        field.setPreferredSize(new Dimension(50, 30));
        totalLabel = new LabeledComponent("Total Score ", field);

        scoringDialog = new JDialog((JFrame) this.getParent(), true);
        scoringDialog.setLocationRelativeTo(null);
        centerView = new CenterView();

        add(upperScrollPane, BorderLayout.WEST);
        add(centerView, BorderLayout.CENTER);
        add(lowerScorecardView, BorderLayout.EAST);
    }

    /**
     * Called whenever a line is scored or a total need to update.
     * 
     * @param evt the event that was triggered.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) { // Advance turn.
        if (evt.getPropertyName().equals("scored")) {
            // TODO: Create Next player's turn
            // Here is where the next player's turn should start
            this.pcs.firePropertyChange("nextPlayer", false, true);
            scoringDialog.setVisible(false);
            player.newTurn();
            turnLabel.setText("Turn: " + player.getTurn());
            handView.getRollButton().setEnabled(true);
        } else if (evt.getPropertyName().equals("total")) {
            int upperTotal = player.getUpperScorecard().getTotalLine().getValue();
            int lowerTotal = player.getLowerScorecard().getTotalLine().getValue();
            int total = upperTotal + lowerTotal;
            ((JTextField) totalLabel.getComponent()).setText("" + total);
        }
    }

    /**
     * Represents the center view in this panel.
     */
    private class CenterView extends JPanel {
        private JLabel logo;
        private JPanel bottomButtons;
        private JPanel namePanel = new JPanel();

        public CenterView() {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            logo = new JLabel(new ImageIcon("media/logo_329x125.png"));
            logo.setPreferredSize(new Dimension(350, 225));
            logo.setText("                                                                                        ");
            turnLabel = new JLabel("Turn: 1");

            scoreButton = new JButton("Score");
            scoreButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Scorecard> scorecards = player.score();
                    scoringDialog.setContentPane(new ScorecardView(scorecards, true, "Scoring"));
                    scoringDialog.setSize(600, 500);
                    scoringDialog.setVisible(true);
                }
            });

            bottomButtons = new JPanel();

            add(logo);
            namePanel.add(new JLabel(player.getName()), BorderLayout.WEST);
            namePanel.add(turnLabel, BorderLayout.EAST);
            add(namePanel);
            add(handView);
            bottomButtons.add(handView.getRollButton());
            bottomButtons.add(scoreButton);
            add(bottomButtons);
            add(totalLabel);

        }
    }

    /**
     * Registers a PropertyChangeListener to this class.
     * 
     * @param listener the listener to register.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener to this class.
     * 
     * @param listener the listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
