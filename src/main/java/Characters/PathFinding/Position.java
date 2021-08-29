package Characters.PathFinding;

/**
 * This class stores the positions of an Entity in terms of coordinates
 */
public class Position {
    private int position_x;
    private int position_y;
    private Position parent = null;

    /**
     * This constructs a position object, which stores the position x and y of the entity.
     * @param position_x This is the position X of the entity, which is in coordinates
     * @param position_y This is the position Y of the entity, which is in coordinates
     */
    public Position(int position_x, int position_y){
        this.position_x = position_x;
        this.position_y = position_y;
    }

    /**
     * This method converts the position of the object to the string.
     * @return the position of the entity in string
     */
    public String convertPositionsToString(){
        String temp = Integer.toString(position_x) + "," + Integer.toString(position_y);
        return temp;
    }

    /**
     * This method returns the position X of the entity, which is in coordinates
     * @return the position X of the object in coordinates
     */
    public int getPosition_X(){
        return position_x;
    }

    /**
     * This method returns the position Y of the entity, which is in coordinates
     * @return the position Y of the object in coordinates
     */
    public int getPosition_Y() {
        return position_y;
    }

    /**
     * This method returns the position X of the entity, which is in integer
     * @return the position X of the object in integer
     */
    public int getPosition_X_Integer(){
        return position_x * 30;
    }

    /**
     * This method returns the position Y of the entity, which is in integer
     * @return the position Y of the object in integer
     */
    public int getPosition_Y_Integer(){
        return position_y * 30;
    }

    /**
     * This method sets the parent of the current position object.
     * @param parent This is the parent of the current position
     */
    public void setParent(Position parent){
        this.parent = parent;
    }

    /**
     * This method returns the parent of the position object.
     * @return the parent of the position
     */
    public Position getParent() {
        return parent;
    }
}
