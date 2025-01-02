package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.League;
import model.Team;

// Listener related to Team
public class SelectTeamListener implements ActionListener {

    private CardLayout cardLayout;
    private JPanel panel;
    private Team myTeam;
    private League myLeague;
    private ScoreSheetAppGUI scoreSheetAppGUI;
    private String interfaceName;
    private ScoreSheetPanel scoreSheetPanel;
    private Team team1;


    // Constructor for Listener related to Team
    public SelectTeamListener(CardLayout cardLayout, JPanel panel, League myLeague, Team myTeam,
                              ScoreSheetAppGUI scoreSheetAppGUI, String interfaceName, ScoreSheetPanel scoreSheetPanel,
                              Team team1) {
        this.cardLayout = cardLayout;
        this.panel = panel;
        this.myTeam = myTeam;
        this.myLeague = myLeague;
        this.scoreSheetAppGUI = scoreSheetAppGUI;
        this.interfaceName = interfaceName;
        this.scoreSheetPanel = scoreSheetPanel;
        this.team1 = team1;

    }

    // EFFECTS: if/else block for actions related to Team
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (interfaceName == "Edit Team ") {
            scoreSheetAppGUI.refreshMenu(myLeague, myTeam);
            cardLayout.show(panel, "Add or Remove Player/Goalie");
        } else if (interfaceName == "Select a Team") {
            Team team1 = myTeam;
            List<Team> teams = myLeague.getTeams();
            List<Team> remainingTeams = new ArrayList<>();
            for (Team t : teams) {
                remainingTeams.add(t);
            }
            remainingTeams.remove(team1);
            panel.add(scoreSheetPanel.createInterfaceSelectTeam(cardLayout, panel, "Select Another Team",
                    remainingTeams, myLeague, team1));
            cardLayout.show(panel, "Select Another Team");
        } else if (interfaceName == "Select Another Team") {
            panel.add(scoreSheetPanel.createInterfaceScoreSheet(cardLayout, panel, "ScoreSheet", team1, myTeam,
                    myLeague));
            cardLayout.show(panel,"ScoreSheet");
        }
    }
}
