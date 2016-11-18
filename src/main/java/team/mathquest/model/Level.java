package team.mathquest.model;

/**
 * A level represents a stage with each having a unique number of enemies.
 *
 */
public class Level {
    
    private Player player;
    private Enemy enemy;
    private int level;
    
    public Level (int level) {
        this.level = level;
        player = new Player();
        enemy = new Enemy();
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
    }
    
    /**
     * @return the total number of enemies on the current level
     */
    public int getTotalEnemies() {
        return ((level * 2) + 1);
    }
    
    /**
     * @return the current level
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
