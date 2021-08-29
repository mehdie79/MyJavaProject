package Map;


import Characters.MovingEntities.Enemy;
import Characters.MovingEntities.MainCharacter;
import Characters.PathFinding.PathFinding;
import GameLauncher.Game;
import KeyManager.KeyInput;
import LoadingResources.ImageManager;
import StaticEntities.BonusRewards;
import StaticEntities.Punishment;
import StaticEntities.RegularReward;
import Obstacles.Barrier;
import Tiles.Tile;
import CollisionManager.Collisions;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * <H1>Map of the Game</H1>
 * The GameMap program creates the entire game board and continues to run it
 * for the duration of the user playing it.
 */

public  class GameMap{
    private int width;
    private int height;
    private Game game;
    private int[][] tiles;
    private int count1= 0;
    private int count2= 0;
    private int numberOfBonusRewardAtTime = 1;
    private BonusRewards bonusReward = null;
    public static final int COLUMNS = 30;
    private Collisions collisions;
    public static final int ROWS = 30;
    public static final int positionStartSign_x =30;
    public static final int positionStartSign_y =30;
    public static final int positionFinishSign_x =840;
    public static final int positionFinishSign_y =900;
    private static KeyInput keyInput;
    private long startTime;
    private boolean playerWinning = false;
    private boolean gameOver = false;
    private boolean allRewardCollected =false;


    private MainCharacter player;
    public int playerScore = 0;
    private float time;
    private PathFinding enemiesPathFinding;
    private List<Enemy> enemies = new ArrayList<>();
    private List<RegularReward> rewards = new ArrayList<>();
    private List<Punishment> punishments = new ArrayList<>();
    private List<BonusRewards> bonuses = new ArrayList<>();
    private List <Barrier> obstacles = new ArrayList<>();

    private final float[][] regularAwardPositions = {
            {179,117}, {300,267}, {510, 479}, {118, 597},
            {360, 747}, {841, 717}, {690, 177}, {778, 57},
            {28, 388}, {750, 387}, {780, 298}, {300,208}
    };

    private final float[][] punishmentPositions = {
            {299, 390}, {839, 630}, {719, 120}, {660, 239},
            {448, 810}, {28, 628}, {183, 720}
    };


    private final float[][] bonusAwardPositions = {
            {213, 270}, {300, 600},{753, 330}, {391, 480},
            {62, 751}, {182, 60}, {483, 210}, {693, 750}
    };


    private final float[][] barrierPositions = {
            {600, 390}, {450, 600},{690, 300}
    };
    private final float[][] enemiesPositions = {
          {510, 360},  {90, 210} , {630,690}
    };
    //creating all static entity variables

    /**
     * This method instantiates all the regular rewards and places them in the
     * arraylist rewards
     */
    private void initRegularReward(){
        for (float[] p : regularAwardPositions){
            rewards.add(new RegularReward(p[0], p[1]));
        }
    }

    /**
     * This method instantiates all the punishments and places them in the
     * arraylist punishments
     */
    private void initPunishment(){
        for (float[] p: punishmentPositions){
            punishments.add(new Punishment(p[0], p[1]));
        }
    }

    /**
     * This method instantiates all the bonus rewards and places them in the
     * arraylist bonuses
     */
    private void initBonus(){

        for (float[] p : bonusAwardPositions){
            bonuses.add(new BonusRewards(p[0], p[1]));
        }
    }

    /**
     *  Instantiate all the barriers and place them in the appropriate arrayList
     */
    private void initBarriers(){
        for(float[] p: barrierPositions){
            obstacles.add(new Barrier(p[0],p[1]));
        }
    }
    /**
     *  Instantiate all the Enemies and place them in the appropriate arrayList
     */
    private void initEnemies(){
        for(float [] p: enemiesPositions){
            enemies.add(new Enemy(p[0],p[1]));
        }
        setEnemiesCollisions();
    }

    /**
     * Set the collisions functionality for each enemy in the array list
     */
    private void setEnemiesCollisions() {
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).setCollisions(collisions);
        }
    }

    /**
     * This method sets the bonus award a new random position on each time interval
     */
    public void getBonusAwardNewPosition(){
        Random random = new Random();
        if(!allBonusRewardCollected()){
            bonusReward = bonuses.get(random.nextInt(bonuses.size()));
        }
    }

    /**
     * This method will remove the passed bonus reward from the array since the collision have been detected
     * between the main character and the passed bonus reward.
     * @param bonusReward This is the bonus reward object, which will be removed from the array
     */
    public void removeBonusAward(BonusRewards bonusReward){
        bonuses.remove(bonusReward);
    }

    /**
     * This is the class constructor and it creates an instance of the KeyInput for
     * the user's key input and also calls methods to initialize the static entity
     * objects
     */
    public  GameMap() {
        keyInput = new KeyInput();

        // Load the map from the map.txt file
        loadMap("src/main/resources/MapData/map.txt");
        initRegularReward();
        initBonus();
        getBonusAwardNewPosition();
        initPunishment();
        initBarriers();
        collisions = new Collisions(this);
        initEnemies();
        initPlayer();
        setEnemiesPathFinding();
    }

    /**
     * This method will initialize the starting position of the main character on the game map
     */
    private void initPlayer() {
        player = new MainCharacter();
        player.setCollisions(collisions);
    }


    /**
     * This method loads the game's map
     *
     * @param path the path of the file containing the map's layout
     */
    public void loadMap(String path) {

        try {
            FileInputStream file = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            this.width = Integer.parseInt(reader.readLine());
            this.height = Integer.parseInt(reader.readLine());
            tiles = new int[height][width];
            String space = " ";
            String line = null;
            // Read the each number from the file, convert it to Integer and set it to Tiles array
            for (int row = 0; row < height; row++) {
                line = reader.readLine();
                String[] numbers = line.split(space);
                for (int column = 0; column < width; column++) {
                    tiles[column][row] = Integer.parseInt(numbers[column]);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    // Setters

    /**
     * This method will set game object to the private game map field for having the ability to detect the key inputs from the user
     * @param game this is the game object, which is responsible for entities movement
     */
    public void setGame(Game game) {
        this.game = game;
        initializeKeys();
    }

    /**
     * This method sets the width of the game map.
     * @param width This is the width of the game map
     */
    public void setWidth(int width){
        this.width = width;
    }

    /**
     *This method sets the height of the game map.
     * @param height This is the height of the game map
     */
    public void setHeight(int height){
        this.height = height;
    }

    /**
     * This method will start the timer when the user have started the game.
     */
    public void setStartTime(){
        startTime =System.currentTimeMillis();
    }

    /**
     * This method sets the player score of the user.
     * @param score This variable is the amount of points, which user have scored on the game
     */
    public void setPlayerScore(int score){
        playerScore = score;
    }

    /**
     * This method will initilize the counts variable of the game map, which will be responsible for
     * defining the time intervals of appearing and disappearing of the bonus reward on the game map.
     * @param counter1 stores the number of counts, which will be updated in each tick
     * @param counter2 stores the number of counts, which will be updated in each tick
     */
    public void setCounts(int counter1, int counter2){
        count1 = counter1;
        count2 = counter2;
    }

    /**
     * This method sets the number of bonus reward at the indicated time interval, which can be seen on the game map.
     * @param num This is the number of bonus reward, which is allowed to be taken at each time interval
     */
    public void setNumberOfBonusRewardAtTime(int num){
        numberOfBonusRewardAtTime = num;
    }

    /**
     * This method sets the index of the bonus reward array for getting a single bonus reward from the array in each
     * time interval.
     * @param index This is the current index of the bonus reward array
     */
    public void setBonusAwardPosition(int index){
        bonusReward = bonuses.get(index);
    }

    /**
     * This method will set all the enemies path finding functionality
     */
    private void setEnemiesPathFinding() {
        for(int i=0; i < enemies.size(); i++){
            enemiesPathFinding = new PathFinding();
            enemiesPathFinding.setCollision(collisions);
            enemies.get(i).setPathFinding(enemiesPathFinding);
        }
    }


    // Getters

    /**
     * This method will return the main character object existed on the game map.
     * @return the main character object on the game map
     */
    public MainCharacter getPlayer() {
        return player;
    }

    /**
     * This method will return the amount of points the user have scored on the game.
     * @return the score of the player obtained in the game
     */
    public int getPlayerScore(){
        return playerScore;
    }

    /**
     * This method will return the list of regular rewards existed on the game map.
     * @return the list of regular rewards on the game map
     */
    public List<RegularReward> getRewards() {
        return rewards;
    }

    /**
     * This method will return the list of bonus rewards existed on the game map.
     * @return the list of bonus rewards on the game map
     */
    public List<BonusRewards> getBonuses() {
        return bonuses;
    }

    /**
     * This method will return the list of obstacles existed on the game map.
     * @return the list of barriers on the game map
     */
    public List<Barrier> getObstacles(){
        return obstacles;
    }

    /**
     * This method will return the list of enemies existed on the game map.
     * @return the list of enemies on the game map
     */
    public List<Enemy> getEnemies(){
        return enemies;
    }


    /**
     * This method will return the width of the game map.
     * @return the width of the game map
     */
    public int getWidth(){
        return width;
    }

    /**
     * This method will return the height of the game map.
     * @return the height of the game map
     */
    public int getHeight(){
        return height;
    }

    /**
     * This method will return the time, which user have played the game.
     * @return the time of the player in the game
     */
    public float getTime(){
        return time;
    }


    /**
     * Checks if all Regular Rewards are collected
     * @return boolean true if rewards array list is empty
     */
    public boolean allRegularRewardCollected(){
        if(rewards.size() == 0)
            allRewardCollected = true;
        return allRewardCollected;
    }

    /**
     * This method checks if the user has won the game
     *
     * @return boolean true if the user is winning
     */
    public boolean playerWinning() {
        int size =30;
        if(allRegularRewardCollected()) {
            if((int)player.getPositionX() / size==  positionFinishSign_x /size
                    && (int) player.getPositionY() /size == positionFinishSign_y /size) {
                time = StopWatch();
                playerWinning = true;
            }
        }
        return playerWinning;
    }

    /**
     * This method will check to see is the score below zero or not.
     * @return true if the score is below zero and false otherwise
     */
    public boolean scoreBelowZero(){
        if (getScore() < 0)
            return true;
        else
            return false;
    }

    /**
     * This method checks the collision between main character and enemies.
     * @return true if the Main character hits the enemy and false otherwise
     */
    public boolean playerHitEnemy(){
        if(collisions.checkPlayerHitEnemy(player))
            return true;
        else
            return false;
    }
    /**
     * This method checks if the game has ended
     *
     * @return boolean true if the game has ended
     */
    public boolean gameOver() {
        if(scoreBelowZero() || playerHitEnemy() ){
            gameOver = true;
        }
        return gameOver;
    }

    public int getScore(){ return playerScore; }

    /**
     * This method draws the grids on the game map
     *
     * @param g draws necessary components
     */
    public void drawTheGridLines(Graphics g) {

        for (int i = 0; i < ROWS; i++) {
            g.drawLine(0, 30 * (1 + i), game.getGameWidth(), 30 * (1 + i));
        }
        for (int i = 0; i < COLUMNS - 1; i++) {
            g.drawLine(30 * (1 + i), 30, 30 * (1 + i), game.getGameHeight());
        }

    }

    /**
     * This method draws the tiles that make up the entire game map
     *
     * @param g draws necessary components
     */
    public void drawTiles(Graphics g){
        for (int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                getTile(x,y).render(g,x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT+30);
            }
        }
    }

    /**
     * This method renders/displays all the images and visual in the game. The static entities,
     * main character, and enemies are all rendered here.
     *
     * @param g draws necessary components
     */
    public void render(Graphics g) {

        //render static entities
        renderRegularReward(g);
        renderPunishment(g);
        renderBonusReward(g);

        // render obstacles
        renderBarriers(g);
        drawStartExitSign(g);


        renderEnemies(g);
        player.render(g);
        // Draws the score board
        drawScoreBoard(g);
    }

    /**
     *  Draws the finash and start sign into the screen
     * @param g draw images into the screen
     */
    private void drawStartExitSign(Graphics g) {
        g.drawImage(ImageManager.startSign,positionStartSign_x,positionStartSign_y,30,30,null);
        g.drawImage(ImageManager.finishSign,positionFinishSign_x,positionFinishSign_y,30,30,null);
    }

    /**
     * This method draws the images based on their positions on the game map.
     * @param g draw the images into the screen
     */
    private void renderEnemies(Graphics g) {
        for(int i =0; i< enemies.size(); i++){
            enemies.get(i).render(g);
        }
    }

    /**
     * This method loops through the barriers and renders each based on its set positions.
     *
     * @param g draws the images into the screen
     */
    private void renderBarriers(Graphics g) {
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).render(g);
        }
    }

    /**
     * The method renderRegularReward loops through the rewards arraylist and renders each reward.
     *
     * @param g draws necessary components
     */
    public void renderRegularReward(Graphics g){
        for (int i = 0; i < rewards.size(); i++) {
            rewards.get(i).render(g);
        }
    }

    /**
     * The method renderPunishment loops through the punishments arraylist and renders each punishment
     * @param g draws necessary components
     */
    public void renderPunishment(Graphics g){
        for (int i = 0; i < punishments.size(); i++) {
            punishments.get(i).render(g);
        }
    }

    /**
     * This method will give five second time intervals for appearing and disappearing of the bonus reward
     */
    public void updateCount(){
        if((int) StopWatch() % 2 == 0 && (int) StopWatch() % 5 == 0){
            if(count1 == count2 + 1) {
                count2++;
            }
        }
        else if( (int) StopWatch() % 5 == 0 && StopWatch() > 1){
            if(count1 == count2) {
                count1++;
            }
        }
    }

    /**
     * Check if the Bonus Award is visible or not
     * @return true if the bonus award is visible
     */
    public boolean visible(){
        if (count1 == count2 + 1){
            return true;
        }
        else
            return false;
    }

    /**
     * Check if all the Bonus Award is collected
     * @return true if the all bonus award is collected by the character
     */
    public boolean allBonusRewardCollected(){
        if(bonuses.size() == 0)
            return true;
        else
            return false;
    }
    /**
     * The method renderBonusReward loops through the bonuses arraylist and renders each bonus reward.
     * To make the images appear and disappear at certain increments, there are conditions using a counter
     * for when a bonus reward will appear and disappear. There is a condition for each bonus reward.
     *
     * @param g draws necessary components
     */
    public void renderBonusReward(Graphics g) {
        if(allBonusRewardCollected()){
            return;
        }
        // Update count for giving time intervals in order to appear or disappear the Bonus reward
        updateCount();
         if (visible()) {
            if (existedBonusReward()) {
                bonusReward.render(g);
            }
        }
    }

    /**
     * This method is used to check the collision between the player and any other static entity. When the
     * method is it loops through the arraylists of every static entity and increments the players score
     * accordingly when at collision is detected between the static entity's position and the player's
     * position. Whe a collision is detected the static entity is relocated to a position off screen making
     * it disappear from the game board.
     */
    public void updateThePlayerScore(){
       checkCollisionRegularAward();
       checkCollisionPunishment();
       checkCollisionBonusAward();
    }

    /**
     * This method will check if the collision have been detected between the main character and regular rewards.
     * If the main character collides with regular reward the points will be added to the score.
     */
    public void checkCollisionRegularAward(){
        for(int i = 0; i < rewards.size(); i++){
            if(collisions.checkStaticEntityCollision(rewards.get(i), player)){
                playerScore = playerScore + 10;
                rewards.remove(rewards.get(i));
            }
        }

    }

    /**
     * This method checks whether the Main character have collided with the punishments since it will deduct the main character's
     * score if the collision have been detected.
     */
    public void checkCollisionPunishment(){
        for(int i = 0; i < punishments.size(); i++){
            if(collisions.checkStaticEntityCollision(punishments.get(i), player)){
                playerScore = playerScore - 10;
                punishments.remove(punishments.get(i));
            }
        }

    }

    /**
     * Checks if the character have taken the Bonus award at the five secend time interval
     * @return true if character have not collected the bonus award while it is visible to the character
     */
    public boolean existedBonusReward(){
        if (visible() && numberOfBonusRewardAtTime == 1){
            return true;
        } else if(!visible()){
            numberOfBonusRewardAtTime = 1;
            getBonusAwardNewPosition();
            return false;
        }
        return false;
    }


    /**
     * Check if the main character have collided with the bonus award
     */
    public void checkCollisionBonusAward(){
        if(allBonusRewardCollected()){
            return;
        }
        else if(existedBonusReward() && visible()){
            if(collisions.checkStaticEntityCollision(bonusReward, player)){
                playerScore = playerScore + 20;
                removeBonusAward(bonusReward);
                getBonusAwardNewPosition();
                numberOfBonusRewardAtTime--;
            }
        }

    }

    /**
     * This method creates the black bar at the top of the game board to display the score and timer,
     * also called the score board
     *
     * @param g the image to be drawn for the scoreboard
     */
    // Draws the score and time into the screen
    private void drawScoreBoard (Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0,0,900,30);
        g.setColor(Color.white);
        Font font = new Font("arial",Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Score: " + playerScore, 10, 23);
        g.drawString("Time: " + StopWatch(), 750, 23);
    }


    /**
     * This method will return the tile based on the indicated row and column, which is useful for rendering tiles object on the
     * screen.
     * @param x this the row of the tiles matrix
     * @param y this the column of the tiles matrix
     * @return the tile from the tiles array based on the specified row and column
     */
    public Tile getTile(int x, int y) {
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
            return Tile.dirtTile;
        return t;
    }


    /**
     * This method initializes the key input of the game for the Main characters movement
     */
    private void initializeKeys() {
        game.getScreen().getJFrame().addKeyListener(keyInput);
    }

    /**
     * This method retrieves the key inputs from the user
     *
     * @return KeyInput inputted key from the user
     */
    public static KeyInput getKeyInput() {
        return keyInput;
    }

    /**
     * This method will update player and enemies movement on each tick
     */
    public void tick() {
        keyInput.tick();
        player.tick();
        tickEnemies();

    }

    /**
     * This method updates the movement of the enemies each tick.
     */
    public void tickEnemies() {
        for (int i=0; i< enemies.size(); i++){
            enemies.get(i).setCharacterPositions(player.getPositionX(),player.getPositionY());
            enemies.get(i).tick();
        }
    }

    /**
     * This method retrieves the skeleton of the game map ....
     * @return
     */
    public int[][] getTiles() {
        return tiles;
    }

    /**
     * This method contains the timer's time in seconds
     *
     * @return float the timer's time in seconds
     */
    public float StopWatch(){ return (System.currentTimeMillis() - startTime) / 1000f; }

}



