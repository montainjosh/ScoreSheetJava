package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeagueTest {
    private League myLeague;
    private Team testTeam1;
    private Team testTeam2;
    private Team testTeam3;

    @BeforeEach
    void runBefore() {
        myLeague = new League("My league");
        testTeam1 = new Team("Nerds");
        testTeam2 = new Team("Giga Nerds");
        testTeam3 = new Team("Turbo Nerds");
        myLeague.addTeam(testTeam1);
        myLeague.addTeam(testTeam2);
        myLeague.addTeam(testTeam3);
    }

    @Test
    void testAddTeam() {
        List<Team> leagueTeams = myLeague.getTeams();
        assertEquals(3, leagueTeams.size());
        assertEquals(testTeam2, leagueTeams.get(1));
    }

    @Test
    void testFindTeam() {
        assertEquals(testTeam1, myLeague.findTeam("Nerds"));
        assertEquals(testTeam3, myLeague.findTeam("Turbo Nerds"));

    }
    @Test
    void testName() {
        assertEquals("My league", myLeague.getName());
    }
}