package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

// Represents a hockey league that has a name and a list of teams
public class League implements Writable {
    private final String name;
    private List<Team> teams;
    private EventLog eventLog;

    // EFFECTS: constructs a league with given name and no teams
    public League(String name) {
        this.name = name;
        teams = new ArrayList<>();
        eventLog = EventLog.getInstance();
        EventLog.getInstance().logEvent(new Event("League " + name + " Made"));

    }

    // MODIFIES: this
    // EFFECTS: adds team to teams
    public void addTeam(Team team) {
        teams.add(team);
        EventLog.getInstance().logEvent(new Event("Team " + team.getName() + " Added"));
    }

    // REQUIRES: name is associated with a team in teams
    // EFFECTS: returns team associated with input team name
    public Team findTeam(String name) {
        Team foundTeam = new Team("NULL");
        for (Team t : teams) {
            if (name.equals(t.getName())) {
                foundTeam = t;
            }
        }
        return foundTeam;
    }

    // EFFECTS: returns League as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonLeague = new JSONObject();
        jsonLeague.put("name", name);
        jsonLeague.put("teams", teamsToJson());
        return jsonLeague;
    }

    // EFFECTS: returns teams in this League as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArrayTeams = new JSONArray();

        for (Team t: teams) {
            jsonArrayTeams.put(t.toJson());
        }

        return jsonArrayTeams;
    }

    public String getName() {
        return this.name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void printLog(EventLog eventLog) {
        for (Event e : eventLog) {
            System.out.println(e.toString());
        }
    }

    // Generates a string of all the stats in a League
    public String generateAllStats(League myLeague) {
        String allStats = "";
        allStats += myLeague.getName() + "\n\n";
        for (Team t : myLeague.getTeams()) {
            allStats += t.getName() + " Stats:" + "\nW: " + t.getWins() + "     L: " + t.getLosses() + "\n\n";
            List<Player> currentTeamPlayers = t.getPlayers();
            for (Player p : currentTeamPlayers) {
                allStats += p.getName() + "     G: " + p.getGoals() + "     A: " + p.getAssists();
                allStats += "     PIM: " + p.getPenaltyMinutes() + "\n";
            }
            allStats += "\n";
            List<Goalie> currentTeamGoalies = t.getGoalies();
            for (Goalie g : currentTeamGoalies) {
                allStats += g.getName() + "     SA: " + g.getShotsAgainst() + "     GA: " + g.getGoalsAgainst();
                allStats += "     Wins: " + g.getWins() + "\n";
            }
            allStats += "\n";
        }
        EventLog.getInstance().logEvent(new Event("League Stats Updated"));
        return allStats;
    }
}
