package model;

import org.json.JSONObject;
import persistence.Writable;
// represents a goalie with a name, age, and amounts of shots against, goals against, and wins

public class Goalie implements Writable {
    private final String name;
    private final int age;
    private int shotsAgainst;
    private int goalsAgainst;
    private int wins;

    // REQUIRES: age > 0, name has a non-zero length
    // EFFECTS: constructs a goalie with given name and age, and 0 shotsAgainst, goalsAgainst, and wins
    public Goalie(String name, int age) {
        this.name = name;
        this.age = age;
        shotsAgainst = 0;
        goalsAgainst = 0;
        wins = 0;
        EventLog.getInstance().logEvent(new Event("New Goalie " + name + " Made"));
    }

    // MODIFIES: this
    // EFFECTS: adds one shot against to goalie's shotsAgainst total
    public void addShotAgainst() {
        this.shotsAgainst++;
        EventLog.getInstance().logEvent(new Event("Shot Against added to " + name));
    }

    // MODIFIES: this
    // EFFECTS: adds one goal against to goalie's goalsAgainst total
    public void addGoalAgainst() {
        this.goalsAgainst++;
        EventLog.getInstance().logEvent(new Event("Goal Against added to " + name));
    }

    // MODIFIES: this
    // EFFECTS: adds one win to goalie's wins total
    public void addWin() {
        this.wins++;
        EventLog.getInstance().logEvent(new Event("Win added to " + name));
    }

    // EFFECTS: returns Goalie as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonGoalie = new JSONObject();
        jsonGoalie.put("name", name);
        jsonGoalie.put("age", age);
        jsonGoalie.put("shotsAgainst", shotsAgainst);
        jsonGoalie.put("goalsAgainst", goalsAgainst);
        jsonGoalie.put("wins", wins);
        return jsonGoalie;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getShotsAgainst() {
        return shotsAgainst;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getWins() {
        return wins;
    }

}
