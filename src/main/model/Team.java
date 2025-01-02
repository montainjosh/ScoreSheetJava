package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.List;
import java.util.ArrayList;

// Represents a team with a name, players, goalies, and a number of wins and losses

public class Team implements Writable {

    private final String name;
    private List<Player> players;
    private List<Goalie> goalies;
    private int wins;
    private int losses;

    // REQUIRES: name has a non-zero length
    // EFFECTS: constructs a team with given name, empty lists for players and goalies, and 0 wins and losses
    public Team(String name) {
        this.name = name;
        players = new ArrayList<>();
        goalies = new ArrayList<>();
        wins = 0;
        losses = 0;
        if (!(name == "dummy")) {
            EventLog.getInstance().logEvent(new Event("Team " + name + " Made"));
        }
    }

    // REQUIRES: player's name may not be found in players (cannot appear twice)
    // MODIFIES: this
    // EFFECTS: adds player to team's players
    public void addPlayer(Player player) {
        players.add(player);
        EventLog.getInstance().logEvent(new Event("Player " + player.getName() + " Added to " + name));
    }

    // REQUIRES: player is within players
    // MODIFIES: this
    // EFFECTS: removes player from players
    public void removePlayer(Player player) {
        players.removeIf(p -> p.equals(player));
        EventLog.getInstance().logEvent(new Event("Player " + player.getName() + " Removed from " + name));
    }

    // REQUIRES: input name matches name of player in players
    // EFFECTS: return player with corresponding name
    public Player findPlayer(String name) {
        Player foundPlayer = new Player("NULL", 1, "NULL");
        for (Player p : players) {
            if (p.getName().equals(name)) {
                foundPlayer = p;
            }
        }
        return foundPlayer;
    }

    // REQUIRES: goalie's name not found in goalies (cannot appear twice)
    // MODIFIES: this
    // EFFECTS: adds goalie to team's goalies
    public void addGoalie(Goalie goalie) {
        goalies.add(goalie);
        EventLog.getInstance().logEvent(new Event("Goalie " + goalie.getName() + " Added to " + name));
    }

    // REQUIRES: player is within players
    // MODIFIES: this
    // EFFECTS: removes player from players
    public void removeGoalie(Goalie goalie) {
        goalies.removeIf(g -> g.equals(goalie));
        EventLog.getInstance().logEvent(new Event("Goalie " + goalie.getName() + " Removed from " + name));
    }

    // REQUIRES: input name matches name of goalie in goalies
    // EFFECTS: return goalie with corresponding name
    public Goalie findGoalie(String name) {
        Goalie foundGoalie = new Goalie("NULL", 1);
        for (Goalie g : goalies) {
            if (g.getName().equals(name)) {
                foundGoalie = g;
            }
        }
        return foundGoalie;
    }

    // MODIFIES: this
    // EFFECTS: adds 1 to wins
    public void addWin() {
        wins++;
    }

    // MODIFIES: this
    // EFFECTS: adds 1 to losses
    public void addLoss() {
        losses++;
    }

    // EFFECTS: returns Team as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonTeam = new JSONObject();
        jsonTeam.put("name", name);
        jsonTeam.put("players", playersToJson());
        jsonTeam.put("goalies", goaliesToJson());
        jsonTeam.put("wins", wins);
        jsonTeam.put("losses", losses);
        return jsonTeam;
    }

    // EFFECTS: returns players in this team as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArrayPlayers = new JSONArray();

        for (Player p: players) {
            jsonArrayPlayers.put(p.toJson());
        }

        return jsonArrayPlayers;
    }

    // EFFECTS: returns goalies in this team as a JSON array
    private JSONArray goaliesToJson() {
        JSONArray jsonArrayGoalies = new JSONArray();

        for (Goalie g: goalies) {
            jsonArrayGoalies.put(g.toJson());
        }

        return jsonArrayGoalies;
    }


    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Goalie> getGoalies() {
        return goalies;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
}
