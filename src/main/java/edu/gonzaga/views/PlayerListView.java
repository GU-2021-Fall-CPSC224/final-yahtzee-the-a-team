package edu.gonzaga.views;

import edu.gonzaga.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class PlayerListView extends JPanel {
    private JList<String> players;
    private DefaultListModel<String> playersModel;

    private JButton addButton;
    private JButton removeButton;
    private JButton okButton;
    private JTextField nameField;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public PlayerListView() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 400));

        playersModel = new DefaultListModel<>();
        players = new JList<>(playersModel);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        okButton = new JButton("OK");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 25));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = nameField.getText();
                if (!text.equals("") && !playersModel.contains(text)) {
                    playersModel.add(0, text);
                    players.setSelectedIndex(0);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = players.getSelectedValue();
                if (selected != null) {
                    playersModel.removeElement(selected);
                }
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playersModel.getSize() <= 0) {
                    nameField.setText("Player 1");
                } else {
                    pcs.firePropertyChange("close", 0, 1);
                }
            }
        });

        add(players, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(nameField);
        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(okButton);
        add(bottomPanel, BorderLayout.SOUTH);
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

    public ArrayList<String> getPlayers() {
        ArrayList<String> players = new ArrayList<>();
        for (int i = 0; i < playersModel.getSize(); i++) {
            players.add(playersModel.get(i));
        }
        return players;
    }
}
