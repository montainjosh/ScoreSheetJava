package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.Team;
import model.League;

// Listener for making a Team
public class TextListener implements ActionListener {

    private CardLayout cardLayout;
    private JPanel panel;
    private String function;
    private List<JTextField> textFields;
    private League myLeague;
    private ScoreSheetAppGUI scoreSheetAppGUI;

    // constructor for text Listener
    public TextListener(CardLayout cardLayout, JPanel panel, String interfaceName,
                        League myLeague,List<JTextField> textFields, ScoreSheetAppGUI scoreSheetAppGUI) {
        this.cardLayout = cardLayout;
        this.panel = panel;
        function = interfaceName;
        this.textFields = textFields;
        this.myLeague = myLeague;
        this.scoreSheetAppGUI = scoreSheetAppGUI;
    }

    // EFFECTS: receives name to make a Team
    @Override
    public void actionPerformed(ActionEvent e) {
        List<String> userInput = new ArrayList<>();
        for (JTextField textField : textFields) {
            userInput.add(textField.getText());
        }
        if ("Add Team".equals(function)) {
            Team myTeam = new Team(userInput.get(0));
            myLeague.addTeam(myTeam);
            clearTextFields(textFields);
            scoreSheetAppGUI.refreshMenu(myLeague, new Team("dummy"));
        }
    }

    // EFFECTS: clears text fields
    public void clearTextFields(List<JTextField> textFields) {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
    }
}