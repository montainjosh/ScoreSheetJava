package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private Team testTeam;
    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;
    private Goalie testGoalie1;
    private Goalie testGoalie2;

    @BeforeEach
    void runBefore() {
        testTeam = new Team("Nerds");
        testPlayer1 = new Player("Paul",18,"Center");
        testPlayer2 = new Player("Carter",18,"Left Wing");
        testPlayer3 = new Player("Geoff",18,"Right Wing");
        testGoalie1 = new Goalie("Gregor",19);
        testGoalie2 = new Goalie("Patrice", 19);
    }

    @Test
    void testConstructor() {
        assertEquals("Nerds", testTeam.getName());
        assertEquals(0, testTeam.getPlayers().size());
        assertEquals(0, testTeam.getGoalies().size());
        assertEquals(0, testTeam.getWins());
        assertEquals(0, testTeam.getLosses());
    }

    @Test
    void testAddPlayer() {
        testTeam.addPlayer(testPlayer1);
        assertEquals(testPlayer1, testTeam.getPlayers().get(0));
        testTeam.addPlayer(testPlayer2);
        testTeam.addPlayer(testPlayer3);
        assertEquals(testPlayer1, testTeam.getPlayers().get(0));
        assertEquals(testPlayer2, testTeam.getPlayers().get(1));
        assertEquals(testPlayer3, testTeam.getPlayers().get(2));

    }

    @Test
    void testRemovePlayer() {
        testTeam.addPlayer(testPlayer1);
        testTeam.addPlayer(testPlayer2);
        testTeam.addPlayer(testPlayer3);
        testTeam.removePlayer(testPlayer2);
        assertEquals(2,testTeam.getPlayers().size());
        assertEquals(testPlayer1,testTeam.getPlayers().get(0));
        assertEquals(testPlayer3,testTeam.getPlayers().get(1));
    }

    @Test
    void testAddGoalie() {
        testTeam.addGoalie(testGoalie1);
        assertEquals(testGoalie1, testTeam.getGoalies().get(0));
        testTeam.addGoalie(testGoalie2);
        assertEquals(testGoalie2, testTeam.getGoalies().get(1));
    }

    @Test
    void testRemoveGoalie() {
        testTeam.addGoalie(testGoalie1);
        testTeam.addGoalie(testGoalie2);
        testTeam.removeGoalie(testGoalie1);
        assertEquals(1, testTeam.getGoalies().size());
        assertEquals(testGoalie2, testTeam.getGoalies().get(0));
    }

    @Test
    void testFindPlayer() {
        testTeam.addPlayer(testPlayer1);
        testTeam.addPlayer(testPlayer2);
        testTeam.addPlayer(testPlayer3);
        assertEquals(testPlayer2, testTeam.findPlayer("Carter"));
        assertEquals(testPlayer1, testTeam.findPlayer("Paul"));
    }

    @Test
    void testFindGoalie() {
        testTeam.addGoalie(testGoalie1);
        testTeam.addGoalie(testGoalie2);
        assertEquals(testGoalie2, testTeam.findGoalie("Patrice"));
        assertEquals(testGoalie1, testTeam.findGoalie("Gregor"));
    }

    @Test
    void testAddWin() {
        testTeam.addWin();
        assertEquals(1, testTeam.getWins());
        testTeam.addWin();
        assertEquals(2, testTeam.getWins());
    }

    @Test
    void testAddLoss() {
        testTeam.addLoss();
        assertEquals(1,testTeam.getLosses());
        testTeam.addLoss();
        testTeam.addLoss();
        assertEquals(3,testTeam.getLosses());
    }
}