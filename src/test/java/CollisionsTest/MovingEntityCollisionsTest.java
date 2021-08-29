package CollisionsTest;

import CollisionManager.Collisions;
import Map.GameMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.Collection;

/**
 * <H1>Test the moving entities collision</H1>
 * Testing the collisions between Moving Entity with walls/Barriers
 */
@RunWith(Parameterized.class)
public class MovingEntityCollisionsTest {

    private GameMap map = new GameMap();
    private Collisions collisions = new Collisions(map);
    private boolean result; // It must return false when there is a collision and true when there is not
    private boolean expectedResult;
    private static boolean beforeIsDone = false;

    @Before
    public void setUpCollisions(){
        if(beforeIsDone){
            return;
        }
        else {
            collisions = new Collisions(this.map);
            beforeIsDone = true;
        }
    }

    /**
     *  Tests if the moving entities on the map collides with walls
     * @param position_X_Entity stores the x position of an entity object
     * @param position_Y_Entity stores the y position of an entity object
     * @param expectedResult stores a boolean variable, which determines if the result is what we expect
     */
    public MovingEntityCollisionsTest(double position_X_Entity, double position_Y_Entity, boolean expectedResult) {
        result = collisions.checkForCollision(position_X_Entity,position_Y_Entity);
        this.expectedResult = expectedResult;
    }

    /**
     * Generate different positions for the main character and check if its detected to hit Wall/Barrier or not
     * @return array list of parameters of positions of the character and the result that we expect to get from the function
     */
    @Parameterized.Parameters
    public static Collection positions(){
        return Arrays.asList(new Object[][]{
                {30,60,true}, // Setting the position of the character, which it does not hit a wall/Barrier
                {180, 190, true},
                {330, 126, true},
                {115, 90, false}, // Testing when moving entity is coming to the wall/obstacle from right to left
                {180,95,false} , // Testing when moving entity is coming to the wall/obstacle from up to down
                {243,185,false}, // Testing when moving entity is coming to the wall/obstacle from left to right
                {120,115,false},  // Testing when moving entity is coming to the wall/obstacle from down to up
                {-1,-5,false}  // Position out of bounds
        });
    }

    /**
     * Test to see if the results are equal to the expected value
     */
    @Test
    public void checkWall_Moving_Entity_Collisions(){
        assertEquals(result,expectedResult);
    }
}
