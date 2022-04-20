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
import java.util.ArrayList;

public class PlayerView extends JPanel implements PropertyChangeListener {
    private Player player;
    private CenterView centerView;

    private HandView handView;

    private JButton scoreButton;
    private JLabel turnLabel;

//    private LowerScorecard lowerScorecard;
    private ScorecardView lowerScorecardView;

//    private UpperScorecard upperScorecard;
    private ScorecardView upperScorecardView;
    private JScrollPane upperScrollPane;

    private LabeledComponent totalLabel;

    private JDialog scoringDialog;

    /**
     * The main game class.
     */
    public PlayerView(Player player){
        setLayout(new BorderLayout());
//
//        turn = 1;
//        hand = new Hand(config);

//        upperScorecard = new UpperScorecard(config);
//        lowerScorecard = new LowerScorecard(config);

//        upperScorecard.addPropertyChangeListener(this::propertyChange);
//        lowerScorecard.addPropertyChangeListener(this::propertyChange);

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

        scoringDialog = new JDialog((JFrame)this.getParent(), true);
        centerView = new CenterView();

        add(upperScrollPane, BorderLayout.WEST);
        add(centerView, BorderLayout.CENTER);
        add(lowerScorecardView, BorderLayout.EAST);
    }

    /**
     * Called whenever a line is scored or a total need to update.
     * @param evt the event that was triggered.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) { //Advance turn.
        if(evt.getPropertyName().equals("scored")) {
            scoringDialog.setVisible(false);
            turnLabel.setText("Turn: " + player.getTurn());
            player.newTurn();
            handView.getRollButton().setEnabled(true);
        } else if(evt.getPropertyName().equals("total")) {
//            if(player.gameOver()) {
//                turnLabel.setText("GAME OVER!");
//                handView.getRollButton().setEnabled(false);
//                scoreButton.setEnabled(false);
//                totalLabel.getComponent().setBackground(Color.GREEN);
//            }
            int total = player.getUpperScorecard().getTotalLine().getValue() + player.getLowerScorecard().getTotalLine().getValue();
            ((JTextField)totalLabel.getComponent()).setText("" + total);
        }
    }

    /**
     * Represents the center view in this panel.
     */
    private class CenterView extends JPanel {
        private JLabel logo;

        private JPanel bottomButtons;

        public CenterView() {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            logo = new JLabel(new ImageIcon("media/logo_329x125.png"));
            logo.setPreferredSize(new Dimension(350,225));
            logo.setText("                                                                                        ");
            turnLabel = new JLabel("Turn: 1");

            scoreButton = new JButton("Score");
            scoreButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Scorecard> scorecards = player.score();
                    scoringDialog.setContentPane(new ScorecardView(scorecards, true, "Scoring"));
                    scoringDialog.setSize(600,500);
                    scoringDialog.setVisible(true);
                }
            });


            bottomButtons = new JPanel();

            add(logo);
            add(turnLabel);
            add(handView);
            bottomButtons.add(handView.getRollButton());
            bottomButtons.add(scoreButton);
            add(bottomButtons);
            add(totalLabel);

        }
    }
}
