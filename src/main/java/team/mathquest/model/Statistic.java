package team.mathquest.model;

import team.mathquest.model.MathProblem.ProblemType;

import java.time.Duration;
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

    public String getGamesPlayed() {
        int gamesPlayed = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                gamesPlayed += session.getGamesPlayed();
            return Integer.toString(gamesPlayed);
        }
        else
            return "";
    }

    public String getLevelsCleared() {
        int levelsCompleted = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                levelsCompleted += session.getLevelsCompleted();
            return Integer.toString(levelsCompleted);
        }
        else
            return "";
    }

    public String getTotalCorrect() {
        int totalCorrect = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                totalCorrect += session.getTotalCorrect();
            
            return Integer.toString(totalCorrect);
        }
        else
            return "";
    }

    public String getTotalMissed() {
        int totalMissed = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                totalMissed += session.getTotalMissed();
            
            return Integer.toString(totalMissed);
        }
        else
            return "";
    }

    // TODO isn't returning the correct value
    public String getTotalTime() {
        long totalTime = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                totalTime += Duration.between(session.getSessionStartTime(),
                        session.getSessionEndTime()).toMinutes();
            return Long.toString(totalTime);
        }
        else
            return "";
    }

    // TODO finish this method
    public String getAverageSolveTime() {
        if (!gameHistory.isEmpty()) {
            //long averageSolveTime;
            //return Long.toString(averageSolveTime);
            return "";
        }
        else
            return "";
    }

    // TODO BUG returns 0 every time
    public String getAccuracy() {
        if (!gameHistory.isEmpty()) {
            long accuracy = (Long.parseLong(getTotalCorrect())
                    / (Long.parseLong(getTotalCorrect())
                    + Long.parseLong(getTotalMissed()))) * 100;
            return (Long.toString(accuracy) + "%");
        }
        else
            return "";
    }

    // TODO finish this method
    public String getMostCorrect() {
        if (!gameHistory.isEmpty()) {
            // for (Session session : gameHistory)
            // return Integer.toString(0);
            return "";
        }
        else
            return "";
    }

    // TODO finish this method
    public String getLeastCorrect() {
        if (!gameHistory.isEmpty()) {
            // for (Session session : gameHistory)
            // return Integer.toString(0);
            return "";
        }
        else
            return "";
    }

    public String getAddProblemsSolved() {
        int addProblemsSolved = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                addProblemsSolved += session.getProblemsSolved(ProblemType.ADDITION);
            return Integer.toString(addProblemsSolved);
        }
        else
            return "";
    }

    public String getSubProblemsSolved() {
        int subProblemsSolved = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                subProblemsSolved += session.getProblemsSolved(ProblemType.SUBTRACTION);
            return Integer.toString(subProblemsSolved);
        }
        else
            return "";
    }

    public String getMulProblemsSolved() {
        int mulProblemsSolved = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                mulProblemsSolved += session.getProblemsSolved(ProblemType.MULTIPLICATION);
            return Integer.toString(mulProblemsSolved);
        }
        else
            return "";
    }

    public String getDivProblemsSolved() {
        int divProblemsSolved = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                divProblemsSolved += session.getProblemsSolved(ProblemType.DIVISION);
            return Integer.toString(divProblemsSolved);
        }
        else
            return "";
    }
}
