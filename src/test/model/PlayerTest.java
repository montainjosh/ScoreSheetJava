package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player testPlayer;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player("Paul", 18, "Center");
    }

    @Test
    public void testConstructor() {
        assertEquals("Paul", testPlayer.getName());
        assertEquals(18, testPlayer.getAge());
        assertEquals("Center", testPlayer.getPosition());
        assertEquals(0, testPlayer.getGoals());
        assertEquals(0, testPlayer.getAssists());
        assertEquals(0, testPlayer.getPenaltyMinutes());
    }

    @Test
    public void testAddGoal() {
        testPlayer.addGoal();
        assertEquals(1, testPlayer.getGoals());
        testPlayer.addGoal();
        testPlayer.addGoal();
        assertEquals(3, testPlayer.getGoals());
    }

    @Test
    public void testAddAssist() {
        testPlayer.addAssist();
        assertEquals(1, testPlayer.getAssists());
        testPlayer.addAssist();
        testPlayer.addAssist();
        assertEquals(3, testPlayer.getAssists());
    }

    @Test
    public void testAddPenaltyMinutes() {
        testPlayer.addPenaltyMinutes(2);
        assertEquals(2, testPlayer.getPenaltyMinutes());
        testPlayer.addPenaltyMinutes(2);
        testPlayer.addPenaltyMinutes(5);
        assertEquals(9, testPlayer.getPenaltyMinutes());
    }
}

