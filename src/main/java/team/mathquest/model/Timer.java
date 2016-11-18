package team.mathquest.model;

public class Timer {
    
    private double time;
    
    public Timer (Account account) {
        switch(((User) account).getOptions().getDifficulty()) {
            case EASY:
                time = 240;
                break;
            case NORMAL:
                time = 180;
                break;
            case HARD:
                time = 120;
        }
    }
    
    /**
     * @return the time allotted
     */
    public double getTimeRemaining() {
        return time;
    }
}
