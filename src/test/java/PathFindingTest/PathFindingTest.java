package PathFindingTest;

import Characters.PathFinding.PathFinding;
import Characters.PathFinding.Position;
import CollisionManager.Collisions;
import Map.GameMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * <H1>Test the path finding functionality</H1>
 * Tests the pathfinding class functionality by generating different positions and checks if the expected path has been found or not
 */
@RunWith(Parameterized.class)
public class PathFindingTest {
    private GameMap map = new GameMap();
    private Collisions collisions = new Collisions(map);
    private PathFinding pathFind = new PathFinding();
    private LinkedList<Position> path = new LinkedList<>();
    private Position expectedTargetPosition;
    private Position resultedTargetPosition;
    private boolean flag;   // True when there is a path and false when there is no path
    // Checks if it finds a path to the target
    public PathFindingTest(double positionX, double positionY, double targetPosition_X, double targetPosition_Y, boolean flag){
        pathFind.setCollision(collisions);
        this.pathFind.findPath(positionX,positionY,targetPosition_X,targetPosition_Y);
        this.path = pathFind.getPath();
        setTheResult();
        // set the target based on the tiles on the GameMap
        setExpectedTargetInTiles(targetPosition_X,targetPosition_Y);
        this.flag = flag;
    }

    private void setExpectedTargetInTiles(double targetPosition_X, double targetPosition_Y) {
        this.expectedTargetPosition= new Position((int) targetPosition_X / collisions.getSize(),(int) targetPosition_Y / collisions.getSize());
    }


    private void setTheResult() {
        // if it did not find any path to the target
       if(path == null){
           this.resultedTargetPosition = null;
       } else
            this.resultedTargetPosition = path.get(path.size()-1);
    }

    /**
     * If the path has found set the resultPathFind to true if there is no path set it to false
     * @return true if path has found and false otherwise
     */
    public boolean testIfFindsThePath() {
        if(resultedTargetPosition == null){
            return false;
        } else {
            if (resultedTargetPosition.getPosition_X() == expectedTargetPosition.getPosition_X()) {
                if (resultedTargetPosition.getPosition_Y() == expectedTargetPosition.getPosition_Y()) {
                    return true;
                } else
                    return false;
            } else
                return false;
        }
    }


    /**
     * This method will set the positions for testing if the path has been founded or not
     * @return an array list of object for generating different test cases
     */
    @Parameterized.Parameters
    public static Collection positions(){
        return Arrays.asList(new Object[][]{
                {60.7, 60.3, 180.2, 210.7, true} , {90.2, 150.5, 300, 480, true},   // setting the positions in way that it finds the path
               {30.4, 180.2, 150, 30, false}, {240.5, 240.1, 635.5, 156.3, false} // setting the positions when the target is a wall tile
        });
    }

    /**
     * This method checks if the path found is equal to the expected results
     */
    @Test
    public void testThePathFinding(){
        assertEquals(testIfFindsThePath(),flag);
    }

}
