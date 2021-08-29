package PathFindingTest;

import Characters.PathFinding.Position;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <H1>Test the position object</H1>
 * Tests the position class functionalities
 */
public class PositionTest {



    /**
     * Tests the creation of position objects
     */
    @Test
    public void testObjectCreationPositions(){
        Position position = new Position(2,3);
        assertEquals(2,position.getPosition_X());
        assertEquals(3,position.getPosition_Y());
        assertEquals("2,3", position.convertPositionsToString());
    }

    /**
     * Test the conversion functionality of Positions objects
     */
    @Test
    public void covertPositionIntegerOnMapTests(){
        Position position = new Position(4,5);
        assertEquals(120,position.getPosition_X_Integer());
        assertEquals(150,position.getPosition_Y_Integer());
    }

}
