package model;

// Is made when starting a game, has 2 teams and the score of each team
public class ScoreSheet {
    private Team team1;
    private Team team2;
    private int team1Score;
    private int team2Score;

    // EFFECTS: constructs scoresheet with two input teams, and both teams'
    //          win boolean value is false
    public ScoreSheet(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        team1Score = 0;
        team2Score = 0;
        EventLog.getInstance().logEvent(new Event("New ScoreSheet made between " + team1.getName() + " and "
                + team2.getName()));
    }

    // REQUIRES: string team name matches one of input team names
    // EFFECTS: returns Team associated with input team name
    public Team findTeam(String teamName) {
        if (teamName.equals(team1.getName())) {
            return team1;
        } else {
            return team2;
        }
    }

    public void addGoalTeam1() {
        team1Score++;
    }

    public void addGoalTeam2() {
        team2Score++;
    }

    public Team getTeam1() {
        return this.team1;
    }

    public Team getTeam2() {
        return this.team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }
}
