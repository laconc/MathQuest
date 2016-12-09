package team.mathquest.model;

import team.mathquest.model.MathProblem.ProblemType;

import java.util.List;
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 * Performs all of the calculations for the user statistics.
 *
 */
public class Statistic {
    
    private List<Session> gameHistory;
    private String[] types = { "Addition", "Subtraction",
                               "Multiplication", "Division" };

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
    
    private double calculateTotalTime() {
        double totalTime = 0;
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory)
                totalTime += session.getTotalTime();
        }
        return totalTime;
    }

    public String getTotalTime() {
        if (!gameHistory.isEmpty())
            return String.format("%.1f", calculateTotalTime()) + " seconds";
        else
            return "";
    }

    public String getAverageSolveTime() {
        if (!gameHistory.isEmpty()) {
            double averageSolveTime = calculateTotalTime()
                                    / (Double.parseDouble(getTotalCorrect())
                                    + Double.parseDouble(getTotalMissed()));
            return String.format("%.1f", averageSolveTime) + " seconds";
        }
        else
            return "";
    }

    public String getAccuracy() {
        if (!gameHistory.isEmpty()) {
            double accuracy = (Double.parseDouble(getTotalCorrect())
                    / (Double.parseDouble(getTotalCorrect())
                    + Double.parseDouble(getTotalMissed()))) * 100;
            return (String.format("%.1f", accuracy) + "%");
        }
        else
            return "";
    }

    public String getMostCorrect() {
        int[] correct = { 0, 0, 0, 0 };
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory) {
                correct[0] += session.getProblemsSolved(ProblemType.ADDITION);
                correct[1] += session.getProblemsSolved(ProblemType.SUBTRACTION);
                correct[2] += session.getProblemsSolved(ProblemType.MULTIPLICATION);
                correct[3] += session.getProblemsSolved(ProblemType.DIVISION);
            }
            
            int indexMostCorrect = 0;
            int max = correct[0];
            for (int i = 1; i < correct.length; i++) {
                if (correct[i] > max) {
                    indexMostCorrect = i;
                    max = correct[i];
                }
            }
            
            return types[indexMostCorrect];
        }
        else
            return "";
    }

    public String getLeastCorrect() {
        int[] missed = { 0, 0, 0, 0 };
        if (!gameHistory.isEmpty()) {
            for (Session session : gameHistory) {
                missed[0] += session.getProblemsMissed(ProblemType.ADDITION);
                missed[1] += session.getProblemsMissed(ProblemType.SUBTRACTION);
                missed[2] += session.getProblemsMissed(ProblemType.MULTIPLICATION);
                missed[3] += session.getProblemsMissed(ProblemType.DIVISION);
            }
            
            int indexMostCorrect = 0;
            int max = missed[0];
            for (int i = 1; i < missed.length; i++) {
                if (missed[i] > max) {
                    indexMostCorrect = i;
                    max = missed[i];
                }
            }
            
            return types[indexMostCorrect];
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
