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

import edu.gonzaga.dialogs.ConfigurationDialog;
import edu.gonzaga.dialogs.PlayerDialog;
import edu.gonzaga.views.PlayerListView;
import edu.gonzaga.views.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** Main program class for launching Yahtzee program. */
public class Yahtzee {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(854, 480);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ConfigurationDialog dialog = new ConfigurationDialog(frame);
        dialog.setVisible(true);
        GameConfiguration config = dialog.getPayload();


        if(config != null){
            PlayerDialog playerDialog = new PlayerDialog(frame);
            playerDialog.setVisible(true);

            ArrayList<String> names = playerDialog.getPayload();
            if(names.size() > 0) {
                ArrayList<Player> players = new ArrayList<>();
                for(String s : names) {
                    players.add(new Player(s, config));
                }

                ArrayList<PlayerView> views = new ArrayList<>();
                for(Player player : players) {
                    views.add(new PlayerView(player));
                }

                //To make multiple players turns set content pane to player view.
                frame.setContentPane(views.get(0));
                frame.setVisible(true);
            }
        }

    }
}
