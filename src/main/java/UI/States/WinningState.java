package UI.States;

import GameLauncher.Game;
import LoadingResources.ImageManager;
import Map.GameMap;
import UI.Button.ReturnMenuButton;
import UI.States.State;

import java.awt.*;

/**
 * <H1>How the screen appears when the user has won</H1>
 * This class implements what the user sees when they have
 * won the game.
 */

public class WinningState extends State {

    ReturnMenuButton returnMenuButton;

    /**
     * This will create the WinningState instance.
     * @param game stores the game object
     */
    public WinningState(Game game) {
        super(game);
    }

    /**
     * This method will create the buttons existed on the winning state.
     */
    @Override
    public void createButtons(){
        returnMenuButton = new ReturnMenuButton(250,650,300,200,ImageManager.returnMenuButton,game.getMenuState(),this);
        game.getMouseInputs().addButton(returnMenuButton);
    }

    /**
     * This method checks whether the buttons existed in the winning state have been clicked or not
     */
    @Override
    public void tick() {
        returnMenuButton.click();
    }

    /**
     * Render all entities existed on the winning state.
     * @param g draws the images of entities on the game map
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(ImageManager.winningImage,0,0,900,930,null);
        g.setColor(Color.black);
        Font font = new Font("arial",Font.BOLD, 70);
        g.setFont(font);
        g.drawString("Score: " + map.getScore(), 100, 550);
        g.drawString("Time: " + map.getTime(), 100, 650);
        returnMenuButton.render(g);
    }

}
