package UI.States;

import GameLauncher.Game;
import LoadingResources.ImageManager;
import UI.Button.ReturnMenuButton;

import java.awt.*;

/**
 * <H1>How the screen appears when the user has lost</H1>
 * This class implements what the user sees when they have
 * lost the game.
 */

public class LosingState extends State {

    ReturnMenuButton returnMenuButton;

    /**
     * This will create the LosingState instance.
     * @param game stores the game object
     */
   public LosingState(Game game){
       super(game);
   }


    /**
     * This method will create the buttons existed on the losing state.
     */
    @Override
    public void createButtons(){
        returnMenuButton = new ReturnMenuButton(280,600,300,200,ImageManager.returnMenuButton,game.getMenuState(),this);
        game.getMouseInputs().addButton(returnMenuButton);
    }

    /**
     * This method checks whether the buttons existed in the losing state have been clicked or not
     */
    @Override
    public void tick() {
       returnMenuButton.click();
    }


    /**
     * Render all entities existed on the losing state.
     * @param g draws the images of entities on the game map
     */
    @Override
    public void render(Graphics g) {
       g.drawImage(ImageManager.losingImage,0,0,900,930,null);
       returnMenuButton.render(g);
    }
}
