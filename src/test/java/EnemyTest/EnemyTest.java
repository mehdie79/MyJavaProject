package EnemyTest;

import Characters.MovingEntities.Enemy;
import Characters.PathFinding.PathFinding;
import CollisionManager.Collisions;
import Map.GameMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * <H1>Test the Enemy movement</H1>
 * Checks the movements of enemies based on its given direction
 */

public class EnemyTest {
    // Setting up the map in order to test the enemies movement
    private Enemy enemy;
    private GameMap map;
    private PathFinding pathFinding = new PathFinding();
    private Collisions collisions;
    private String result;

    /**
     * initializes the needed classes for testing the movements of enemy object
     */
    @Before
    public void setUp(){
        map = new GameMap();
        collisions = new Collisions(map);
        pathFinding = new PathFinding();
        pathFinding.setCollision(collisions);
    }

    /**
     * Tests when the enemy has to go to the up direction
     */
    @Test
    public void testUpMovementEnemy(){

        enemy = new Enemy(30,150);
        setEnemyPathFinding(enemy);
        enemy.setCharacterPositions(30,90);  // Enemy must move in up direction
        enemy.tick();
        result = enemy.getDirection();
        assertEquals("up",result);

    }

    // Sets the pathFinding functionality of the enemy
    private void setEnemyPathFinding(Enemy enemy) {
        enemy.setPathFinding(pathFinding);
    }
    /**
     * Tests when the enemy has to go to the down direction
     */
    @Test
    public void testDownMovementEnemy(){
        enemy = new Enemy(30,120);
        setEnemyPathFinding(enemy);
        enemy.setCharacterPositions(30,150);  // Enemy must move in down direction
        enemy.tick();
        result = enemy.getDirection();
        assertEquals("down",result);

    }
    /**
     * Tests when the enemy has to go to the right direction
     */
    @Test
    public void testRightMovementEnemy(){
        enemy = new Enemy(30,60);
        setEnemyPathFinding(enemy);
        enemy.setCharacterPositions(120,60);  // Enemy must move in right direction
        enemy.tick();
        result = enemy.getDirection();
        assertEquals("right",result);

    }
    /**
     * Tests when the enemy has to go to the left direction
     */
    @Test
    public void testLeftMovementEnemy(){
        enemy = new Enemy(150,150);
        setEnemyPathFinding(enemy);
        enemy.setCharacterPositions(90,150);  // Enemy must move in left direction
        enemy.tick();
        result = enemy.getDirection();
        assertEquals("left",result);

    }

}
