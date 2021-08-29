package UI.States;

import GameLauncher.Game;
import LoadingResources.ImageManager;
import UI.Button.ExitButton;
import UI.Button.HowToPlayButton;
import UI.Button.StartButton;

import java.awt.*;

/**
 * <H1>The main menu of the game</H1>
 * This class implements what the user sees before the game starts. Including the
 * play button, how to play button, and exit button.
 */

public class MenuState extends State {

    private StartButton playButton;
    private ExitButton exitButton;
    private HowToPlayButton helpButton;

    /**
     * This will create the MenuState instance.
     * @param game stores the game object
     */
    public MenuState(Game game) {
        super(game);
    }

    /**
     * This method will initilize the play button object and adds it to the UIButtons array in the mouse input class.
     */
    public void startGame(){
        playButton = new StartButton(250, 350, 300, 200,ImageManager.playButton,game.getGameState(),this);
        game.getMouseInputs().addButton(playButton);
    }

    /**
     * This method will create the buttons existed on the menu state.
     */
    @Override
    public void createButtons(){
        helpButton = new HowToPlayButton(250,550,300,200,ImageManager.helpButton,game.getHowToPlayState(),this);
        exitButton = new ExitButton(250, 750, 300, 200,ImageManager.exitButton,game,this);
        game.getMouseInputs().addButton(helpButton);
        game.getMouseInputs().addButton(exitButton);
    }

    /**
     * Removes the old play button object from the buttons array list.
     */
    public void removeOldGame(){
        game.getMouseInputs().removeButton(playButton);
    }

    /**
     * This method checks whether the buttons existed in the menu state have been clicked or not
     */
    @Override
    public void tick() {
        helpButton.click();
        playButton.click();
        exitButton.click();
    }

    /**
     * Render all entities existed on the menu state.
     * @param g draws the images of entities on the game map
     */
    @Override
    public void render(Graphics g) {
       g.drawImage(ImageManager.menu, 0, 0,900, 930, null);
       playButton.render(g);
       exitButton.render(g);
       helpButton.render(g);
    }


}
