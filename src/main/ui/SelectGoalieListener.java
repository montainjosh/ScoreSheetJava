package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Team;
import model.Goalie;
import model.League;

// Listener for Goalie actions
public class SelectGoalieListener implements ActionListener {

    private CardLayout cardLayout;
    private JPanel panel;
    private Goalie goalie;
    private Team team;
    private ScoreSheetAppGUI scoreSheetAppGUI;
    private League myLeague;
    private ScoreSheetPanel scoreSheetPanel;

    // constructor for Goalie Listener
    public SelectGoalieListener(CardLayout cardLayout, JPanel panel, League myLeague, Team team, Goalie goalie,
                                ScoreSheetAppGUI scoreSheetAppGUI, ScoreSheetPanel scoreSheetPanel) {
        this.cardLayout = cardLayout;
        this.panel = panel;
        this.goalie = goalie;
        this.team = team;
        this.scoreSheetAppGUI = scoreSheetAppGUI;
        this.myLeague = myLeague;
        this.scoreSheetPanel = scoreSheetPanel;
    }

    // EFFECTS: if/else block for ActionEvents related to Goalie
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String removeGoalieName = "Remove Goalie " + goalie.getName();
        String goalieName = "Goalie " + goalie.getName();
        if (removeGoalieName.equals(command)) {
            team.removeGoalie(goalie);
            scoreSheetAppGUI.refreshMenu(myLeague, team);
        } else if (goalieName.equals(command)) {
            scoreSheetPanel.createInterfaceGoalieOptions(cardLayout, panel, myLeague, team, goalie);
            cardLayout.show(panel, goalieName);
        } else if ("Add Shot Against".equals(command)) {
            goalie.addShotAgainst();
            cardLayout.show(panel, "ScoreSheet");
        } else if ("Add Goal Against".equals(command)) {
            goalie.addGoalAgainst();
            cardLayout.show(panel, "ScoreSheet");
        }
    }
}
