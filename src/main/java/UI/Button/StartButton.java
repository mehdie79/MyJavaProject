package UI.Button;


import Map.GameMap;
import UI.States.GameState;
import UI.States.State;


import java.awt.image.BufferedImage;
/**
 * <H1>StartButton</H1>
 * This class will instantiate a StartButton instance
 */
public class StartButton extends UIButton {


    private GameState gameState;
    public GameMap map;

    /**
     *This will create a StartButton object based on the indicated parameters.
     * @param x This stores the x position of an StartButton object instance
     * @param y This stores the y position of an StartButton object instance
     * @param width This stores the width of an StartButton object instance
     * @param height This stores the height of an StartButton object instance
     * @param image This stores the image of an StartButton object instance
     * @param gameState This stores the current state, which the game has been on
     * @param existedState This stores the state of an StartButton object, which have been created
     */
    public StartButton(float x, float y, int width, int height, BufferedImage image, GameState gameState, State existedState) {
        super(x, y, width, height,image,existedState);
        this.gameState = gameState;
        map = gameState.getGameMap();
    }

    /**
     *If the button has been clicked it set the states to gameState and it will start the time of the game.
     */
    public void click() {
        if (isHover()) {
            State.setCurrentState(gameState);
            map.setStartTime();
        }
    }
}


