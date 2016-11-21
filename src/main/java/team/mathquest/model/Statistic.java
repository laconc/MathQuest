package team.mathquest.model;

import java.util.List;

/**
 * Performs all of the calculations for the user statistics.
 *
 */
public class Statistic {
    
    private List<Session> gameHistory;
    
    public Statistic (List<Session> gameHistory) {
        this.gameHistory = gameHistory;
    }
}
