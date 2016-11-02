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
    private int[] questionValues = new int[2];
    private List<ProblemType> problemTypeChoices = new ArrayList<>();
    
    MathProblem(Difficulty difficulty, boolean addFlag, boolean subFlag,
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
    
    public void generateMathProblem() {
        // randomly selects a problem type
        problemTypeChoices.get(random.nextInt(problemTypeChoices.size()));
        
        // randomly selects 2 numbers based on the difficulty and
        // figures out the answer
        switch (type) {
            case ADDITION:
                // TODO: fill questionValues array + base on difficulty
                answer = questionValues[0] + questionValues[1];
                break;
            case SUBTRACTION:
                // TODO: fill questionValues array + base on difficulty
                answer = questionValues[0] - questionValues[1];
                break;
            case MULTIPLICATION:
                // TODO: fill questionValues array + base on difficulty
                answer = questionValues[0] * questionValues[1];
                break;
            case DIVISION:
                // TODO: fill questionValues array + base on difficulty
                
                // ensures that the division problems generated don't
                // have a remainder
                if ((questionValues[0] % questionValues[1]) == 0)
                    answer = questionValues[0] / questionValues[1];
                else
                    generateMathProblem();
        }
    }
    
    public ProblemType getProblemType() {
        return type;
    }
    
    public int[] getQuestionValues() {
        return questionValues;
    }
    
    public int getAnswer() {
        return answer;
    }
}
