package team.mathquest.model;

public class Option {
    // TODO: The type-of-problem settings, maybe a 1x4 array
    
    public enum Difficulty {
	EASY, MEDIUM, HARD
    }
    
    private boolean locked; // locks the settings
    private Difficulty difficulty;
    
    Option() {
        this.locked = false;
        this.difficulty = Difficulty.EASY;
    }

    /**
     * @return the state of the 'locked' boolean
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * @param locked sets the 'locked' boolean
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * @return the difficulty setting
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty sets the difficulty setting
     */
    public void setDifficulty(Difficulty difficulty) {
        if(!isLocked())
            this.difficulty = difficulty;
    }
}
