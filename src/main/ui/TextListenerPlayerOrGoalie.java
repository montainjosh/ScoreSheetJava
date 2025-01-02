package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.Team;
import model.League;
import model.Player;
import model.Goalie;

// Text Listener for adding Players or Goalies
public class TextListenerPlayerOrGoalie implements ActionListener {

    private CardLayout cardLayout;
    private JPanel panel;
    private String function;
    private List<JTextField> textFields;
    private ScoreSheetAppGUI scoreSheetAppGUI;
    private Team myTeam;
    private League myLeague;

    // Constructor for Listener that creates a Player or Goalie
    public TextListenerPlayerOrGoalie(CardLayout cardLayout, JPanel panel, String interfaceName, League myLeague,
                        Team myTeam,List<JTextField> textFields, ScoreSheetAppGUI scoreSheetAppGUI) {
        this.cardLayout = cardLayout;
        this.panel = panel;
        function = interfaceName;
        this.textFields = textFields;
        this.scoreSheetAppGUI = scoreSheetAppGUI;
        this.myTeam = myTeam;
        this.myLeague = myLeague;
    }

    // EFFECTS: makes a Player or Goalie based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        List<String> userInput = new ArrayList<>();
        for (JTextField textField : textFields) {
            userInput.add(textField.getText());
        }
        if ("Enter Player".equals(command)) {
            Player myPlayer = new Player(userInput.get(0),Integer.parseInt(userInput.get(1)),userInput.get(2));
            myTeam.addPlayer(myPlayer);
            clearTextFields(textFields);
            scoreSheetAppGUI.refreshMenu(myLeague, myTeam);

        } else if ("Enter Goalie".equals(command)) {
            Goalie myGoalie = new Goalie(userInput.get(3),Integer.parseInt(userInput.get(4)));
            myTeam.addGoalie(myGoalie);
            clearTextFields(textFields);
            scoreSheetAppGUI.refreshMenu(myLeague, myTeam);
        }
    }

    // EFFECTS: clears text fields
    public void clearTextFields(List<JTextField> textFields) {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
    }
}
