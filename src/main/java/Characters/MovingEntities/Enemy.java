package Characters.MovingEntities;
import Characters.PathFinding.PathFinding;
import CollisionManager.Collisions;
import LoadingResources.ImageManager;

import java.awt.*;



/**
 * <H1>Creating an Enemy</H1>
 * The program Enemy implements all necessary components of the enemy including size,
 * speed, distance from the main character, and movement.
 */

public class Enemy extends Character {

    private static final int ENEMY_WIDTH = 28;
    private static final int ENEMY_HEIGHT = 30;
    private static final double ENEMY_SPEED = 2;

    private double character_positionX;
    private double character_positionY;
    private PathFinding pathFinding;
    private Collisions collisions;

    private String direction = "";

    /**
     * This will constructs an enemy object on the map based on the defined position x and y.
     * @param position_x This will store the position x of an enemy object
     * @param position_y This will store the position y of an enemy object
     */
    public Enemy(double position_x, double position_y) {
        super(position_x, position_y, ENEMY_WIDTH, ENEMY_HEIGHT);
        this.speed = ENEMY_SPEED;
    }


    /**
     * This method will check based on the going enemy position whether it has to go up or not.
     * @return true if the enemy has to go up and false otherwise
     */
    public boolean goUp(){
        if((int) position_x == pathFinding.getGoing_enemy_position().getPosition_X_Integer()
                && (int) position_y > pathFinding.getGoing_enemy_position().getPosition_Y_Integer()){
            direction = "up";
            return true;
        }
        else
            return false;

    }

    /**
     *This method will check based on the going enemy position whether it has to go down or not.
     * @return true if the enemy has to go down and false otherwise
     */
    public boolean goDown(){
        if((int) position_x == pathFinding.getGoing_enemy_position().getPosition_X_Integer()
                && (int) position_y < pathFinding.getGoing_enemy_position().getPosition_Y_Integer()){
            direction = "down";
            return true;
        }
        else
            return false;
    }

    /**
     * This method will check based on the going enemy position whether it has to go right or not.
     * @return true if the enemy has to go right and false otherwise
     */
    public boolean goRight(){
        if((int) position_x <pathFinding.getGoing_enemy_position().getPosition_X_Integer()
                && (int) position_y== pathFinding.getGoing_enemy_position().getPosition_Y_Integer()){
            direction = "right";
            return true;
        }
        else
            return false;

    }

    /**
     * This method will check based on the going enemy position whether it has to go left or not.
     * @return true if the enemy has to go left and false otherwise
     */
    public boolean goLeft(){
        if((int) position_x > pathFinding.getGoing_enemy_position().getPosition_X_Integer()
                && (int) position_y == pathFinding.getGoing_enemy_position().getPosition_Y_Integer()){
            direction = "left";
            return true;
        }
        else
            return false;
    }


    /**
     * This method on each tick based on the going enemy position does the following things:
     *  1) Checks which direction (up/down/left/right) the enemy has to move.
     *  2) Based on the define speed and direction it will move the enemy object.
     */
    public void move() {

        if(pathFinding.getGoing_enemy_position() == null){
            return;
       }
        if(goRight()) {
            this.position_x += speed;
        }
        else if(goLeft()) {
            this.position_x -= speed;
        }
        else if(goUp()) {
            this.position_y -= speed;
        }
        else if(goDown()) {
            this.position_y += speed;
        }
    }


    /**
     * It will render the enemy object on the game map based on its position, width, height, and the direction of its movement.
     * @param g This will paint the enemy object on the game map
     */
    @Override
    public void render(Graphics g) {
        if(pathFinding.getGoing_enemy_position() == null){
            return;
        }
        if (goRight()) {
            g.drawImage(ImageManager.enemyRight, (int) this.getPositionX(), (int) this.getPositionY(), width, height, null);
        } else if (goLeft()) {
            g.drawImage(ImageManager.enemyLeft, (int) this.getPositionX(), (int) this.getPositionY(), width, height, null);
        } else if (goUp()) {
            g.drawImage(ImageManager.enemyUp, (int) this.getPositionX(), (int) this.getPositionY(), width, height, null);
        } else if (goDown()){
            g.drawImage(ImageManager.enemyDown, (int) this.getPositionX(), (int) this.getPositionY(), width, height, null);
        }
        else
        {
             if(direction.equals("up")){
                g.drawImage(ImageManager.enemyUp, (int) this.getPositionX(), (int) this.getPositionY(), width, height, null);

            } else if(direction.equals("down")){
                g.drawImage(ImageManager.enemyDown, (int) this.getPositionX(), (int) this.getPositionY(), width, height, null);

            } else if(direction.equals("left")){
                g.drawImage(ImageManager.enemyLeft, (int) this.getPositionX(), (int) this.getPositionY(), width, height, null);
            } else
                g.drawImage(ImageManager.enemyRight, (int) this.getPositionX(), (int) this.getPositionY(), width, height, null);

        }

    }

    /**
     * This method will set the main character's x and y position since the enemy needs to find the shortest path based on the position
     * of the character.
     * @param character_positionX This is the main character position_x on the game map.
     * @param character_positionY This is the main character position_y on the game map.
     */
    public void setCharacterPositions(double character_positionX, double character_positionY){
        this.character_positionX = character_positionX;
        this.character_positionY = character_positionY;
    }


    /**
     *  It will return the current enemies direction, which is moving.
     * @return the direction of the enemies movement
     */
    public String getDirection(){
        return direction;
    }

    /**
     * This method will set the path finding functionality of an enemy for finding the shortest path
     * @param pathFinding This is the path finding object, which finds the shortest path for the enemy
     */
    public void setPathFinding(PathFinding pathFinding) {
        this.pathFinding = pathFinding;
    }

    /**
     * This method will set the collisions functionality of an enemy
     * @param collisions This is the collision functionality of an enemy object
     */
    public void setCollisions(Collisions collisions){
        this.collisions = collisions;
    }

    /**
     * This method will update the enemies movement on each tick based on the position of the main character
     */
    @Override
    public void tick() {
       pathFinding.findPath(this.position_x,this.position_y,character_positionX,character_positionY);
       move();
    }


}
