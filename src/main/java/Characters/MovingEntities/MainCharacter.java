package Characters.MovingEntities;

import CollisionManager.Collisions;
import LoadingResources.ImageManager;
import Map.GameMap;

import java.awt.*;

/**
 * <H1>Creating a Main Character</H1>
 * The program Main Character implements all necessary components of the main character including size,
 * speed, distance, collision with walls and barriers.
 */

public class MainCharacter extends Character {
    private static final int MAIN_CHARACTER_WIDTH = 20;
    private static final int MAIN_CHARACTER_HEIGHT = 28;
    private static final double MAIN_CHARACTER_SPEED = 4;
    private static final int startPosition_x =30;
    private static final int startPosition_y = 60;


    private Collisions collisions;

    /**
     * This will construct a MainCharacter object with predefined speed, position, width, and height.
     */
    public MainCharacter() {
        super(startPosition_x, startPosition_y , MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);// 32 35 -> 1 1  29 35 -> 0 1
        this.speed = MAIN_CHARACTER_SPEED;
    }


    /**
     * This method will based on the input of the keyboard from the user moves the MainCharacter entity in the map while checking
     * for collisions with walls and barriers placed on Game Map.
     */
    public void move(){
        if(GameMap.getKeyInput().goRight() && collisions.checkForCollision( position_x + speed, position_y)) {
            position_x += speed;
        }
        else if(GameMap.getKeyInput().goLeft() && collisions.checkForCollision(position_x - speed, position_y)) {
            position_x -= speed;
        }
        else if(GameMap.getKeyInput().goUp() && collisions.checkForCollision(position_x, position_y - speed) && (position_y > startPosition_y)) {
            position_y -= speed;
        }
        else if(GameMap.getKeyInput().goDown() && collisions.checkForCollision(position_x, position_y + speed)) {
            position_y += speed;
        }
    }


    /**
     * This method renders the main character based on its position, width, and height on the map.
     * @param g is for rendering the character in each tick on the map
     */
    @Override
    public void render(Graphics g) {
        if (GameMap.getKeyInput().goRight()) {
            g.drawImage(ImageManager.playerRight,(int) this.getPositionX(),(int) this.getPositionY(),width,height,null);
        }
        else if(GameMap.getKeyInput().goLeft()) {
            g.drawImage(ImageManager.playerLeft,(int) this.getPositionX(),(int) this.getPositionY(),width,height,null);
        }
        else if(GameMap.getKeyInput().goUp()) {
            g.drawImage(ImageManager.playerUp,(int) this.getPositionX(),(int) this.getPositionY(),width ,height ,null);
        }
        else if(GameMap.getKeyInput().goDown()){
            g.drawImage(ImageManager.playerDown,(int) this.getPositionX(),(int) this.getPositionY(),width,height,null);
        }
        else
        {
            g.drawImage(ImageManager.playerdefault,(int) this.getPositionX(),(int) this.getPositionY(),width,height,null);
        }

    }

    /**
     * This method returns the current x position that the main character is placed on the map in double.
     * @return position X of the main character on the map
     */
    @Override
    public double getPositionX() {
        return super.getPositionX();
    }

    /**
     * This method returns the current y position that the main character is placed on the map in double.
     * @return position Y of the main character on the map
     */
    @Override
    public double getPositionY() {
        return super.getPositionY();
    }

    /**
     * This method is for setting the collision functionality for the character for detecting collisions with
     * walls and barriers.
     * @param collisions sets the collision object to the Main character's collision variable
     */
    public void setCollisions (Collisions collisions){
        this.collisions = collisions;
    }

    /**
     * This method in each tick of the game checks for user input from keyboard to make sure that the main character
     * is moving in each tick
     */
    @Override
    public void tick() { move(); }
}
