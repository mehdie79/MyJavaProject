package Characters.PathFinding;

import CollisionManager.Collisions;


import java.util.*;

/**
 *<H1> Path finding functionality</H1>
 *  This class is responsible for enemies path finding feature since with obtaining the positions of the enemy and target (Rabbit)
 *  it will populate the path field with the shortest path, which enemy by following the path can reach the target.
 */
public class PathFinding {
    private int[][] maze;
    private Collisions collisions;
    private LinkedList<Position> path= null;
    private Position enemy_position;
    private Position target_position;
    private Position going_enemy_position = null;
    private int current_enemyPosition_x;
    private int current_enemyPosition_y;

    /**
     * This method will initialize the position x and y of the enemy while converting it to tiles.
     * @param position_x This is the position x of the enemy
     * @param position_y This is the position y of the enemy
     */
    private void initializedEnemyPosition(double position_x,double position_y){
        this.enemy_position = new Position(convertToTiles(position_x),convertToTiles(position_y));
    }

    /**
     *This method will initialize the position x and y of the target while converting it to tiles.
     * @param position_x This is the position x of the target
     * @param position_y This is the position y of the target
     */
    private void initializedTargetPosition(double position_x,double position_y){
        this.target_position = new Position(convertToTiles(position_x),convertToTiles(position_y));
    }

    /**
     * Based on the BFS algorithm to find the shortest path between the enemy position and the character(Target)
     *<p>
     * This method finds the shortest path with the use of pathfinding algorithm by populating the
     * LinkedList of positions with the path between target and enemy
     *</p>
     * @param position_xEnemy the current x position of the enemy
     * @param position_yEnemy the current y position of the enemy
     * @param target_x the x position of the target
     * @param target_y the y position of the enemy
     */
    public void findPath(double position_xEnemy,double position_yEnemy,double target_x,double target_y){
        maze = collisions.getMapData();
        /* we have to reduce one from position y each time since our maze is 30 pixel down the screen */
        initializedEnemyPosition(position_xEnemy,position_yEnemy);
        initializedTargetPosition(target_x,target_y);

        if(maze[enemy_position.getPosition_X()][enemy_position.getPosition_Y()-1] != 0 || maze[target_position.getPosition_X()][target_position.getPosition_Y()-1] !=0){
            return;
        }

        setCurrentEnemy_Integer(position_xEnemy,position_yEnemy);
        path = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        initializedVisitedToFalse(visited);
        Queue<Position> queue = new LinkedList<>();
        queue.add(enemy_position);
        int height = maze.length;
        int width = maze[0].length;
        Position destination= null;
        Position currentPosition;
        // BFS search for reaching the target
        while(!queue.isEmpty()){
            currentPosition = queue.poll();
            if(isEqual(currentPosition,target_position)){
                destination= currentPosition;
                break;
            }
            // Move right
            visit(maze,queue,width,height,currentPosition.getPosition_X()+1,currentPosition.getPosition_Y()-1,currentPosition,visited);
            // Move left
            visit(maze,queue,width,height,currentPosition.getPosition_X()-1,currentPosition.getPosition_Y()-1,currentPosition,visited);
            // Move up
            visit(maze,queue,width,height,currentPosition.getPosition_X(),currentPosition.getPosition_Y()+1-1,currentPosition,visited);
            // Move down
            visit(maze,queue,width,height,currentPosition.getPosition_X(),currentPosition.getPosition_Y()-1-1,currentPosition,visited);

            }
        if(destination == null){
            path=null;
            return;
        }
        else{
            currentPosition = destination;
            do{
                path.addFirst(currentPosition);
            }while((currentPosition = currentPosition.getParent()) != null);

            setEnemyPositionGoing();

        }
    }

    /**
     * This will set the current enemy position on the map in integer
     * @param position_xEnemy This the position x of the enemy on the map
     * @param position_yEnemy This the position y of the enemy on the map
     */
    private void setCurrentEnemy_Integer(double position_xEnemy, double position_yEnemy) {
        this.current_enemyPosition_x = (int) position_xEnemy;
        this.current_enemyPosition_y = (int) position_yEnemy;
    }

    /**
     * This method will set the position that enemy must go in order to find the target.
     */
    private void setEnemyPositionGoing() {

        if(path != null){
            if(path.size() == 1){
                this.going_enemy_position = path.get(0);
            }
            else if(path.size() != 0) {
                if (current_enemyPosition_x == path.get(0).getPosition_X_Integer()
                        && current_enemyPosition_y == path.get(0).getPosition_Y_Integer()) {
                        this.going_enemy_position = path.get(1);
                    }
                }
        }
        else
            going_enemy_position = null;

    }

    /**
     * This method checks if the enemy have already visited the going position since it will return if its visited and it will
     * populate the queue position with the going position otherwise.
     * @param maze contains the ID of wall and dirt tile in a 2D array, which is the information of the whole map
     * @param queue contains the path between enemy and Main character
     * @param width is the width of the map
     * @param height is the height of the map
     * @param row the going x position of the enemy, which is indicated by row of the maze matrix
     * @param column the going y position of the enemy, which is indicated by column of the maze matrix
     * @param parent the position that enemy has already been
     * @param visited a 2D array, which contains all the positions in the maze that have been visited
     */
    private void visit(int[][] maze, Queue<Position> queue, int width,int height,int row, int column, Position parent,boolean[][] visited) {
        if(row <0 || row >= width || column < 0 || column >= height || maze[row][column] == 1 || visited[row][column]){
            return;
        }
        visited[row][column]=true;
        Position adjacent = new Position(row,column+1);
        adjacent.setParent(parent);
        queue.add(adjacent);
    }

    /**
     * This will initialize the 2D visited array to false.
     * @param visited This is the 2D array, which contains the information on positions of the map, which have been visited based on the BFS algorithm
     *
     */
    private void initializedVisitedToFalse(boolean[][] visited) {
        for(int i= 0; i < visited.length; i++){
            for(int j=0; j< visited[0].length; j++){
                visited[i][j] = false;
            }
        }
    }

    /**
     * Checks to see if the two passed positions whether are equal or not.
     * @param position1 This is the position object containing the x and y of an entity
     * @param position2 This is the position object containing the x and y of an entity
     * @return true if the position x and y of the two positions are equal and false otherwise
     */
    boolean isEqual(Position position1,Position position2){
        if(position1.getPosition_X() == position2.getPosition_X()
                && position1.getPosition_Y() == position2.getPosition_Y()){
            return true;
        }
        else
            return false;
    }

    /**
     * This will convert the passed position (either x or y) in terms of tiles array.
     * @param position This is either position x or y of an entity on the map
     * @return the converted passed position in terms of tiles
     */
    public int convertToTiles (double position){
        return (int) position / 30;
    }

    /**
     * This will set the collisions functionality to the path finding class since we need to find the path, which
     * has no walls on it.
     * @param collision
     */
    public void setCollision(Collisions collision){
        this.collisions = collision;
    }

    /**
     * This will return the position, which enemy must go.
     * @return the position that the enemy most move
     */
    public Position getGoing_enemy_position(){
      return going_enemy_position;
    }

    /**
     * This will return the path of positions between two entities on the map.
     * @return the path between two entities defined on the game map
     */
    public LinkedList<Position> getPath(){
        return path;
    }

}
