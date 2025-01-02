package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GoalieTest {
    private Goalie testGoalie;

    @BeforeEach
    void runBefore(){
        testGoalie = new Goalie("Gregor",19);
    }

    @Test
    void testConstructor(){
        assertEquals("Gregor",testGoalie.getName());
        assertEquals(19,testGoalie.getAge());
        assertEquals(0,testGoalie.getShotsAgainst());
        assertEquals(0,testGoalie.getGoalsAgainst());
        assertEquals(0,testGoalie.getWins());
    }

    @Test
    void testAddShotAgainst() {
        testGoalie.addShotAgainst();
        assertEquals(1,testGoalie.getShotsAgainst());
        testGoalie.addShotAgainst();
        testGoalie.addShotAgainst();
        assertEquals(3,testGoalie.getShotsAgainst());
    }

    @Test
    void testAddGoalAgainst() {
        testGoalie.addGoalAgainst();
        assertEquals(1,testGoalie.getGoalsAgainst());
        testGoalie.addGoalAgainst();
        testGoalie.addGoalAgainst();
        assertEquals(3,testGoalie.getGoalsAgainst());
    }

    @Test
    void testAddWin() {
        testGoalie.addWin();
        assertEquals(1,testGoalie.getWins());
        testGoalie.addWin();
        testGoalie.addWin();
        assertEquals(3,testGoalie.getWins());
    }
}