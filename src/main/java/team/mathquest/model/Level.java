package team.mathquest.model;

public class Level {
    
    private Player player;
    private Enemy enemy;
    private int level;
    
    public Level (int level) {
        this.level = level;
        player = new Player();
        enemy = new Enemy(); // temp
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }
    
    public int getTotalEnemies() {
        return ((level * 2) + 1);
    }
}
