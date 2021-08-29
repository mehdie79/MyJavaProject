package MouseManager;

import UI.Button.UIButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * <H1>MouseInputs</H1>
 * This class is responsible for receiving inputs from user's mouse.
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    private boolean pressLeft, pressRight;
    protected int mousePosition_x, mousePosition_y;
    ArrayList<UIButton> buttons = new ArrayList<>();


    /**
     * This method will append a new UIButton to the buttons array list.
     * @param button This is a UIButton object, which have been defined on the states class
     */
    public void addButton(UIButton button){
        buttons.add(button);
    }

    /**
     *This method will remove the indicated UIButton from the buttons array list.
     * @param button This is a UIButton object, which have been defined on the states class
     */
    public void removeButton(UIButton button){
        buttons.remove(button);
    }

    /**
     * This method will invoke the on mouse method of each button since the user have clicked on the screen
     * @param event This is the event object, which has been passed based on the user's mouse input
     */
    public void pressButtons(MouseEvent event){
        for (int i=0; i< buttons.size();i++)
            buttons.get(i).OnMouse(event);
    }

    /**
     *This method will set either the press right or left variables to true since the user have clicked on the screen
     * @param e This is the event object, which has been passed based on the user's mouse input
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            pressRight =true;
        else if(e.getButton() == MouseEvent.BUTTON3)
            pressLeft = true;

    }

    /**
     *This method will set either the press right or left variables to false since the user have released the clicked
     * button on the screen.
     * @param e This is the event object, which has been passed based on the user's mouse input
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            pressRight =false;
        else if(e.getButton() == MouseEvent.BUTTON3)
            pressLeft = false;
        pressButtons(e);
    }

    /**
     * This method will set the position x and y of the mouse on the screen.
     * @param e This is the event object, which has been passed based on the user's mouse input
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition_x = e.getX();
        mousePosition_y = e.getY();
    }

    /**
     * This method will return press left variable to see whether or not the mouse left button have been pressed.
     * @return the press left boolean variable
     */
    public boolean pressLeft() {
        return pressLeft;
    }

    /**
     *This method will return press right variable to see whether or not the mouse right button have been pressed.
     * @return the press right boolean variable
     */
    public boolean pressRight() {
        return pressRight;
    }

    /**
     * This will return the x position of the mouse on the screen.
     * @return the position x of the mouse on the screen
     */
    public int getMousePosition_x() {
        return mousePosition_x;
    }

    /**
     *This will return the y position of the mouse on the screen.
     * @return the position y of the mouse on the screen
     */
    public int getMousePosition_y() {
        return mousePosition_y;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) { }

}
