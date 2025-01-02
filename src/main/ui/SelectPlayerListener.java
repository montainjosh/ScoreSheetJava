package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Team;
import model.Player;
import model.League;

// a listener that deals with Player actions
public class SelectPlayerListener implements ActionListener {

    private CardLayout cardLayout;
    private JPanel panel;
    private Player player;
    private Team team;
    private ScoreSheetAppGUI scoreSheetAppGUI;
    private League myLeague;
    private ScoreSheetPanel scoreSheetPanel;

    // constructor for Player listener
    public SelectPlayerListener(CardLayout cardLayout, JPanel panel, League myLeague, Team team, Player player,
                                ScoreSheetAppGUI scoreSheetAppGUI, ScoreSheetPanel scoreSheetPanel) {
        this.cardLayout = cardLayout;
        this.panel = panel;
        this.player = player;
        this.team = team;
        this.scoreSheetAppGUI = scoreSheetAppGUI;
        this.myLeague = myLeague;
        this.scoreSheetPanel = scoreSheetPanel;
    }

    // if/else block for action events related to Player
    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String removePlayerName = "Remove Player " + player.getName();
        String playerName = "Player " + player.getName();
        if (removePlayerName.equals(command)) {
            team.removePlayer(player);
            scoreSheetAppGUI.refreshMenu(myLeague, team);
        } else if (playerName.equals(command)) {
            scoreSheetPanel.createInterfacePlayerOptions(cardLayout, panel, myLeague, team, player);
            cardLayout.show(panel, playerName);
        } else if ("Add Goal".equals(command)) {
            player.addGoal();
            cardLayout.show(panel, "ScoreSheet");
        } else if ("Add Assist".equals(command)) {
            player.addAssist();
            cardLayout.show(panel, "ScoreSheet");
        } else if ("Add PIM".equals(command)) {
            scoreSheetPanel.createInterfacePenaltyMinuteOptions(cardLayout,panel,myLeague,player,team);
            cardLayout.show(panel,"Penalty Minutes");
        } else if ("2 Minutes".equals(command)) {
            player.addPenaltyMinutes(2);
            cardLayout.show(panel, "ScoreSheet");
        } else if ("5 Minutes".equals(command)) {
            player.addPenaltyMinutes(5);
            cardLayout.show(panel, "ScoreSheet");
        } else if ("10 Minutes".equals(command)) {
            player.addPenaltyMinutes(10);
            cardLayout.show(panel, "ScoreSheet");
        }
    }
}
