package model;

import org.json.JSONObject;
import persistence.Writable;
// represents a player with a name, age, position, and amounts of goals, assists, and penalty minutes

public class Player implements Writable {
    private final String name;
    private final int age;
    private final String position;
    private int goals;
    private int assists;
    private int penaltyMinutes;

    // REQUIRES: age > 0, name and position has a non-zero length
    // EFFECTS: constructs a new player with given name, age, position, and 0 goals, assists, and penalty minutes
    public Player(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
        goals = 0;
        assists = 0;
        penaltyMinutes = 0;
        EventLog.getInstance().logEvent(new Event("New Player " + name + " Made"));
    }

    // MODIFIES: this
    // EFFECTS: adds one goal to player's goal total
    public void addGoal() {
        this.goals++;
        EventLog.getInstance().logEvent(new Event("Goal added to " + name));

    }

    // MODIFIES: this
    // EFFECTS: adds one assist to player's assist total
    public void addAssist() {
        this.assists++;
        EventLog.getInstance().logEvent(new Event("Assist added to " + name));
    }

    // REQUIRES: minutes > 0
    // MODIFIES: this
    // EFFECTS: adds minutes to player's penaltyMinute total
    public void addPenaltyMinutes(int minutes) {
        this.penaltyMinutes += minutes;
        EventLog.getInstance().logEvent(new Event(minutes + " Penalty Minutes added to " + name));
    }

    // EFFECTS: returns Player as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonPlayer = new JSONObject();
        jsonPlayer.put("name", name);
        jsonPlayer.put("age", age);
        jsonPlayer.put("position", position);
        jsonPlayer.put("goals", goals);
        jsonPlayer.put("assists", assists);
        jsonPlayer.put("penaltyMinutes", penaltyMinutes);
        return jsonPlayer;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getPenaltyMinutes() {
        return penaltyMinutes;
    }
}
