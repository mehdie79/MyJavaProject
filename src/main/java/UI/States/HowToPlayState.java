package UI.States;

import GameLauncher.Game;
import LoadingResources.ImageManager;
import UI.Button.ReturnMenuButton;

import java.awt.*;
/**
 * <H1>How the screen appears when the user has won</H1>
 * This class implements what the user sees when they have
 * clicked on the how to play button.
 */
public class HowToPlayState extends State {

    private ReturnMenuButton menuButton;

    /**
     * This will create the how to play state instance.
     * @param game stores the game object
     */
    public HowToPlayState(Game game) {
        super(game);
    }

    /**
     * This method will create the buttons existed on the how to play state.
     */
    public void createButtons(){
        menuButton = new ReturnMenuButton(250,640,300,200, ImageManager.returnMenuButton,game.getMenuState(),this);
        game.getMouseInputs().addButton(menuButton);
    }

    /**
     * This method checks whether the buttons existed in the how to play state have been clicked or not
     */
    @Override
    public void tick() {
        menuButton.click();
    }

    /**
     * Render all entities existed on the how to play state.
     * @param g draws the images of entities on the game map
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(ImageManager.howToPlayImage, 0,0,900,930,null);
        menuButton.render(g);
    }

}
