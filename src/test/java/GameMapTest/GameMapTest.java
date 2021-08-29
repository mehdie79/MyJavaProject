package GameMapTest;

import Characters.MovingEntities.Enemy;
import Characters.MovingEntities.MainCharacter;
import Map.GameMap;
import StaticEntities.BonusRewards;
import StaticEntities.RegularReward;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * <H1>Test the Game map</H1>
 * This class will test if the players score gets updated by colliding with static entities on the map while checking the player winning and
 * losing scenarios functionalities.
 */
public class GameMapTest {
    private GameMap map;
    private MainCharacter player;
    private List<RegularReward> rewards;
    private List<BonusRewards> bonuses;
    private List<Enemy> enemies;

    @Before
    public void initialize() {
        map = new GameMap();
        player = map.getPlayer();
        rewards = map.getRewards();
        bonuses = map.getBonuses();
        enemies = map.getEnemies();
    }

    /**
     * Ensure that the player's score is correspondingly updated as collision
     * between player and static entity is detected
     */
    @Test
    //testing all static entity collisions, player score should be updated
    public void integrationCheckCollisionEntitiesTest(){
        //regular reward
        player.setPosition(179, 117); //collides with regular reward
        map.checkCollisionRegularAward();
        assertEquals(10, map.getPlayerScore());

        //punishment
        player.setPosition(299, 390); //collides with punishment
        map.checkCollisionPunishment();
        assertEquals(0, map.getPlayerScore());

        //bonus reward
        player.setPosition(213, 270); //does not collide with bonus reward
        map.setCounts(2, 1);
        map.setNumberOfBonusRewardAtTime(0);
        map.checkCollisionBonusAward();
        assertEquals(0, map.getPlayerScore());

        player.setPosition(213, 270);           //collides with bonus reward
        map.setCounts(2, 1);                                      //returns true
        map.setNumberOfBonusRewardAtTime(1);                      //returns true (existedBonusReward())
        map.setBonusAwardPosition(0);                             //set position to be used for the time interval
        map.checkCollisionBonusAward();
        assertEquals(20, map.getPlayerScore());

        bonuses.clear();                                           //no more bonus rewards
        map.checkCollisionBonusAward();
        assertEquals(20, map.getPlayerScore());

    }

    @Test
    public void integrationAllRegularRewardCollectedTest(){
        assertFalse(map.allRegularRewardCollected());

        rewards.clear();
        assertTrue(map.allRegularRewardCollected());
    }

    /**
     * Returns true only if the player has collected all the rewards and is
     * standing on the finish sign
     */
    @Test
    public void integrationPlayerWinningTest(){
        player.setPosition(289, 123); //both conditions false
        assertFalse(map.playerWinning()); //did not collect all rewards and not at finish sign

        player.setPosition(100, 100); //first true second false
        assertFalse(map.playerWinning()); //collected all rewards but not at finish sign

        player.setPosition(840, 900); //first false second true
        assertFalse(map.playerWinning()); //did not collect all rewards but at finish sign

        rewards.clear();                                //both conditions true
        assertTrue(map.playerWinning()); //collected all rewards and at finish sign

    }

    /**
     * Ensuring that the method being tested returns true if the player hits an enemy
     * or has its score fall below zero indicating that the player has lost
     */
    @Test
    public void integrationGameOverTest(){
        enemies.clear();
        enemies.add(new Enemy(500, 500));     //both false
        map.setPlayerScore(10);
        player.setPosition(10, 10);
        assertFalse(map.gameOver());

        map.setPlayerScore(-1);                                 //one or is true
        assertTrue(map.gameOver());

        enemies.get(0).setPosition(120, 120); //other or is true
        player.setPosition(120, 120);
        assertTrue(map.gameOver());
    }
}

