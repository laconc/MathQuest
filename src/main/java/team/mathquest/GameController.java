package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.MathProblem;
import team.mathquest.model.MathProblem.ProblemType;
import team.mathquest.model.Session;
import team.mathquest.model.User;

import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller for the Game screen.
 *
 */
public class GameController extends Controller {
    
    @FXML
    private Label topValueLabel;
    @FXML
    private Label bottomValueLabel;
    @FXML
    private Label operatorLabel;
    @FXML
    private Label enemyNumberLabel;
    @FXML
    private Label stageNumberLabel;
    @FXML
    private Label timerLabel;
    @FXML
    private TextField answerField;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView enemyImage;
    
    private MathProblem problem;
    private Session session = new Session();
    private boolean isPaused = true;

    @Override
    public void start(Account account) {
        super.start(account);
        addKeyboardListeners();
        session.setSessionStartTime(LocalDateTime.now());
        
        problem = new MathProblem(((User) getAccount()).getOptions().getDifficulty(),
                ((User) getAccount()).getOptions().getFlag(ProblemType.ADDITION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.SUBTRACTION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.MULTIPLICATION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.DIVISION));
        
        pauseState();
    }
    
    private void pauseState() {
        isPaused = true;
        topValueLabel.setText("");
        bottomValueLabel.setText("");
        operatorLabel.setText("");
        answerField.setText("");
        // TODO display instructions
    }
    
    private void runningState() {
        isPaused = false;
        problem.newProblem();
        
        topValueLabel.setText(Integer.toString(problem.getTopValue()));
        bottomValueLabel.setText(Integer.toString(problem.getBottomValue()));
        
        switch(problem.getProblemType()) {
            case ADDITION:
                operatorLabel.setText("+");
                break;
            case SUBTRACTION:
                operatorLabel.setText("-");
                break;
            case MULTIPLICATION:
                operatorLabel.setText("x");
                break;
            case DIVISION:
                operatorLabel.setText("÷");
        }
        
        // start timer
    }
    
    /**
     * Checks the user-provided answer.
     * Called when the user presses Enter & runningState = true.
     */
    @FXML
    private void validateAnswer() {
        session.incrementGamesCompleted();
        
        // if executes if the user-provided answer is correct
        if (Integer.parseInt(answerField.getText()) == problem.getAnswer()) {
            session.incrementProblemsSolved(problem.getProblemType());
            // TODO reduce Enemy HP & flash correct image
        }
        else {
            session.incrementProblemsMissed(problem.getProblemType());
            // TODO reduce Player HP & flash wrong image
        }
        pauseState();
    }
    
    private void addKeyboardListeners() {
        getMainApp().getMainStage().addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER) {
                if (isPaused)
                    runningState();
                else
                    validateAnswer();
            }
        });
    }
    
    /**
     * Displays the Quit Game dialog box.
     *
     */
    @FXML
    private void handleQuitButtonAction(ActionEvent event) {
        getMainApp().showQuitGame(getAccount());
        // TODO pass session to: session.setSessionEndTime(LocalDateTime.now()) & save;
    }
}
