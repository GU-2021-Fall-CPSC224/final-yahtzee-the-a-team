/* (C)2021 */
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

import edu.gonzaga.dialogs.ParameterDialog;
import edu.gonzaga.views.PlayerView;

import javax.swing.*;

/** Main program class for launching Yahtzee program. */
public class Yahtzee {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(854, 480);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ParameterDialog dialog = new ParameterDialog(frame);
        dialog.setVisible(true);
        GameConfiguration config = dialog.getPayload();


        if(config != null){
            Player player = new Player(config);
            PlayerView playerView = new PlayerView(player);
            frame.setContentPane(playerView);
            frame.setVisible(true);
        }

    }
}
