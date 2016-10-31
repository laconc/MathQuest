package team.mathquest.model;

import java.util.ArrayList;
import java.util.Random;
import team.mathquest.model.Option.Difficulty;

public class MathProblem {
    
    /**
     * The possible problem types.
     */
    public enum ProblemType {
	ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION;
    }
    
    Random random = new Random();
    private ProblemType problemType;
    private int answer;
    int[] integers = new int[2];
    private ArrayList<ProblemType> problemTypeChoices = new ArrayList<>();
    
    MathProblem(Difficulty difficulty, boolean addFlag, boolean subFlag,
            boolean mulFlag, boolean divFlag) {
        
        if (addFlag)
            problemTypeChoices.add(ProblemType.ADDITION);
        if (subFlag)
            problemTypeChoices.add(ProblemType.SUBTRACTION);
        if (mulFlag)
            problemTypeChoices.add(ProblemType.MULTIPLICATION);
        if (divFlag)
            problemTypeChoices.add(ProblemType.DIVISION);
        
        generateMathProblem();
            
    }
    
    private void generateMathProblem() {
        // randomly selects a problem type
        problemTypeChoices.get(random.nextInt(problemTypeChoices.size()));
        
        // randomly selects 2 numbers based on the difficulty and
        // figures out the answer
        switch (problemType) {
            case ADDITION:
                // TODO: fill integers array + base on difficulty
                answer = integers[0] + integers[1];
                break;
            case SUBTRACTION:
                // TODO: fill integers array + base on difficulty
                answer = integers[0] - integers[1];
                break;
            case MULTIPLICATION:
                // TODO: fill integers array + base on difficulty
                answer = integers[0] * integers[1];
                break;
            case DIVISION:
                // TODO: fill integers array + base on difficulty
                
                // ensures that the division problems generated don't
                // have a remainder
                if ((integers[0] % integers[1]) == 0)
                    answer = integers[0] / integers[1];
                else
                    generateMathProblem();
        }
    }
    
    private ProblemType getProblemType() {
        return problemType;
    }
    
    private int[] getQuestion() {
        return integers;
    }
    
    private int getAnswer() {
        return answer;
    }
}
