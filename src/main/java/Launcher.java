//import Window.Screen;

import GameLauncher.Game;

/**
 * <H1>Launching the Game</H1>
 * This will launch the game and contains main
 */

public class Launcher {

    public static int screenWidth =900;
    public static int screenHeight =930;


    public static void main(String[] args){

        Game game=new Game(screenWidth, screenHeight,"Rabbit Trails");
        game.start();
    }
}
