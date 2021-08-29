package LoadingResources;
import java.awt.image.BufferedImage;

/**
 * <H1>Manage Images</H1>
 * This program stores and initializes all the images
 */

public class ImageManager {
    public static BufferedImage playerdefault,playerRight,playerLeft,playerDown,playerUp;
    public static BufferedImage dirt, wall, stone, punishment, regularReward, specialReward;
    public static BufferedImage enemyUp, enemyDown, enemyRight, enemyLeft;
    public static BufferedImage startSign, finishSign;
    public static BufferedImage howToPlayImage;
    public static BufferedImage menu, playButton, losingImage, winningImage;
    public static BufferedImage returnMenuButton, exitButton, helpButton;

    /**
     * Loads all the images existed in the resources folder for more efficiency purpose
     */
    public static void initializeImages(){
        playerRight = ImageLoader.loadImage("src/main/resources/Images/rightbunny.png");
        playerLeft = ImageLoader.loadImage("src/main/resources/Images/leftbunny.png");
        playerDown = ImageLoader.loadImage("src/main/resources/Images/downbunny.png");
        playerUp = ImageLoader.loadImage("src/main/resources/Images/upbunny.png");
        playerdefault = ImageLoader.loadImage("src/main/resources/Images/frontbunny.png");

        dirt = ImageLoader.loadImage("src/main/resources/Images/dirt.png");
        wall = ImageLoader.loadImage("src/main/resources/Images/wall.png");
        stone = ImageLoader.loadImage("src/main/resources/Images/stone.png");
        punishment = ImageLoader.loadImage("src/main/resources/Images/punishment.png");
        regularReward = ImageLoader.loadImage("src/main/resources/Images/regularReward.png");
        specialReward = ImageLoader.loadImage("src/main/resources/Images/specialReward.png");


        enemyUp = ImageLoader.loadImage("src/main/resources/Images/upeagle.png");
        enemyDown = ImageLoader.loadImage("src/main/resources/Images/downeagle.png");
        enemyRight = ImageLoader.loadImage("src/main/resources/Images/righteagle.png");
        enemyLeft  = ImageLoader.loadImage("src/main/resources/Images/lefteagle.png");

        startSign = ImageLoader.loadImage("src/main/resources/Images/start.png");
        finishSign = ImageLoader.loadImage("src/main/resources/Images/finish.png");


        playButton= ImageLoader.loadImage("src/main/resources/Images/playButton.png");

        menu = ImageLoader.loadImage("src/main/resources/Images/menu.png");
        playButton= ImageLoader.loadImage("src/main/resources/Images/playButton.png");

        losingImage = ImageLoader.loadImage("src/main/resources/Images/losing.jpg");
        winningImage= ImageLoader.loadImage("src/main/resources/Images/winning.jpg");

        returnMenuButton = ImageLoader.loadImage("src/main/resources/Images/menuButton.png");
        exitButton = ImageLoader.loadImage("src/main/resources/Images/exitButton.png");
        helpButton = ImageLoader.loadImage("src/main/resources/Images/howToPlayButton.png");
        howToPlayImage = ImageLoader.loadImage("src/main/resources/Images/howToPlay.jpg");


    }

}

