package GameLauncher;

import java.awt.*;
import java.awt.image.BufferStrategy;
import LoadingResources.ImageManager;
import MouseManager.MouseInputs;
import UI.States.*;
import Window.Screen;

/**
 * <H1>Getting ready to launching the game</H1>
 * This program calls all the methods appropriately to launch the game. This includes
 * the game map/screen, graphics of the game, and starting and stopping the game
 */

public class Game implements Runnable {
    private Screen screen;
    private  Thread thread;
    private int width, height;
    private String title;
    private  boolean gameRunning = false;
    private BufferStrategy bs;
    public Graphics g;
    private MouseInputs mouseInputs;

    private MenuState menuState;
    private State losingState;
    private State howToPlayState;
    private State winningState;

    /**
     * This will constructs a game object based on the passed width, height, and title.
     * @param width This stores the width of the screen
     * @param height This stores the height of the screen
     * @param title This stores the title of the screen
     */
    public Game(int width , int height, String title){
        this.width =width;
        this.height = height;
        this.title = title;
        mouseInputs = new MouseInputs();
    }

    /**
     * This method will paint all entities on the screen in each tick
     */
    private void render() {
        // Drawing images into the screen
        bs = screen.getCanvas().getBufferStrategy();
        if(bs == null) {
            screen.getCanvas().createBufferStrategy(3);  // Create 3 Buffers for speed improvement
            return;
        }
        // Draw images into the canvas
        g = bs.getDrawGraphics();

        // Draw on the screen based on the current State of the game


        if (State.getCurrentState() != null) {
            State.getCurrentState().render(g);
        }

        g.dispose();
        bs.show();

    }

    /**
     * This method will update the state of the game
     */
    private void tick(){
        // Update based on the current State of the game
        if (State.getCurrentState() != null)
            State.getCurrentState().tick();
    }

    /**
     * This method will return the screen object of the game
     * @return the screen of the game
     */
    public Screen getScreen(){
        return screen;
    }

    /**
     * This method will initialize all the screen and all the graphics of the game.
     */
    private void initializeGraphicsGame(){
        screen = new Screen(width, height, title);

       // Setting the focus for executing mouse inputs
        screen.getJFrame().addMouseListener(mouseInputs);
        screen.getJFrame().addMouseMotionListener(mouseInputs);
        screen.getCanvas().addMouseListener(mouseInputs);
        screen.getCanvas().addMouseMotionListener(mouseInputs);

        ImageManager.initializeImages();

        menuState = new MenuState(this);
        losingState = new LosingState(this);
        howToPlayState = new HowToPlayState(this);
        winningState = new WinningState(this);

        menuState.createButtons();
        losingState.createButtons();
        howToPlayState.createButtons();
        winningState.createButtons();

        menuState.startGame();

        State.setCurrentState(menuState);
    }

    /**
     * This will create the gameLoop for updating and rendering at a certain amount of time
     * Description: The below game loop when the game starts in each one second invokes
     * tick and render method 60 times.
     *
     */
    public void run() {
        initializeGraphicsGame();

        long lastTime = System.nanoTime(); // return the current time of computer in nano second
        final double FRAME_PER_SECOND = 60.0;  // tick and render method be called 60 times every second
        double timePerTick = 1000000000 / FRAME_PER_SECOND;
        double lag = 0;  // Stores the maximum amount of time computer is allowed to call tick and render method
        int  ticks =0;
        long timer = 0;

        while(gameRunning){
            long now = System.nanoTime();
            lag += (now - lastTime) / timePerTick;
            timer += now - lastTime ; // the amount of time past since last time it was called
            lastTime = now;
            // if lag >= 1 have to call tick and render for achieving 60 frame per second
            while (lag >= 1) {
                tick();
                render();
                lag--;
                ticks ++;
            }
            if (timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    /**
     * This will return a new game state object each time that has been invoked
     * @return A new gameState object
     */
    public GameState getGameState(){
        return new GameState(this);
    }

    /**
     * This method will return the game screen's width.
     * @return the width of game screen
     */
    public int getGameWidth(){
        return width;
    }

    /**
     * This method will return the game screen's height.
     * @return the height of game screen
     */
    public int getGameHeight(){
        return height;
    }

    /**
     * Restarts the game by removing the old game object and initializing a new one
     */
    public void resetGame(){
        menuState.removeOldGame();
        menuState.startGame();
    }

    /**
     * This will return the menu state object
     * @return the menu state object
     */
    public State getMenuState(){
        return menuState;
    }

    /**
     *This will return the losing state object
     * @return the losing state object
     */
    public State getLosingState(){
        return losingState;
    }

    /**
     *This will return the winning state object
     * @return the wining state object
     */
    public State getWinningState(){
        return winningState;
    }

    /**
     *This will return the how to play state object
     * @return the how to play state object
     */
    public State getHowToPlayState(){
        return howToPlayState;
    }

    /**
     *This will return the mouse inputs object
     * @return the mouse inputs object
     */
   public MouseInputs getMouseInputs(){
        return mouseInputs;
    }

    /**
     * This method will initiate the game loop.
     */
    public synchronized void start(){
        // For preventing to call the running method more than one time.
        if(gameRunning)
            return;
        gameRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * This method will stop the game loop when the game is not running any more.
     */
    public synchronized void stop() {
        // For preventing the game to stop running more than one time.
        if(!gameRunning)
            return;
        gameRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
