package Characters.MovingEntities;

import java.awt.*;

/**
 * <H1>Creating Characters</H1>
 * This program implements a character and its position, dimensions, and speed. There are
 * also methods for the character's collisions with each other, the wall, and barriers.
 */

public abstract class Character {
    protected double position_x;
    protected double position_y;
    protected int width;
    protected int height;
    protected double speed;

    /**
     * This will construct a character object based on the passed position, width, and height on the game map.
     * @param position_x This will store the position x of the moving entity object
     * @param position_y This will store the position y of the moving entity object
     * @param width This will define the width of the moving entity object
     * @param height This will define the height of moving entity object
     */
    public Character(double position_x, double position_y, int width, int height){
        this.position_x=position_x;
        this.position_y = position_y;
        this.width = width;
        this.height = height;

    }

    //setters

    /**
     * This will define the position x and y of the character object
     * @param position_x This will set the position x of the moving entity object
     * @param position_y This will set the position y of the moving entity object
     */
    public void setPosition(double position_x, double position_y){
        this.position_x = position_x;
        this.position_y = position_y;
    }

    //getters

    /**
     * This method will return the current position x of the character object on the game map
     * @return The position x of the moving entity object
     */
    public double getPositionX(){
        return position_x;
    }

    /**
     *This method will return the current position y of the character object on the game map
     * @return The position y of the moving entity object
     */
    public double getPositionY(){
        return position_y;
    }

    /**
     *This method will return width of the character object on the game map
     * @return The width of the moving entity object
     */
    public int getWidth(){
        return width;
    }

    /**
     *This method will return the height of the character object on the game map
     * @return The height of the moving entity object
     */
    public int getHeight(){
        return height;
    }

    /**
     * This method is responsible for rendering main character object on the screen
     * @param g This will draw the images on the screen
     */
    public abstract void render(Graphics g); // abstract

    /**
     * This method will update the position of the character object on the game map.
     */
    public abstract void tick(); // abstract

}
