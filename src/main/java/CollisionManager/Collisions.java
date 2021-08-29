package CollisionManager;
import Characters.MovingEntities.Enemy;
import Characters.MovingEntities.MainCharacter;
import Map.GameMap;
import Obstacles.Barrier;
import StaticEntities.StaticEntity;
import java.util.List;


/**
 * <H1>Collision Detection</H1>
 * The Collisions program implements an application that catches the collision between entities of the game
 *  which returns either true or false.
 *
 */

public class Collisions {
    private static boolean flag_x = false;
    private static boolean flag_y = false;
    private static int size = 30;
    private GameMap map;
    private List<Barrier> obstacles;
    private List<Enemy> enemies;
    private int[][] mapf;
    private int[][] mapData;

    /**
     * This will construct a collision object based on the entities that have been defined on the game map class.
     * @param map This contains the entities data, which is defined on the game
     */
    public Collisions(GameMap map){
        this.map = map;
        obstacles =map.getObstacles();
        enemies = map.getEnemies();
        mapf = map.getTiles();
       mapData = placeBarriers(obstacles, mapf); // creates a whole new map with the barriers and walls acting as walls
    }

    /**
     * This function will return the 2D array, which contains the information about walls and dirt tiles.
     * @return the game map data
     */
    public int[][] getMapData(){
        return mapData;
    }

    /**
     * It returns the size of the map data array.
     * @return the size of the 2D array map data
     */
    public int getSize(){
        return size;
    }
    /**
     * Returns a boolean that determines whether there has been a collision or not.
     * <p>
     *     This method is used to check the collision between a reward and the player. First
     *     there is a check for whether or not there is a collision in x axis, meaning the
     *     reward and player have the overlapping y coordinates. The same is checked for the y axis.
     *     If both return true then there has been a collision and the method will return true.
     * </p>
     *
     * @param object1 the static entity on the game board
     * @param player the moving main character
     * @return the collision between the static entity and main character
     */

    public boolean checkStaticEntityCollision(StaticEntity object1, MainCharacter player){
        flag_x = (object1.getPositionX() + object1.getWidth() >= player.getPositionX()
                && player.getPositionX() + player.getWidth() >= object1.getPositionX());

        flag_y = (object1.getPositionY() + object1.getHeight() >= player.getPositionY()
                && player.getPositionY() + player.getHeight() >= object1.getPositionY());

        return flag_x && flag_y;

    }

    /**
     * This will place the barriers ID on the map data 2D array.
     * @param obstacles This is the list of barriers, which is defined in the game map
     * @param mapf This is the 2D array, which contains the ID of tiles defined in the game map.
     * @return A new 2D array, which it also stores the ID's of the barriers
     */
    private static int[][] placeBarriers(List<Barrier> obstacles, int[][] mapf) {
        // make a whole new 2d array for the map and make it the same as the old map (to stop pass by reference)
        int[][] mapd = new int[mapf.length][mapf[0].length];
        for (int i = 0; i < mapf.length; i++) {
            for (int j = 0; j < mapf[0].length; j++) {
                mapd[j][i] = mapf[j][i];
            }
        }

        // make all the tiles of the barriers act like wall tiles
        for (int i = 0; i < obstacles.size(); i++) {
            mapd[(int) obstacles.get(i).getPositionX() / size][(int) obstacles.get(i).getPositionY() / size -1] = 1;
        }
        return mapd;
    }

    /**
     * This will check collision of walls and barriers with the position that the main character wants to move (in pixels).
     * @param positionX This is the position X of a moving entity object
     * @param positionY This is the position y of a moving entity object
     * @return false if the moving entity is either hit the wall tiles or it has moved out the scope of the game map
     */
    public boolean checkForCollision(double positionX, double positionY) {
        // map[column][row]

        // get the boarder of the pixels that will be our hitbox
        double top = positionY - 30;
        double bottom = positionY- 3 ;
        double left = positionX;
        double right = positionX + 16;

        try {
            // now we check if each of the 4 corners of the hitbox are touching a wall
            if (mapData[(int) left / size][(int) top / size] == 1
                    || mapData[(int) right / size][(int) top / size] == 1
                    || mapData[(int) left / size][(int) bottom / size] == 1
                    || mapData[(int) right / size][(int) bottom / size] == 1) {
                return false;
            }
        } // it will cause a segmentation fault if you go out of bounds
        catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * This will check if collision have been detected between the main character and enemies.
     * @param character This is the main character object stored on the game map
     * @return true if the main character hits the enemies and false otherwise
     */
    public boolean checkPlayerHitEnemy(MainCharacter character){
        for(int i=0; i< enemies.size(); i++){
            if( enemies.get(i).getPositionX() + enemies.get(i).getWidth() >= character.getPositionX()
                    && character.getPositionX() + character.getWidth() >= enemies.get(i).getPositionX()){
                if(enemies.get(i).getPositionY() + enemies.get(i).getHeight() >= character.getPositionY()
                        && character.getPositionY() + character.getHeight() >= enemies.get(i).getPositionY()){
                    return true;
                }
            }
        }
        return false;
    }

}