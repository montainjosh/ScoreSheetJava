package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;

import model.League;
import model.Team;

// general Listener for my App
public class InputListener implements ActionListener {

    private CardLayout cardLayout;
    private JPanel panel;
    private League myLeague;
    private ScoreSheetAppGUI scoreSheetAppGUI;
    private static final String JSON_PATH = "./data/league.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Team myTeam;

    // constructor for general Listener, instantiates jsonWriter and jsonReader
    public InputListener(CardLayout cardLayout, JPanel panel, League myLeague, Team myTeam,
                         ScoreSheetAppGUI scoreSheetAppGUI) {
        this.cardLayout = cardLayout;
        this.panel = panel;
        this.myLeague = myLeague;
        this.myTeam = myTeam;
        this.scoreSheetAppGUI = scoreSheetAppGUI;
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);
    }

    // if/else block for general Listener
    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Add/Edit Team".equals(command)) {
            cardLayout.show(panel, "Add/Edit Team");
        } else if ("Make New ScoreSheet".equals(command)) {
            scoreSheetAppGUI.refreshMenu(myLeague, myTeam);
            cardLayout.show(panel, "Select a Team");
        } else if ("Add Team".equals(command)) {
            cardLayout.show(panel, "Add Team");
        } else if ("Edit Team".equals(command)) {
            scoreSheetAppGUI.refreshMenu(myLeague, myTeam);
            cardLayout.show(panel, "Edit Team ");
        } else if ("Load League From File".equals(command)) {
            loadLeague();
        } else if ("Save League To File".equals(command)) {
            saveLeague();
        } else if ("Add Player/Goalie".equals(command)) {
            scoreSheetAppGUI.refreshMenu(myLeague, myTeam);
            cardLayout.show(panel, "Add Player/Goalie");
        } else if ("List All Stats".equals(command)) {
            scoreSheetAppGUI.refreshMenu(myLeague, new Team("dummy"));
            cardLayout.show(panel, "All Stats");
        } else if ("Return to Menu".equals(command)) {
            cardLayout.show(panel, "Options ");
        } else if ("Remove Player/Goalie".equals(command)) {
            scoreSheetAppGUI.refreshMenu(myLeague, myTeam);
            cardLayout.show(panel, "Remove Player/Goalie");
        } else if ("End ScoreSheet".equals(command)) {
            scoreSheetAppGUI.refreshMenu(myLeague,new Team("dummy"));
            List<Team> teams = myLeague.getTeams();
            for (Team t : teams) {
                System.out.println(t.getName());
            }
            cardLayout.show(panel,"Options ");
        }
    }

    // EFFECTS: loads League from file
    private void loadLeague() {
        try {
            myLeague = jsonReader.readLeague();
            System.out.println("Loaded " + myLeague.getName() + " from " + JSON_PATH);
            scoreSheetAppGUI.refreshMenu(myLeague, new Team("dummy"));

        } catch (IOException e) {
            System.out.println("Unable to load from file: " + JSON_PATH);
        }
    }

    // EFFECTS: saves League to file
    private void saveLeague() {
        try {
            jsonWriter.openWriter();
            jsonWriter.write(myLeague);
            jsonWriter.closeWriter();
            System.out.println("Saved " + myLeague.getName() + " to " + JSON_PATH);
            cardLayout.show(panel, "Options ");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file to " + JSON_PATH);
        }
    }
}
