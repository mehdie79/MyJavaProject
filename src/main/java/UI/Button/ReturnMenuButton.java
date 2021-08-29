package UI.Button;

import UI.States.State;

import java.awt.image.BufferedImage;
/**
 * <H1>ReturnMenuButton</H1>
 * This class will instantiate a ReturnMenuButton instance
 */
public class ReturnMenuButton extends UIButton {
    private State menuState;

    /**
     *This will create a ReturnMenuButton object based on the indicated parameters.
     * @param x This stores the x position of an ReturnMenuButton object instance
     * @param y This stores the y position of an ReturnMenuButton object instance
     * @param width This stores the width of an ReturnMenuButton object instance
     * @param height This stores the height of an ReturnMenuButton object instance
     * @param image This stores the image of an ReturnMenuButton object instance
     * @param menuState This stores the current state, which the game has been on
     * @param existedState This stores the state of an ReturnMenuButton object, which have been created
     */
    public ReturnMenuButton(float x, float y, int width, int height, BufferedImage image, State menuState, State existedState) {
        super(x, y, width, height, image,existedState);
        this.menuState = menuState;
    }

    /**
     *If the button has been clicked it set the states to menuState.
     */
    public void click() {
        if(isHover()){
            State.setCurrentState(menuState);
        }
    }

}
