package Obstacles;


import java.awt.*;

/**
 * <H1>Obstacle</H1>
 * This will constructs an abstract obstacle entity object
 */
public abstract class Obstacle {
      private float position_x;
      private float position_y;

      private int width;
      private int height;

    /**
     * This will constructs an obstacle object on the game map.
     * @param position_x This stores the position x of the obstacle object on the map
     * @param position_y This stores the position y of the obstacle object on the map
     * @param width This stores the width of the obstacle object on the map
     * @param height This stores the height of the obstacle object on the map
     */
      public Obstacle(float position_x, float position_y, int width, int height){
          this.position_x = position_x;
          this.position_y = position_y;
          this.width = width;
          this.height = height;
      }

    /**
     * This method will set the height of an obstacle object
     * @param height This stores the height of the obstacle object on the map
     */
    public void setHeight(int height){
        this.height = height;
      }


    /**
     * This method will set the width of an obstacle object
     * @param width This stores the width of the obstacle object on the map
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     * This method will return the position x of an obstacle object
     * @return position x of the obstacle object on the map
     * */
    public float getPositionX(){
        return position_x;
    }

    /**
     * This method will return the position y of an obstacle object
     * @return position y of the obstacle object on the map
     */
    public float getPositionY(){ return position_y; }

    /**
     * This method will return the width of an obstacle object
     * @return width of the obstacle object on the map
     */
    public int getWidth(){
          return width;
    }

    /**
     * This method will return the height of an obstacle object
     * @return height of the obstacle object on the map
     */
    public int getHeight(){
          return height;
    }

    /**
     * This method will paint an obstacle object on the map.
     * @param g This will draw the image of the entity on the screen
     */
    public abstract void render(Graphics g); // Abstract



}
