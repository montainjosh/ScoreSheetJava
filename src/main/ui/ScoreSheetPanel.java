package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.League;
import model.Team;
import model.Player;
import model.Goalie;
import model.ScoreSheet;

// creates Interfaces for my app
public class ScoreSheetPanel extends JPanel {

    private ScoreSheetAppGUI scoreSheetAppGUI;
    private static final String IMAGE_PATH = "./data/hockeypuck.png";


    // constructor for ScoreSheetPanel
    public ScoreSheetPanel(ScoreSheetAppGUI scoreSheetAppGUI) {
        this.scoreSheetAppGUI = scoreSheetAppGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    // EFFECTS: Creates a generic JPanel with buttons of input label names
    public JPanel createInterface(CardLayout cardLayout, JPanel panel, String interfaceName, League myLeague,
                                  String... labelNames) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());

        newPanel.add(new JLabel(interfaceName));

        for (String name : labelNames) {
            JButton button = new JButton(name);
            button.addActionListener(new InputListener(cardLayout, panel, myLeague, new Team("dummy"),
                    scoreSheetAppGUI));
            newPanel.add(button);
        }
        BufferedImage hockeyPuck = null;
        try {
            hockeyPuck = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(hockeyPuck));
        newPanel.add(picLabel);

        cardLayout.addLayoutComponent(interfaceName, newPanel);
        return newPanel;
    }

    // EFFECTS: creates the JPanel that shows the players and goalies of the two teams of a ScoreSheet
    public JPanel createInterfaceScoreSheet(CardLayout cardLayout, JPanel panel, String interfaceName, Team team1,
                              Team team2, League myLeague) {
        ScoreSheet myScoreSheet = new ScoreSheet(team1, team2);
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());
        JPanel team1Panel = new JPanel(new GridLayout(5, 2));
        JPanel team2Panel = new JPanel(new GridLayout(5, 2));

        addPlayers(cardLayout, panel, team1Panel,myLeague,team1,scoreSheetAppGUI);
        addGoalies(cardLayout, panel, team1Panel,myLeague,team1,scoreSheetAppGUI);
        addPlayers(cardLayout, panel, team2Panel,myLeague,team2,scoreSheetAppGUI);
        addGoalies(cardLayout, panel, team2Panel,myLeague,team2,scoreSheetAppGUI);

        newPanel.add(createSubPanel(team1Panel,team1.getName()), BorderLayout.NORTH);
        newPanel.add(createSubPanel(team2Panel,team2.getName()),BorderLayout.SOUTH);

        JButton exitButton = new JButton("End ScoreSheet");
        exitButton.addActionListener(new InputListener(cardLayout, panel, myLeague, new Team("dummy"),
                scoreSheetAppGUI));
        newPanel.add(exitButton,BorderLayout.CENTER);

        cardLayout.addLayoutComponent(interfaceName, newPanel);
        return newPanel;
    }

    // EFFECTS: adds all Player buttons for a team
    public void addPlayers(CardLayout cardLayout, JPanel mainPanel, JPanel subPanel, League myLeague, Team myTeam,
                           ScoreSheetAppGUI scoreSheetAppGUI) {
        List<Player> players = myTeam.getPlayers();
        for (Player p : players) {
            JButton button = new JButton("Player " + p.getName());
            button.addActionListener(new SelectPlayerListener(cardLayout, mainPanel, myLeague, myTeam, p,
                    scoreSheetAppGUI, this));
            subPanel.add(button);
        }
    }

    // EFFECTS: adds all Goalie buttons for a team
    public void addGoalies(CardLayout cardLayout, JPanel mainPanel, JPanel subPanel, League myLeague, Team myTeam,
                           ScoreSheetAppGUI scoreSheetAppGUI) {
        List<Goalie> goalies = myTeam.getGoalies();
        for (Goalie g : goalies) {
            JButton button = new JButton("Goalie " + g.getName());
            button.addActionListener(new SelectGoalieListener(cardLayout, mainPanel, myLeague, myTeam, g,
                    scoreSheetAppGUI, this));
            subPanel.add(button);
        }
    }

    //EFFECTS: Creates a JPanel that displays text fields for the user to enter information in
    public JPanel createInterfaceText(CardLayout cardLayout, JPanel panel, String interfaceName,
                                      League myLeague, String... fields) {
        List<JTextField> textFields = new ArrayList<>();
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        newPanel.add(new JLabel(interfaceName));

        for (String name : fields) {
            newPanel.add(new JLabel(name));
            JTextField userInput = new JTextField(50);
            newPanel.add(userInput);
            textFields.add(userInput);
        }

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new TextListener(cardLayout, panel, interfaceName, myLeague, textFields,
                scoreSheetAppGUI));

        newPanel.add(enterButton);
        cardLayout.addLayoutComponent(interfaceName, newPanel);
        return newPanel;
    }

    //EFFECTS: Creates a JPanel that allows the user to input information to make a Player or Goalie
    public JPanel createInterfaceTextAddPlayerOrGoalie(CardLayout cardLayout, JPanel panel, String interfaceName,
                                                       League myLeague, Team myTeam) {
        JPanel newPanel = new JPanel(new BorderLayout());
        JPanel playerPanel = new JPanel(new GridLayout(0, 2));
        JPanel goaliePanel = new JPanel(new GridLayout(0, 2));
        List<JTextField> textFields = new ArrayList<>();

        List<String> playerFields = List.of("Name", "Age", "Position");
        List<String> goalieFields = List.of("Name", "Age");

        addFieldsAndButtons(playerPanel, playerFields, textFields, "Enter Player", cardLayout, panel,
                interfaceName, myLeague, myTeam);
        addFieldsAndButtons(goaliePanel, goalieFields, textFields, "Enter Goalie", cardLayout, panel,
                interfaceName, myLeague, myTeam);

        newPanel.add(createSubPanel(playerPanel, "Enter Player Info"), BorderLayout.NORTH);
        newPanel.add(createSubPanel(goaliePanel, "Enter Goalie Info"), BorderLayout.SOUTH);

        cardLayout.addLayoutComponent(interfaceName, newPanel);
        return newPanel;
    }

    // EFFECTS: Helper method that adds text fields and an enter button
    private void addFieldsAndButtons(JPanel panel, List<String> fields, List<JTextField> textFields, String buttonText,
                                     CardLayout cardLayout, JPanel mainPanel, String interfaceName, League myLeague,
                                     Team myTeam) {
        for (String fieldName : fields) {
            panel.add(new JLabel(fieldName));
            JTextField userInput = new JTextField(20);
            textFields.add(userInput);
            panel.add(userInput);
        }

        JButton enterButton = new JButton(buttonText);
        enterButton.addActionListener(new TextListenerPlayerOrGoalie(cardLayout, mainPanel, interfaceName, myLeague,
                myTeam, textFields, scoreSheetAppGUI));
        panel.add(enterButton);
    }

    // EFFECTS: Creates a Panel that is used to separate and go in between 2 other panels
    private JPanel createSubPanel(JPanel panel, String label) {
        JPanel subPanel = new JPanel(new BorderLayout());
        subPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        subPanel.add(new JLabel(label), BorderLayout.NORTH);
        subPanel.add(panel, BorderLayout.CENTER);
        return subPanel;
    }

    // EFFECTS: Creates a JPanel that displays all teams in input list of teams
    public JPanel createInterfaceSelectTeam(CardLayout cardLayout, JPanel panel, String interfaceName,
                                            List<Team> teams, League myLeague, Team team) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        newPanel.add(new JLabel(interfaceName));

        for (Team myTeam : teams) {
            JButton button = new JButton(myTeam.getName());
            button.addActionListener(new SelectTeamListener(cardLayout, panel, myLeague, myTeam, scoreSheetAppGUI,
                    interfaceName, this, team));
            newPanel.add(button);
        }
        cardLayout.addLayoutComponent(interfaceName, newPanel);
        return newPanel;
    }

    // EFFECTS: Creates a JPanel that has "Add" and "Remove" buttons
    public JPanel createInterfaceSelectAddOrRemove(CardLayout cardLayout, JPanel panel, String interfaceName,
                                                   League myLeague, Team team) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        newPanel.add(new JLabel(interfaceName));

        JButton addButton = new JButton("Add Player/Goalie");
        JButton removeButton = new JButton(("Remove Player/Goalie"));
        addButton.addActionListener(new InputListener(cardLayout, panel, myLeague, team,
                scoreSheetAppGUI));
        removeButton.addActionListener(new InputListener(cardLayout, panel, myLeague, team, scoreSheetAppGUI));
        newPanel.add(addButton);
        newPanel.add(removeButton);

        cardLayout.addLayoutComponent(interfaceName, newPanel);
        return newPanel;
    }

    // EFFECTS: displays all players and goalies on a team for the purpose of removing them
    public JPanel createInterfaceSelectPlayerOrGoalie(CardLayout cardLayout, JPanel panel, String interfaceName,
                                                      League myLeague, Team team) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        newPanel.add(new JLabel(interfaceName));
        List<Player> players = team.getPlayers();
        List<Goalie> goalies = team.getGoalies();

        for (Player player : players) {
            JButton button = new JButton("Remove Player " + player.getName());
            button.addActionListener(new SelectPlayerListener(cardLayout, panel, myLeague, team, player,
                    scoreSheetAppGUI, this));
            newPanel.add(button);
        }

        for (Goalie goalie : goalies) {
            JButton button = new JButton("Remove Goalie " + goalie.getName());
            button.addActionListener(new SelectGoalieListener(cardLayout, panel, myLeague, team, goalie,
                    scoreSheetAppGUI, this));
            newPanel.add(button);
        }
        cardLayout.addLayoutComponent(interfaceName, newPanel);
        return newPanel;
    }

    // EFFECTS: Creates a JPanel that displays all the stats in a League
    public JPanel createInterfaceAllStats(CardLayout cardLayout, JPanel panel, String interfaceName, League myLeague) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());

        newPanel.add(new JLabel(interfaceName));

        JTextArea allStats = new JTextArea(myLeague.generateAllStats(myLeague));
        allStats.setEditable(false);
        newPanel.add(allStats);

        JButton button = new JButton("Return to Menu");
        button.addActionListener(new InputListener(cardLayout, panel, myLeague, new Team("dummy"),
                scoreSheetAppGUI));
        newPanel.add(button);

        cardLayout.addLayoutComponent(interfaceName, newPanel);
        return newPanel;
    }


    // Creates a JPanel with buttons to edit Goalie stats
    public void createInterfaceGoalieOptions(CardLayout cardLayout, JPanel panel, League myLeague, Team team,
                                             Goalie goalie) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        newPanel.add(new JLabel(goalie.getName()));

        JButton addShotAgainstButton = new JButton("Add Shot Against");
        JButton addGoalAgainstButton = new JButton(("Add Goal Against"));
        addShotAgainstButton.addActionListener(new SelectGoalieListener(cardLayout, panel, myLeague, team, goalie,
                scoreSheetAppGUI, this));
        addGoalAgainstButton.addActionListener(new SelectGoalieListener(cardLayout, panel, myLeague, team, goalie,
                scoreSheetAppGUI, this));
        newPanel.add(addShotAgainstButton);
        newPanel.add(addGoalAgainstButton);

        cardLayout.addLayoutComponent("Goalie " + goalie.getName(), newPanel);
        panel.add(newPanel);
    }

    // Creates a JPanel with buttons to edit Player stats
    public void createInterfacePlayerOptions(CardLayout cardLayout, JPanel panel, League myLeague, Team team,
                                             Player player) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        newPanel.add(new JLabel(player.getName()));

        JButton addGoalButton = new JButton("Add Goal");
        JButton addAssistButton = new JButton("Add Assist");
        JButton addPenaltyMinutesButton = new JButton("Add PIM");
        addGoalButton.addActionListener(new SelectPlayerListener(cardLayout, panel, myLeague, team, player,
                scoreSheetAppGUI, this));
        addAssistButton.addActionListener(new SelectPlayerListener(cardLayout, panel, myLeague, team, player,
                scoreSheetAppGUI, this));
        addPenaltyMinutesButton.addActionListener(new SelectPlayerListener(cardLayout, panel, myLeague, team, player,
                scoreSheetAppGUI, this));
        newPanel.add(addGoalButton);
        newPanel.add(addAssistButton);
        newPanel.add(addPenaltyMinutesButton);

        cardLayout.addLayoutComponent("Player " + player.getName(), newPanel);
        panel.add(newPanel);
    }

    // Creates a JPanel that displays all possible penalty minute options as buttons
    public JPanel createInterfacePenaltyMinuteOptions(CardLayout cardLayout, JPanel panel, League myLeague,
                                                      Player player, Team team) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        newPanel.add(new JLabel("Select Amount of Minutes"));
        List<String> pimOptions = List.of("2 Minutes","5 Minutes","10 Minutes");

        for (String s : pimOptions) {
            JButton button = new JButton(s);
            button.addActionListener(new SelectPlayerListener(cardLayout, panel, myLeague, team, player,
                    scoreSheetAppGUI, this));
            newPanel.add(button);
        }

        cardLayout.addLayoutComponent("Penalty Minutes", newPanel);
        panel.add(newPanel);
        return newPanel;
    }
}

