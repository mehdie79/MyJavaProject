package StaticEntities;

import java.awt.*;
import LoadingResources.ImageManager;

/**
 * <H1>Bonus Reward</H1>
 * This class will simply create an instance of a BonusReward
 *
 */

public class BonusRewards extends StaticEntity {
    private static final int BONUS_WIDTH = 30;
    private static final int BONUS_HEIGHT = 30;
    private static final int DURATION = 400;

    /**
     * This creates a bonus reward instance on the game map based on the passed x and y positions
     * @param positionX This stores the position x of a bonus reward
     * @param positionY This stores the position y of a bonus reward
     */
    public BonusRewards(float positionX, float positionY){
        super(positionX, positionY, BONUS_WIDTH, BONUS_HEIGHT);
    }

    /**
     * This will set the x and y position of the bonus reward on the map.
     * @param position_x This stores the position x of a bonus reward
     * @param position_y This stores the position y of a bonus reward
     */
    public void setPositions(float position_x, float position_y){
        this.setPositionX(position_x);
        this.setPositionY(position_y);
    }

    /**
     * This method will draw a bonus reward on the game map.
     * @param g This will draw the image of the entity on the screen
     */
    @Override
    public void render(Graphics g){
        g.drawImage(ImageManager.specialReward,(int)this.getPositionX(),(int)this.getPositionY(), this.getWidth(), this.getHeight(),null);
    }
}
