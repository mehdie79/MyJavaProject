package StaticEntities;

import LoadingResources.ImageManager;

import java.awt.*;

/**
 * <H1>Punishment</H1>
 * The Punishment program when called simply creates an instance of a Punishment
 *
 */

public class Punishment extends StaticEntity {
    private static final int PUNISH_WIDTH = 30;
    private static final int PUNISH_HEIGHT = 30;

    /**
     * This will construct a punishment instance on the game map
     * @param positionX This stores the position x of a punishment
     * @param positionY This stores the position y of a punishment
     */
    public Punishment(float positionX, float positionY){
        super (positionX, positionY, PUNISH_WIDTH, PUNISH_HEIGHT);

    }

    /**
     * This method will draw a punishment on the game map.
     * @param g This will draw the image of the entity on the screen
     */
    @Override
    public void render(Graphics g){
        g.drawImage(ImageManager.punishment,(int)this.getPositionX(),(int)this.getPositionY(),this.getWidth(), this.getHeight(), null);
    }
}
