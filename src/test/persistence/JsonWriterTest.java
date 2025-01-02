package persistence;

import static org.junit.jupiter.api.Assertions.*;

import model.Team;
import model.Player;
import model.Goalie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import model.League;

class JsonWriterTest {

    @Test
    void testWriteInvalidFile() {
        try {
            League myLeague = new League("My League");
            JsonWriter writer = new JsonWriter("./data/LOL\n.json");
            writer.openWriter();
            fail("expected fail");
        } catch (IOException e) {
            // do nothing
        }
    }

    @Test
    void testWriteEmptyLeague() {
        try {
            League myLeague = new League("My League");
            JsonWriter writer = new JsonWriter("./data/testLeagueEmpty.json");
            writer.openWriter();
            writer.write(myLeague);
            writer.closeWriter();
            JsonReader reader = new JsonReader("./data/testLeagueEmpty.json");
            League myLeagueTest = reader.readLeague();
            assertEquals("My League", myLeagueTest.getName());
            List<Team> teams = myLeagueTest.getTeams();
            assertEquals(0, teams.size());
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @Test
    void testWriteSampleLeague() {
        try {
            League myLeague = setUpLeague();

            JsonWriter writer = new JsonWriter("./data/testLeague.json");
            writer.openWriter();
            writer.write(myLeague);
            writer.closeWriter();
            JsonReader reader = new JsonReader("./data/testLeague.json");
            League myLeagueTest = reader.readLeague();
            assertEquals("My League", myLeagueTest.getName());
            List<Team> teams = myLeagueTest.getTeams();
            Team team = teams.get(0);
            assertEquals("Nerds", team.getName());
            assertEquals(0, team.getWins());
            List<Player> players = team.getPlayers();
            List<Goalie> goalies = team.getGoalies();
            Player player = players.get(0);
            Goalie goalie = goalies.get(0);
            assertEquals("Paul", player.getName());
            assertEquals("Carter", goalie.getName());
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    League setUpLeague() {
        League myLeague = new League("My League");
        Player player = new Player("Paul", 18, "forward");
        Goalie goalie = new Goalie("Carter", 18);
        Team team = new Team("Nerds");
        team.addPlayer(player);
        team.addGoalie(goalie);
        myLeague.addTeam(team);

        return myLeague;
    }
}