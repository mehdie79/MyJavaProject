package StaticEntities;


import java.awt.*;

/**
 * <H1>Static Entity</H1>
 * The Static Entity program contains all necessary components for all static entities
 *
 */
public abstract class StaticEntity {
    private float positionX;
    private float positionY;
    private int width;
    private int height;

    /**
     *This creates a static entity instance on the game map based on the passed x and y positions
     * @param positionX This stores the position x of a static entity object
     * @param positionY This stores the position y of a static entity object
     * @param width This stores the width of a static entity object
     * @param height This stores the height of a static entity object
     */
    public StaticEntity(float positionX, float positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    /**
     * This method will set the position x of a static entity object.
     * @param positionX This stores the position x of a static entity object
     */
    //positionX and positionY setters and getters
    public void setPositionX(float positionX) { this.positionX = positionX; }

    /**
     *  This method will set the position y of a static entity object.
     * @param positionY This stores the position y of a static entity object
     */
    public void setPositionY(float positionY) { this.positionY = positionY; }

    /**
     *
     * @return position x of an static entity object
     */
    public float getPositionX() { return positionX; }

    /**
     *
     * @return position y of an static entity object
     */
    public float getPositionY() { return positionY; }

    //width and height setters and getters;

    /**
     *  This method will set the width of a static entity object.
     * @param width This stores the width of a static entity object
     */
    public void setWidth(int width) { this.width = width; }

    /**
     *  This method will set the height of a static entity object.
     * @param height This stores the height of a static entity object
     */
    public void setHeight(int height) { this.height = height; }

    /**
     *
     * @return width of an static entity object
     */
    public int getWidth() { return width; }

    /**
     *
     * @return height of an static entity object
     */
    public int getHeight() { return height; }

    /**
     * This method will draw a static entity object on the game map.
     * @param g This will draw the image of the entity on the screen
     */
    public abstract void render(Graphics g);

}