package UI.Button;


import UI.States.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * <H1>UIButton</H1>
 * This class will instantiate a UI button instance
 */
public abstract class UIButton {
    protected BufferedImage image;
    private float position_x;
    private float position_y;
    private int width, height;
    private boolean hover = false;
    protected State existedState;

    /**
     * This will create a UI button object based on the indicated parameters.
     * @param x This stores the x position of an UIButton object instance
     * @param y This stores the y position of an UIButton object instance
     * @param width This stores the width of an UIButton object instance
     * @param height This stores the height of an UIButton object instance
     * @param image This stores the image of an UIButton object instance
     * @param stateOfGame This stores the current state, which the game has been on
     */
    public UIButton(float x, float y, int width, int height, BufferedImage image,State stateOfGame) {
        this.position_x = x;
        this.position_y =y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.existedState = stateOfGame;
    }

    /**
     * This method will draw an UI  Button object on the screen
     * @param g draws the images of entities on the game map
     */
    public void render(Graphics g) {
        g.drawImage(image, (int) getPosition_x(), (int) getPosition_y(), getWidth(), getHeight(), null);
    }

    /**
     * If the current state is equal to the passed existed state and the button have been clicked it will set the hover variable equal
     * to true and false otherwise.
     * @param e This is the event object, which has been passed based on the user's mouse input
     */
    public void OnMouse(MouseEvent e){
        if(existedState == State.getCurrentState()){
            if(e.getX() >= position_x && e.getX() <= position_x + width && e.getY() <= position_y+height && e.getY() >= position_y )
                hover =true;
            else
                hover = false;
        }
        else
            hover = false;
    }

    /**
     *
     * @return the position x of an UI button object
     */
    public float getPosition_x() {
        return position_x;
    }

    /**
     *
     * @return the position y of an UI button object
     */
    public float getPosition_y() {
        return position_y;
    }

    /**
     *
     * @return the width of an UI button object
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return the height of an UI button object
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method will check if the button has been clicked or not.
     * @return the hover boolean variable of an UI button object
     */
    public boolean isHover() {
        return hover;
    }

    /**
     * In each thick it will check if the button have clicked or not and it will act accordingly.
     */
    public abstract void click();

}
