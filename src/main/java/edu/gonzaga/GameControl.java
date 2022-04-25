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

    private ArrayList<String> names;
    private ArrayList<PlayerView> playerViews;
    private int playerTurn = 0;
    private JFrame frame = new JFrame();
    
    public GameControl()
    {
        frame.setSize(854, 480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
        ConfigurationDialog dialog = new ConfigurationDialog(frame);
        dialog.setVisible(true);
        GameConfiguration config = dialog.getPayload();
    
    
        if(config != null){
            PlayerDialog playerDialog = new PlayerDialog(frame);
            playerDialog.setVisible(true);
    
            names = playerDialog.getPayload();
            if(!names.isEmpty()) {
                ArrayList<Player> players = new ArrayList<>();
                for(String s : names) {
                    players.add(new Player(s, config));
                }
    
                playerViews = new ArrayList<>();
                for(Player player : players) {
                    PlayerView playerView = new PlayerView(player);
                    playerView.addPropertyChangeListener(this::propertyChange);
                    playerViews.add(playerView);
                }
                startNextPlayerRound();
            }
        }
    }

    /**
    * @Author Joshua Venable
    * @Date created: 4/22/22;
    * Date last modified: 4/22/22
    * @Description makes the next player's content pane visible
    * @pre previously visible player content pane
    * @post invisible previous player content pane, visible current player content pane
    **/
    private void startNextPlayerRound(){
        if(playerTurn >= playerViews.size())
        {
            playerTurn = 0;
        }
        frame.setVisible(false);
        frame.setContentPane(playerViews.get(playerTurn));
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(evt.getPropertyName().equals("nextPlayer"))
        {
            playerTurn++;
            startNextPlayerRound();
        }
    }
}
