package KeyManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <H1>Key Inputs manager</H1>
 * This class will create an object, which logs the user's key input and acts accordingly
 */

public class KeyInput implements KeyListener {
    private boolean  up;
    private boolean left;
    private boolean right;
    private boolean down;
    private boolean[] keys;
    private int a = 0;

    /**
     * Initializes the keys boolean array.
     */
    public KeyInput(){
        keys = new boolean[256];
    }

    /**
     * If the user has pressed the key board key based on specified key code it will initialize the keys array elemment to true
     * @param e This contains the key code based on the user input
     */
    @Override
    public void keyPressed(KeyEvent e){
        if(a == 0) {
            keys[e.getKeyCode()] = true;
            a = 1;
        }
    }

    /**
     * If the user have released the pressed key it will set the element of the keys array (Based on the released key code) to false.
     * @param e This contains the key code based on the user input
     */
    @Override
    public void keyReleased(KeyEvent e){
        keys [e.getKeyCode()] = false;
        a=0;
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    /**
     * This will update the up/left/right/down boolean variables based on the user's input
     */
    public void tick(){
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys [KeyEvent.VK_RIGHT];
    }

    /**
     * Returns a boolean variable, which determines if the main character have to go up or not.
     * @return the up boolean variable
     */
    public boolean goUp(){
        return up;
     }

    /**
     *Returns a boolean variable, which determines if the main character have to go down or not.
     * @return the down boolean variable
     */
     public boolean goDown(){
         return down;
     }

    /**
     *Returns a boolean variable, which determines if the main character have to go left or not.
     * @return the left boolean variable
     */
     public boolean goLeft(){ return left; }

    /**
     *Returns a boolean variable, which determines if the main character have to go right or not.
     * @return the right boolean variable
     */
     public boolean goRight(){
         return right;
     }

}
