package LoadingResources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <H1>Load an Image</H1>
 * This program takes images from a filepath and loads them
 */

public class ImageLoader {

    /**
     * This method returns a buffered image of the image given found from the filepath inputted
     * @param path the filepath of the image
     * @return BufferedImage  the ability for the image to be displayed
     */
    public static BufferedImage loadImage (String path){
        try {
            return ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
