package team.mathquest.model;

public class Timer {
    
    private double time;
    
    public Timer (Account account) {
        switch(((User) account).getOptions().getDifficulty()) {
            case EASY:
                time = 120;
                break;
            case NORMAL:
                time = 90;
                break;
            case HARD:
                time = 60;
        }
    }
    
    /**
     * @return the time allotted
     */
    public double getTime() {
        return time;
    }
}
