package Obstacles;

import LoadingResources.ImageManager;

import java.awt.*;

/**
 * <H1>Barrier</H1>
 * This will constructs a barrier object on the game map
 */
public class Barrier extends Obstacle{

    public static final int WIDTH_BARRIER= 30;
    public static final int HEIGHT_BARRIER= 30;

    /**
     * This will constructs a barrier object on the map based on the defined position, width, and height.
     * @param position_x This stores the position x of the barrier on the map
     * @param position_y This stores the position y of the barrier on the map
     */
    public Barrier(float position_x, float position_y){
        super(position_x, position_y, WIDTH_BARRIER, HEIGHT_BARRIER);
    }

    /**
     * This function will draw the barrier on the screen based on its position, width, and height.
     * @param g This will draw the image of the entity on the screen
     */
    @Override
    public void render(Graphics g){
        g.drawImage(ImageManager.stone,(int) this.getPositionX(),(int) this.getPositionY(),getWidth(),getHeight(),null);
    }

}
