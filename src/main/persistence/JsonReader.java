package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Converts a JSON object containing League data to a League
public class JsonReader {
    private String source;

    // EFFECTS: constructs a JSON reader to read from a file from given path
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads jsonData from source path and returns it
    // throws IOException if it could not read file
    public League readLeague() throws IOException {
        String jsonData = readFile(source);
        JSONObject myLeagueJson = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("League Loaded from File"));
        return parseLeague(myLeagueJson);
    }

    // EFFECTS: makes a contentBuilder that gets the file's data, then returns it as a string
    // throws IOException if there's an error reading from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses League from JSON object and returns it
    private League parseLeague(JSONObject myLeagueJson) {
        String name = myLeagueJson.getString("name");
        League myLeague = new League(name);
        addTeams(myLeague, myLeagueJson);
        return myLeague;
    }

    // MODIFIES: League
    // EFFECTS: parses teams from JSON object and adds them to League
    private void addTeams(League myLeague, JSONObject myLeagueJson) {
        JSONArray teamJsonArray = myLeagueJson.getJSONArray("teams");
        for (Object jsonTeam : teamJsonArray) {
            JSONObject nextTeam = (JSONObject) jsonTeam;
            addTeam(myLeague, nextTeam);
        }
    }

    // MODIFIES: League
    // EFFECTS: parses team from JSON object and adds it to League
    private void addTeam(League myLeague, JSONObject teamJson) {
        String name = teamJson.getString("name");
        int wins = teamJson.getInt("wins");
        int losses = teamJson.getInt("losses");

        Team team = new Team(name);
        for (int i = 1; i <= wins; i++) {
            team.addWin();
        }
        for (int i = 1; i <= losses; i++) {
            team.addLoss();
        }

        addPlayers(team, teamJson);
        addGoalies(team, teamJson);
        myLeague.addTeam(team);

    }

    // MODIFIES: League
    // EFFECTS: parses players from JSON object and adds it to League
    private void addPlayers(Team team, JSONObject teamJson) {
        JSONArray jsonArrayPlayers = teamJson.getJSONArray("players");
        for (Object jsonPlayer : jsonArrayPlayers) {
            JSONObject nextPlayer = (JSONObject) jsonPlayer;
            addPlayer(team, nextPlayer);
        }
    }

    // MODIFIES: League
    // EFFECTS: parses player from JSON object and adds it to League
    private void addPlayer(Team team, JSONObject playerJson) {
        String name = playerJson.getString("name");
        int age = playerJson.getInt("age");
        String position = playerJson.getString("position");
        int goals = playerJson.getInt("goals");
        int assists = playerJson.getInt("assists");
        int penaltyMinutes = playerJson.getInt("penaltyMinutes");
        Player player = new Player(name, age, position);
        for (int i = 1; i <= goals; i++) {
            player.addGoal();
        }
        for (int i = 1; i <= assists; i++) {
            player.addAssist();
        }
        player.addPenaltyMinutes(penaltyMinutes);
        team.addPlayer(player);
    }

    // MODIFIES: League
    // EFFECTS: parses goalies from JSON object and adds it to League
    private void addGoalies(Team team, JSONObject teamJson) {
        JSONArray jsonArrayGoalies = teamJson.getJSONArray("goalies");
        for (Object jsonGoalie : jsonArrayGoalies) {
            JSONObject nextGoalie = (JSONObject) jsonGoalie;
            addGoalie(team, nextGoalie);
        }
    }

    // MODIFIES: League
    // EFFECTS: parses goalie from JSON object and adds it to League
    private void addGoalie(Team team, JSONObject goalieJson) {
        String name = goalieJson.getString("name");
        int age = goalieJson.getInt("age");
        int shotsAgainst = goalieJson.getInt("shotsAgainst");
        int goalsAgainst = goalieJson.getInt("goalsAgainst");
        int wins = goalieJson.getInt("wins");
        Goalie goalie = new Goalie(name, age);
        for (int i = 1; i <= shotsAgainst; i++) {
            goalie.addShotAgainst();
        }
        for (int i = 1; i <= goalsAgainst; i++) {
            goalie.addGoalAgainst();
        }
        for (int i = 1; i <= wins; i++) {
            goalie.addWin();
        }
        team.addGoalie(goalie);
    }


}
