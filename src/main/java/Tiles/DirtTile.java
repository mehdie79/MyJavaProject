package Tiles;

import LoadingResources.ImageManager;
import Tiles.Tile;

/**
 * <H1>Dirt Road for player to walk on</H1>
 * The DirtTile program is used to create a dirt tile for the game map
 */

public class DirtTile extends Tile {

    /**
     * Creates a dirt tile object on the game map
     * @param ID stores the ID of an tile object
     */
    public DirtTile(int ID) {
        super(ImageManager.dirt, ID);
    }
}
