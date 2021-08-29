package UI.States;

import GameLauncher.Game;
import Map.GameMap;

import java.awt.*;
/**
 * <H1>How the screen appears when the user has won</H1>
 * This class implements what the user sees when they have
 * won the game.
 */
public abstract class State {

    protected GameMap map;
    private static State currentState = null;
    protected Game game;

    /**
     * This will create the State instance.
     * @param game stores the game object
     */
    public State (Game game){
        this.game = game;
    }

    /**
     *
     * @return current state of the game
     */
    public static State getCurrentState() {
        return currentState;
    }

    /**
     * This method will set the game map object to the states object
     * @param map stores the GameMap object of the game
     */
    public void setMap (GameMap map){
        this.map = map;
    }

    /**
     *This method will set the current state, which the game is currently running.
     * @param currentState stores the current state object, which the game is running
     */
    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }

    /**
     * This method will create the buttons existed on the state.
     */
    public abstract void createButtons();

    /**
     * This method updates the buttons existed on the current state.
     */
    public abstract void tick();

    /**
     *  Render all entities existed on the state.
     * @param g draws the images of entities on the game map
     */
    public abstract void render(Graphics g);
}
