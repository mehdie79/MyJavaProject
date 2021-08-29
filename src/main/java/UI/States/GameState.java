package UI.States;

import GameLauncher.Game;
import Map.GameMap;

import java.awt.*;

/**
 * <H1>What appears during the gameplay</H1>
 * This class implements everything that happens while the game is running
 * and being played by the user.
 */

public class GameState extends State {

   private GameMap map;

    /**
     * Creates the game and map object of the game.
     * @param game stores the game object
     */
   public GameState(Game game){
       super(game);
       map= new GameMap();
       map.setGame(game);
   }

    /**
     *
     * @return map object of the game
     */
   public GameMap getGameMap(){
       return map;
   }

    @Override
    public void createButtons() {}

    /**
     * Updates the entities existed on the game map while checking whether the user have win/lost the game.
     */
    @Override
    public void tick() {
       map.tick();
       map.updateThePlayerScore();
       checkIfPlayerWon();
       checkIfPlayerLost();
    }

    /**
     * If the player has won the game set the current state to winning state and reset the game.
     */
    public void checkIfPlayerWon(){
       if(map.playerWinning()){
           game.getWinningState().setMap(map);
           State.setCurrentState(game.getWinningState());
           game.resetGame();
       }

    }

    /**
     * If the player has lost the game set the current state to losing state and reset the game.
     */
    public void checkIfPlayerLost(){
        if(map.gameOver()){
            State.setCurrentState(game.getLosingState());
            game.resetGame();
        }
    }

    /**
     * Render all entities existed on the game state.
     * @param g draws the images of entities on the game map
     */
    @Override
    public void render(Graphics g) {
       map.drawTiles(g);
       map.drawTheGridLines(g);
       map.render(g);
    }

}
