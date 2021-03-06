package team.mathquest.model;

import java.time.LocalDateTime;
import team.mathquest.model.MathProblem.ProblemType;

public class Session {
    
    private LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;
    private int gamesPlayed;
    private int levelsCompleted;
    private int[] problemsSolved;
    private int[] problemsMissed;
    private double totalTime;
    
    public Session() {
        levelsCompleted = 0;
        problemsSolved = new int[4];
        problemsMissed = new int[4];
        totalTime = 0;
    }

    /**
     * @return the time the session started
     */
    public LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    /**
     * @param sessionStartTime the time the session started
     */
    public void setSessionStartTime(LocalDateTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    /**
     * @return the time the session ended
     */
    public LocalDateTime getSessionEndTime() {
        return sessionEndTime;
    }

    /**
     * @param sessionEndTime the time the session ended
     */
    public void setSessionEndTime(LocalDateTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }
    
    /**
     * @return the number of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    
    /**
     * Adds 1 to the number of games played.
     */
    public void incrementGamesPlayed() {
        gamesPlayed += 1;
    }

    /**
     * @return the number of stages completed
     */
    public int getLevelsCompleted() {
        return levelsCompleted;
    }

    /**
     * Adds 1 to the number of levels completed.
     */
    public void incrementLevelsCompleted() {
        levelsCompleted += 1;
    }

    /**
     * @param type the type of question problem
     * @return the number of problems solved of that type
     */
    public int getProblemsSolved(ProblemType type) {
        
        switch (type) {
            case ADDITION:
                return problemsSolved[0];
            case SUBTRACTION:
                return problemsSolved[1];
            case MULTIPLICATION:
                return problemsSolved[2];
            case DIVISION:
                return problemsSolved[3];
            default:
                return -1;
        }
    }
    
    /**
     * @param type the type of question problem
     */
    public void incrementProblemsSolved(ProblemType type) {
        
        switch (type) {
            case ADDITION:
                problemsSolved[0] += 1;
                break;
            case SUBTRACTION:
                problemsSolved[1] += 1;
                break;
            case MULTIPLICATION:
                problemsSolved[2] += 1;
                break;
            case DIVISION:
                problemsSolved[3] += 1;
        }
    }

    /**
     * @param type the type of question problem
     * @return the number of problems missed of that type
     */
    public int getProblemsMissed(ProblemType type) {
        
        switch (type) {
            case ADDITION:
                return problemsMissed[0];
            case SUBTRACTION:
                return problemsMissed[1];
            case MULTIPLICATION:
                return problemsMissed[2];
            case DIVISION:
                return problemsMissed[3];
            default:
                return -1;
        }
    }
    
    /**
     * @param type the type of question problem
     */
    public void incrementProblemsMissed(ProblemType type) {
        
        switch (type) {
            case ADDITION:
                problemsMissed[0] += 1;
                break;
            case SUBTRACTION:
                problemsMissed[1] += 1;
                break;
            case MULTIPLICATION:
                problemsMissed[2] += 1;
                break;
            case DIVISION:
                problemsMissed[3] += 1;
        }
    }

    /**
     * @return the total time spent solving problems
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * @param totalTime sets the total time spent solving problems
     */
    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }
    
    /**
     * @return the average time to solve a problem
     */
    public double getAverageTime() {
        return totalTime / getTotalQuestions();
    }
    
    /**
     * @return the total number of questions answered
     */
    public int getTotalQuestions() {
        return getTotalCorrect() + getTotalMissed();
    }

    /**
     * @return the total number of questions answered correctly
     */
    public int getTotalCorrect() {
        int totalCorrect = 0;
        
        for (int i = 0; i < 4; i++)
            totalCorrect += problemsSolved[i];
        
        return totalCorrect;
    }

    /**
     * @return the total number of questions answered incorrectly
     */
    public int getTotalMissed() {
        int totalMissed = 0;
        
        for (int i = 0; i < 4; i++)
            totalMissed += problemsMissed[i];
        
        return totalMissed;
    }
}
