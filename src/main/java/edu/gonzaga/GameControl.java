/**
 * Name: Joshua Venable
 * Class: CPSC 224, Spring 2022
 * Date: 4/21/22
 * Programming Assigment: Final Yahtzee Project
 * Description: This class controls the game
 * Notes: 
 * 
 * 
 **/

package edu.gonzaga;

import edu.gonzaga.dialogs.ConfigurationDialog;
import edu.gonzaga.dialogs.PlayerDialog;
import edu.gonzaga.views.PlayerListView;
import edu.gonzaga.views.PlayerView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class GameControl implements PropertyChangeListener
{
    private int playerTurn = 0;
    public GameControl()
    {
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
                    PlayerView playerView = new PlayerView(player);
                    playerView.addPropertyChangeListener(this::propertyChange);
                    views.add(playerView);
                }
                //To make multiple players turns set content pane to player view.
                if(playerTurn >= views.size())
                {
                    playerTurn = 0;
                }
                frame.setContentPane(views.get(playerTurn));
                frame.setVisible(true);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(evt.getPropertyName().equals("nextPlayer"))
        {
            playerTurn++;
        }
    }
}
