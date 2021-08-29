package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <H1>Tiles for the Game Map</H1>
 * The program Tile holds methods for the tiles which will be the dirt path
 * and wall/bush walls of the maze
 *
 */

public abstract class Tile {

    protected BufferedImage image;
    protected final int ID_TILE;


    protected float position_x;
    protected float position_y;
    public static final int TILE_WIDTH = 30;
    public static final int TILE_HEIGHT = 30;

    public static Tile[] tiles = new Tile[256];
    public static Tile dirtTile = new DirtTile(0);
    public static Tile wallTile = new WallTile(1);

    /**
     * This method is the constructor for instantiating a tile.
     *
     * @param image tile image needed
     * @param ID 0 or 1 indicating whether it is a dirt tile or a wall/bush title
     */

    public Tile(BufferedImage image, int ID){
        this.image = image;
        this.ID_TILE = ID;

        tiles[ID_TILE] = this;
    }

    /**
     * Draws the images of the tile object on the game map.
     * @param g draws the images of entities on the game map
     * @param position_x stores the x position of the tile object
     * @param position_y stores the y position of the tile object
     */
    public void render(Graphics g, int position_x, int position_y){
        g.drawImage(image,position_x,position_y,TILE_WIDTH,TILE_HEIGHT,null );
    }
    // Getters

    /**
     *
     * @return the ID of the tile
     */
    public int getID(){
        return ID_TILE;
    }

    /**
     *
     * @return the position x of the tile
     */
    public float getPosition_x() {
        return position_x;
    }

    /**
     *
     * @return the position y of the tile
     */
    public float getPosition_y() {
        return position_y;
    }

    // Setters

    /**
     * This will set the position x of an tile object on the map
     * @param position_x stores the x position of the tile object
     */
    public void setPosition_x(float position_x) {
        this.position_x = position_x;
    }

    /**
     *This will set the position y of an tile object on the map
     * @param position_y stores the y position of the tile object
     */
    public void setPosition_y(float position_y) {
        this.position_y = position_y;
    }
}
