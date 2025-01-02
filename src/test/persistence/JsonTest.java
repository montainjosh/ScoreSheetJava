package persistence;

import model.Player;
import model.Goalie;
import model.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class JsonTest {

    protected void checkTeamOne(String name, List<Player> players, List<Goalie> goalies, int wins, int losses,
                                Team team) {
        assertEquals(name, team.getName());
        assertEquals(wins, team.getWins());
        assertEquals(losses, team.getLosses());
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Goalie goalie1 = goalies.get(0);
        Goalie goalie2 = goalies.get(1);
        checkPlayer("Gregor", 18, "defence", 1, 0, 0, player1);
        checkPlayer("Humpty", 18, "forward", 0, 0, 4, player2);
        checkGoalie("Carter", 18, 0, 0, 0, goalie1);
        checkGoalie("Wall", 18, 30, 2, 0, goalie2);
    }

    protected void checkTeamTwo(String name, List<Player> players, List<Goalie> goalies, int wins, int losses,
                                Team team) {
        assertEquals(name, team.getName());
        assertEquals(wins, team.getWins());
        assertEquals(losses, team.getLosses());
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Goalie goalie1 = goalies.get(0);
        Goalie goalie2 = goalies.get(1);
        checkPlayer("Geoff", 18, "forward", 0, 1, 2, player1);
        checkPlayer("Patrice", 18, "defence", 2, 0, 0, player2);
        checkGoalie("Elisa", 18, 0, 0, 0, goalie1);
        checkGoalie("Dumpty", 18, 25, 1, 1, goalie2);
    }

    protected void checkPlayer(String name, int age, String position, int goals, int assists, int penaltyMinutes,
                               Player player) {
        assertEquals(name, player.getName());
        assertEquals(age, player.getAge());
        assertEquals(position, player.getPosition());
        assertEquals(goals, player.getGoals());
        assertEquals(assists, player.getAssists());
        assertEquals(penaltyMinutes, player.getPenaltyMinutes());
    }

    protected void checkGoalie(String name, int age, int shotsAgainst, int goalsAgainst, int wins, Goalie goalie) {
        assertEquals(name, goalie.getName());
        assertEquals(age, goalie.getAge());
        assertEquals(shotsAgainst, goalie.getShotsAgainst());
        assertEquals(goalsAgainst, goalie.getGoalsAgainst());
        assertEquals(wins, goalie.getWins());
    }

}
