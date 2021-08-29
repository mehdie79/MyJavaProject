package CollisionsTest;

import Characters.MovingEntities.MainCharacter;
import CollisionManager.Collisions;
import Map.GameMap;

import StaticEntities.RegularReward;
import StaticEntities.StaticEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * <H1>Test the static entities collision</H1>
 * Check collision detecting method to ensure that collisions with static entities
 * are properly detected
 */

@RunWith(Parameterized.class)
public class StaticEntityCollisionsTest {
    private Collisions collision;
    private GameMap map = new GameMap();
    private MainCharacter player;
    private StaticEntity entity;
    private boolean result;

    @Before
    public void initialize(){
        collision = new Collisions(map);
    }

    /**
     * This will set up the position of the main character and static entity object while setting up the expected result
     * @param player_X stores the x position of the player
     * @param player_Y stores the y position of the player
     * @param static_X stores the x position of the static entity
     * @param static_Y stores the y position of the static entity
     * @param expected stores the boolean for checking the functionality
     * */
    public StaticEntityCollisionsTest(double player_X, double player_Y, float static_X, float static_Y, boolean expected){
        entity = new RegularReward (static_X, static_Y);
        player = new MainCharacter();
        player.setPosition(player_X, player_Y);
        this.result = expected;
    }

    /**
     * Generate different positions for the main character and check if its detected to hit static entities or not
     * @return array list of parameters of positions of the character and the result that we expect to get from the function
     */
    @Parameterized.Parameters
    public static Collection positions(){
        return Arrays.asList(new Object[][]{
            {35, 35, 35, 35, true},
            {180, 190, 300, 400, false},
            {48, 159, 48, 300, false},
            {289, 123, 34, 123, false}
        });
    }

    /**
     * Only one static entity tested because every static entity behaves in the same
     * manner and therefore if one static entity is detected correctly in every scenario
     * then naturally the other static entities will also be detected correctly
     */
    @Test
    public void testStaticEntityCollisions(){
        assertEquals(result, collision.checkStaticEntityCollision(entity, player));
    }
}
