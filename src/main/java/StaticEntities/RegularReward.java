package StaticEntities;

import java.awt.*;

import LoadingResources.ImageManager;

/**
 * <H1>Regular Reward</H1>
 * The Regular Reward program when called simply creates an instance of a RegularReward
 *
 */

public class RegularReward extends StaticEntity {
    public static final int REWARD_WIDTH = 33;
    public static final int REWARD_HEIGHT = 33;


    /**
     *This creates a regular reward instance on the game map based on the passed x and y positions
     * @param positionX This stores the position x of a bonus reward
     * @param positionY This stores the position y of a bonus reward
     */
    public RegularReward(float positionX, float positionY){
        super(positionX, positionY, REWARD_WIDTH, REWARD_HEIGHT);
    }

    /**
     * This method will draw a regular reward on the game map.
     * @param g This will draw the image of the entity on the screen
     */
    @Override
    public void render(Graphics g){
        g.drawImage(ImageManager.regularReward,(int)this.getPositionX(),(int)this.getPositionY(),this.getWidth(), this.getHeight(), null);
    }
}
