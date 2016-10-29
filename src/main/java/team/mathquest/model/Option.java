package team.mathquest.model;

import java.util.HashMap;
import java.util.Map;

public class Option {
    
    /**
     * The possible values that the difficulty setting can be.
     */
    public enum Difficulty {
	EASY, NORMAL, HARD
    }
    
    private boolean locked; // prevents the user from making changes
    private Difficulty difficulty;
    private Map<String, Boolean> problemType = new HashMap<>();
    
    Option() {
        this.locked = false;
        this.difficulty = Difficulty.EASY;
        
        defaultProblemType();
    }
    
    /**
     * Fills the problemType HashMap. Addition problems are the only type
     * shown initially.
     * 
     */
    private void defaultProblemType() {
        problemType.put("add", true);
        problemType.put("sub", false);
        problemType.put("mul", false);
        problemType.put("div", false);
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
    
    /**
     * @return the state of the addition flag
     */
    public boolean getAdditionFlag() {
        return problemType.get("add");
    }
    
    /**
     * @return the state of the subtraction flag
     */
    public boolean getSubtractionFlag() {
        return problemType.get("sub");
    }
    
    /**
     * @return the state of the multiplication flag
     */
    public boolean getMultiplicationFlag() {
        return problemType.get("mul");
    }
    
    /**
     * @return the state of the division flag
     */
    public boolean getDivisionFlag() {
        return problemType.get("div");
    }
    
    /**
     * @param flag whether to show addition problems or not
     */
    public void setAdditionFlag(boolean flag) {
        if(!isLocked())
            problemType.put("add", flag);
    }
    
    /**
     * @param flag whether to show subtraction problems or not
     */
    public void setSubtractionFlag(boolean flag) {
        if(!isLocked())
            problemType.put("sub", flag);
    }
    
    /**
     * @param flag whether to show multiplication problems or not
     */
    public void setMultiplicationFlag(boolean flag) {
        if(!isLocked())
            problemType.put("mul", flag);
    }
    
    /**
     * @param flag whether to show division problems or not
     */
    public void setDivisionFlag(boolean flag) {
        if(!isLocked())
            problemType.put("div", flag);
    }
}
