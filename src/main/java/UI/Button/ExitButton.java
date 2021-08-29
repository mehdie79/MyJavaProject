package UI.Button;

import GameLauncher.Game;
import UI.States.State;

import java.awt.image.BufferedImage;
/**
 * <H1>ExitButton</H1>
 * This class will instantiate an ExitButton instance
 */
public class ExitButton extends UIButton {

    private Game game;

    /**
     *This will create a ExitButton object based on the indicated parameters.
     * @param x This stores the x position of an ExitButton object instance
     * @param y This stores the y position of an ExitButton object instance
     * @param width This stores the width of an ExitButton object instance
     * @param height This stores the height of an ExitButton object instance
     * @param image This stores the image of an ExitButton object instance
     * @param game This stores the game object
     * @param existedState This stores the state of an ExitButton object, which have been created
     */
    public ExitButton(float x, float y, int width, int height, BufferedImage image, Game game, State existedState) {
        super(x, y, width, height, image,existedState);
        this.game = game;
    }

    /**
     * If the button has been clicked it will close the frame otherwise it will not
     */
    public void click() {
        if(isHover()){
            game.getScreen().closeTheFrame();
        }
    }
}
