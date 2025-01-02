package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreSheetTest {
    private ScoreSheet testScoreSheet;
    private Team testTeam1;
    private Team testTeam2;
    @BeforeEach
    void runBefore() {
        testTeam1 = new Team("Nerds");
        testTeam2 = new Team("Giga Nerds");
        testScoreSheet = new ScoreSheet(testTeam1, testTeam2);
    }

    @Test
    void testConstructor() {
        assertEquals(testTeam1, testScoreSheet.getTeam1());
        assertEquals(testTeam2, testScoreSheet.getTeam2());
        assertEquals(0, testScoreSheet.getTeam1Score());
        assertEquals(0, testScoreSheet.getTeam2Score());
    }

    @Test
    void testFindTeam() {
        assertEquals(testTeam1, testScoreSheet.findTeam("Nerds"));
        assertEquals(testTeam2, testScoreSheet.findTeam("Giga Nerds"));
    }


    @Test
    void testAddGoalTeam1() {
        testScoreSheet.addGoalTeam1();
        testScoreSheet.addGoalTeam1();
        assertEquals(2,testScoreSheet.getTeam1Score());
    }

    @Test
    void testAddGoalTeam2() {
        testScoreSheet.addGoalTeam2();
        testScoreSheet.addGoalTeam2();
        assertEquals(2,testScoreSheet.getTeam2Score());
    }

}