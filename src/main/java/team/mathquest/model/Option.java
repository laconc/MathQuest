package team.mathquest.model;

import java.util.HashMap;
import java.util.Map;
import team.mathquest.model.MathProblem.ProblemType;

public class Option {
    
    /**
     * The possible values that the difficulty setting can be.
     */
    public enum Difficulty {
	EASY, NORMAL, HARD
    }
    
    private String playerName;
    private boolean locked; // prevents the user from making changes
    private Difficulty difficulty;
    private Map<String, Boolean> problemType = new HashMap<>();
    
    Option() {
        this.playerName = "Seamus"; // default name
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
        problemType.put("addition", true);
        problemType.put("subtraction", false);
        problemType.put("multiplication", false);
        problemType.put("division", false);
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
     * @param type the type of question problem
     * @return the state of the requested flag
     */
    public boolean getFlag(ProblemType type) {
        switch (type) {
                case ADDITION:
                    return problemType.get("addition");
                case SUBTRACTION:
                    return problemType.get("subtraction");
                case MULTIPLICATION:
                    return problemType.get("multiplication");
                case DIVISION:
                    return problemType.get("division");
                default:
                    return false;
            }
    }
    
    /**
     * @param type the type of question problem
     * @param flag whether to show addition problems or not
     */
    public void setFlag(ProblemType type, boolean flag) {
        if(!isLocked())
            switch (type) {
                case ADDITION:
                    problemType.put("addition", flag);
                    break;
                case SUBTRACTION:
                    problemType.put("subtraction", flag);
                    break;
                case MULTIPLICATION:
                    problemType.put("multiplication", flag);
                    break;
                case DIVISION:
                    problemType.put("division", flag);
            }
    }

    /**
     * @return the player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName the player's name to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
