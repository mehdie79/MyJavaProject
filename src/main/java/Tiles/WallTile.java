package Tiles;


import LoadingResources.ImageManager;


/**
 * <H1>Dirt Road for player to walk around</H1>
 * The DirtTile program is used to create a wall/bush tile for the game map
 */

public class WallTile extends Tile {
    /**
     * Creates a wall tile object on the game map
     * @param ID stores the ID of an tile object
     */
    public WallTile(int ID) {
        super(ImageManager.wall, ID);
    }

}




