package team.mathquest.model;

import team.mathquest.model.Option.Difficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathProblem {
    
    /**
     * The possible problem types.
     */
    public enum ProblemType {
	ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION;
    }
    
    private Random random = new Random();
    private ProblemType type;
    private Difficulty difficulty;
    private int answer;
    private int topValue;
    private int bottomValue;
    private List<ProblemType> problemTypeChoices = new ArrayList<>();
    
    public MathProblem(Difficulty difficulty, boolean addFlag, boolean subFlag,
            boolean mulFlag, boolean divFlag) {
        
        this.difficulty = difficulty;
        
        if (addFlag)
            problemTypeChoices.add(ProblemType.ADDITION);
        if (subFlag)
            problemTypeChoices.add(ProblemType.SUBTRACTION);
        if (mulFlag)
            problemTypeChoices.add(ProblemType.MULTIPLICATION);
        if (divFlag)
            problemTypeChoices.add(ProblemType.DIVISION);
    }
    
    public void newProblem() {
        // randomly selects a problem type
        type = problemTypeChoices.get(random.nextInt(problemTypeChoices.size()));
        
        // randomly selects 2 numbers based on the difficulty and
        // figures out the answer
        switch (type) {
            case ADDITION:
                switch (difficulty) {
                    case EASY:
                        topValue = random.nextInt(10) + 1;
                        bottomValue = random.nextInt(10) + 1;
                        break;
                    case NORMAL:
                        topValue = random.nextInt(50) + 1;
                        bottomValue = random.nextInt(50) + 1;
                        break;
                    case HARD:
                        topValue = random.nextInt(100) + 1;
                        bottomValue = random.nextInt(100) + 1;
                }
                answer = topValue + bottomValue;
                break;
                
            case SUBTRACTION:
                switch (difficulty) {
                    case EASY:
                        answer = random.nextInt(10) + 1;
                        bottomValue = random.nextInt(10) + 1;
                        break;
                    case NORMAL:
                        answer = random.nextInt(50) + 1;
                        bottomValue = random.nextInt(50) + 1;
                        break;
                    case HARD:
                        answer = random.nextInt(100) + 1;
                        bottomValue = random.nextInt(100) + 1;
                }
                topValue = bottomValue + answer;
                break;
                
            case MULTIPLICATION:
                switch (difficulty) {
                    case EASY:
                        topValue = random.nextInt(4) + 1;
                        bottomValue = random.nextInt(4) + 1;
                        break;
                    case NORMAL:
                        topValue = random.nextInt(9) + 1;
                        bottomValue = random.nextInt(9) + 1;
                        break;
                    case HARD:
                        topValue = random.nextInt(12) + 1;
                        bottomValue = random.nextInt(12) + 1;
                }
                answer = topValue * bottomValue;
                break;
                
            case DIVISION:
                switch (difficulty) {
                    case EASY:
                        answer = random.nextInt(4) + 1;
                        bottomValue = random.nextInt(4) + 1;
                        break;
                    case NORMAL:
                        answer = random.nextInt(9) + 1;
                        bottomValue = random.nextInt(9) + 1;
                        break;
                    case HARD:
                        answer = random.nextInt(12) + 1;
                        bottomValue = random.nextInt(12) + 1;
                }
                topValue = bottomValue * answer;
        }
    }
    
    public ProblemType getProblemType() {
        return type;
    }
    
    public int getTopValue() {
        return topValue;
    }
    
    public int getBottomValue() {
        return bottomValue;
    }
    
    public int getAnswer() {
        return answer;
    }
}
