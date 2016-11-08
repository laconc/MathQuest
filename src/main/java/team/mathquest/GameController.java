package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.Level;
import team.mathquest.model.MathProblem;
import team.mathquest.model.MathProblem.ProblemType;
import team.mathquest.model.Session;
import team.mathquest.model.User;

import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the Game screen.
 *
 */
public class GameController extends Controller {
    
    @FXML
    private AnchorPane mainAnchorPane;
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
    
    private Level level = new Level(0);
    private MathProblem problem;
    private Session session = new Session();
    private boolean isPaused = true;

    @Override
    public void start(Account account) {
        super.start(account);
        addKeyboardListener();
        session.setSessionStartTime(LocalDateTime.now());
        
        problem = new MathProblem(((User) getAccount()).getOptions().getDifficulty(),
                ((User) getAccount()).getOptions().getFlag(ProblemType.ADDITION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.SUBTRACTION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.MULTIPLICATION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.DIVISION));
        
        playerImage.setImage(level.getPlayer().getImage());
        enemyImage.setImage(level.getEnemy().getImage()); // temp
        pauseState();
    }
    
    private void pauseState() {
        isPaused = true;
        topValueLabel.setText("");
        bottomValueLabel.setText("");
        operatorLabel.setText("");
        answerField.setText("");
        displayInstructions();
    }
    
    private void runningState() {
        isPaused = false;
        closeInstructions();
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
        
        // TODO start timer
        answerField.requestFocus();
    }
    
    private void displayInstructions() {
        mainAnchorPane.setEffect(new GaussianBlur());
        // TODO display instructions in a modal
    }
    
    private void closeInstructions() {
       // TODO close the instructions modal
       mainAnchorPane.setEffect(null);
    }
    
    /**
     * Checks the user-provided answer.
     * Called when the user presses Enter & runningState = true.
     */
    @FXML
    private void validateAnswer() {
        session.incrementGamesCompleted();
        
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
    
    private void addKeyboardListener() {
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
