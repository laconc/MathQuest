package team.mathquest.model;

/**
 * A level represents a stage with each having a unique number of enemies.
 *
 */
public class Level {
    
    private Player player;
    private Enemy enemy;
    private int currentEnemy;
    private int level;
    
    public Level (int level) {
        this.level = level;
        player = new Player();
        enemy = new Enemy();
        currentEnemy = 1;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the current enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }
    
    /**
     * generates a new enemy
     */
    public void newEnemy() {
        enemy = new Enemy();
        currentEnemy += 1;
    }
    
    /**
     * @return the current enemy's number out of the total in this level
     */
    public int getEnemyNumber() {
        return currentEnemy;
    }
    
    /**
     * @return the total number of enemies on the current level
     */
    public int getEnemyPackSize() {
        return ((level * 2) + 1);
    }
    
    /**
     * @return the current level
     */
    public int getLevelNumber() {
        return level;
    }
    
    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
