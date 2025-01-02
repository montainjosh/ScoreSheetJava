package persistence;

import model.League;
import model.Team;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFileDNE() {
        JsonReader reader = new JsonReader("./data/LOL.json");
        try {
            League myLeague = reader.readLeague();
            fail("expected fail");
        } catch (IOException e) {
            // do nothing
        }
    }

    @Test
    void testReaderEmptyLeague() {
        JsonReader reader = new JsonReader("./data/emptyLeague.json");
        try {
            League myLeague = reader.readLeague();
            assertEquals("My League", myLeague.getName());
            List<Team> teams = myLeague.getTeams();
            assertEquals(0, teams.size());
        } catch (IOException e) {
            fail("Error reading from file");
        }
    }

    @Test
    void testReader() {
        JsonReader reader = new JsonReader("./data/sampleLeague.json");
        try {
            League myLeague = reader.readLeague();
            assertEquals("My League", myLeague.getName());
            List<Team> teams = myLeague.getTeams();
            assertEquals(2, teams.size());
            Team team1 = teams.get(0);
            Team team2 = teams.get(1);
            checkTeamOne("Canucks", team1.getPlayers(), team1.getGoalies(), 0, 1, team1);
            checkTeamTwo("Flames", team2.getPlayers(), team2.getGoalies(), 1, 0, team2);
        } catch (IOException e) {
            fail("Error reading from file");
        }
    }
}