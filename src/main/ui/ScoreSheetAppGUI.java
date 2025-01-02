package ui;

import model.League;
import persistence.JsonWriter;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import model.Team;
import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

// graphical user interaction for my project
public class ScoreSheetAppGUI extends JFrame {

    private static final String JSON_PATH = "./data/league.json";
    private League myLeague;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ScoreSheetPanel scoreSheetPanel;
    private JPanel panel;
    private CardLayout cardLayout;

    // constructor for my project
    public ScoreSheetAppGUI() throws FileNotFoundException {
        super("ScoreSheet UI");
        myLeague = new League("My League");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                myLeague.printLog(EventLog.getInstance());
                super.windowClosing(e);
                System.exit(0);
            }
        });
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        initialize();
    }

    // EFFECTS: initializes League, JsonWriter and JsonReader and Opens Menu
    public void initialize() {
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);
        openMenu();
    }

    // EFFECTS: Initializes main Panel for my App
    public void openMenu() {
        scoreSheetPanel = new ScoreSheetPanel(this);
        add(scoreSheetPanel);
        panel = new JPanel();
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
        panel.add(scoreSheetPanel.createInterface(cardLayout,panel, "Options", myLeague,
                "Add/Edit Team", "Make New ScoreSheet", "List All Stats",
                "Load League From File", "Save League To File"));
        panel.add(scoreSheetPanel.createInterface(cardLayout,panel,"Add/Edit Team", myLeague,
                "Add Team", "Edit Team"));
        panel.add(scoreSheetPanel.createInterfaceText(cardLayout,panel,"Add Team", myLeague,
                "Name"));
        getContentPane().add(panel);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: refreshes many Panels to display updated information
    public void refreshMenu(League myLeague, Team myTeam) {
        this.myLeague = myLeague;
        panel.add(scoreSheetPanel.createInterfaceSelectTeam(cardLayout,panel,"Edit Team ",
                myLeague.getTeams(), myLeague, new Team("dummy")));
        panel.add(scoreSheetPanel.createInterface(cardLayout,panel,"Add/Edit Team", myLeague,
                "Add Team", "Edit Team"));
        panel.add(scoreSheetPanel.createInterfaceText(cardLayout,panel,"Add Team", myLeague,
                "Name"));
        panel.add(scoreSheetPanel.createInterface(cardLayout,panel, "Options ", myLeague,
                "Add/Edit Team", "Make New ScoreSheet", "List All Stats",
                "Load League From File", "Save League To File"));
        panel.add(scoreSheetPanel.createInterfaceSelectAddOrRemove(cardLayout,panel,
                "Add or Remove Player/Goalie", myLeague, myTeam));
        panel.add(scoreSheetPanel.createInterfaceTextAddPlayerOrGoalie(cardLayout,panel,"Add Player/Goalie",
                myLeague, myTeam));
        panel.add(scoreSheetPanel.createInterfaceAllStats(cardLayout,panel,"All Stats",myLeague));
        panel.add(scoreSheetPanel.createInterfaceSelectPlayerOrGoalie(cardLayout, panel,
                "Remove Player/Goalie", myLeague, myTeam));
        panel.add(scoreSheetPanel.createInterfaceSelectTeam(cardLayout, panel, "Select a Team",
                myLeague.getTeams(), myLeague, new Team("dummy")));
        cardLayout.show(panel, "Options ");
    }
}
