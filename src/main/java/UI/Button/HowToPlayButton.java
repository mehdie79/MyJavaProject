package UI.Button;

import UI.States.State;

import java.awt.image.BufferedImage;
/**
 * <H1>HowToPlayButton</H1>
 * This class will instantiate a HowToPlayButton instance
 */
public class HowToPlayButton extends UIButton {
    private State howToPlayState;

    /**
     *This will create a HowToPlayButton object based on the indicated parameters.
     * @param x This stores the x position of an HowToPlayButton object instance
     * @param y This stores the y position of an HowToPlayButton object instance
     * @param width This stores the width of an HowToPlayButton object instance
     * @param height This stores the height of an HowToPlayButton object instance
     * @param image This stores the image of an HowToPlayButton object instance
     * @param howToPlayState This stores the current state, which the game has been on
     * @param existedState This stores the state of an HowToPlayButton object, which have been created
     */
    public HowToPlayButton(float x, float y, int width, int height, BufferedImage image, State howToPlayState, State existedState) {
        super(x, y, width, height, image,existedState);
        this.howToPlayState = howToPlayState;
    }

    /**
     *If the button has been clicked it set the states to how to play state.
     */
    @Override
    public void click() {
        if(isHover()){
            State.setCurrentState(howToPlayState);
        }
    }
}
