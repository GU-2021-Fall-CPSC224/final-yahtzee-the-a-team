package edu.gonzaga.dialogs;

import edu.gonzaga.GameConfiguration;
import edu.gonzaga.views.ParameterView;
import edu.gonzaga.views.PlayerListView;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PlayerDialog extends JDialog implements PropertyChangeListener {
    PlayerListView content;

    public PlayerDialog(JFrame frame) {
        super(frame, "Add Players", true);
        content = new PlayerListView();
        content.addPropertyChangeListener(this::propertyChange);
        setContentPane(content);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(500,400);
        setResizable(false);
    }

    public ArrayList<String> getPayload() {
        return content.getPlayers();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("close")) {
            setVisible(false);
        }
    }
}
