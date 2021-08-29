package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * <H1>The Screen of the Game</H1>
 * This program implements the screen which is seen when the game is running
 */

public class Screen {
    private JFrame frame;
    private Canvas canvas;
    private String gameTitle;
    private int width;
    private int height;

    /**
     *  This will constructs a screen object based on the passed width, height, and title variables.
     * @param width This stores the width of the screen
     * @param height This stores the height of the screen
     * @param gameTitle This stores the game title of the screen
     */
    public Screen(int width, int height, String gameTitle){
        this.width= width;
        this.height= height;
        this.gameTitle= gameTitle;
        createScreen();
    }

    /**
     * This method creates the screen giving it its dimensions using the
     * methods provided by JFrame and Canvas
     */
    private void createScreen(){
        frame = new JFrame(gameTitle);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas= new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    //getters

    /**
     *
     * @return the canvas object of the screen
     */
    public Canvas getCanvas(){
        return canvas;
    }

    /**
     *
     * @return the JFrame object of the screen
     */
    public JFrame getJFrame(){
        return frame;
    }

    /**
     * This method will closes the Frame when the user clicks on the exit button
     */
    public void closeTheFrame(){
        this.frame.dispatchEvent(new WindowEvent(getJFrame(), WindowEvent.WINDOW_CLOSING));
    }

    /**
     *
     * @return width of the screen
     */
    public int getWidth(){
        return width;
    }

    /**
     *
     * @return height of the screen
     */
    public int getHeight(){
        return height;
    }
}
