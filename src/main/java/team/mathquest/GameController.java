package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.GameTimer;
import team.mathquest.model.Level;
import team.mathquest.model.MathProblem;
import team.mathquest.model.MathProblem.ProblemType;
import team.mathquest.model.Session;
import team.mathquest.model.User;

import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private AnchorPane subAnchorPane;
    @FXML
    private Label topValueLabel;
    @FXML
    private Label bottomValueLabel;
    @FXML
    private Label operatorLabel;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label enemyNameLabel;
    @FXML
    private Label enemyNumberLabel;
    @FXML
    private Label enemyTotalLabel;
    @FXML
    private Label playerHpLabel;
    @FXML
    private Label enemyHpLabel;
    @FXML
    private Label levelNumberLabel;
    @FXML
    private Label timerLabel;
    @FXML
    private Label instructionsLabel;
    @FXML
    private TextField answerField;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView enemyImage;
    
    private EventHandler handler;
    private Level level;
    private GameTimer timer;
    private MathProblem problem;
    private Session session = new Session();
    private boolean isPaused = true;

    @Override
    public void start(Account account) {
        super.start(account);
        addKeyboardListener();
        
        problem = new MathProblem(((User) getAccount()).getOptions().getDifficulty(),
                ((User) getAccount()).getOptions().getFlag(ProblemType.ADDITION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.SUBTRACTION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.MULTIPLICATION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.DIVISION));
        
        timer = new GameTimer(account);
        timer.setEvent((EventHandler<ActionEvent>) (ActionEvent t) -> {
            pausedState();
            resetLevel();
        });
        timerLabel.textProperty().bind(getTimer().getTimeProperty().asString());
        
        level = new Level(((User) getAccount()).getLevel());
        playerImage.setImage(getLevel().getPlayer().getImage());
        enemyImage.setImage(getLevel().getEnemy().getImage());
        
        updateLabels();
        
        session.setSessionStartTime(LocalDateTime.now());
        pausedState();
    }
    
    private void updateLabels() {
        levelNumberLabel.setText(Integer.toString(getLevel().getLevelNumber()));
        playerNameLabel.setText(getLevel().getPlayer().getName());
        playerHpLabel.setText(Integer.toString(getLevel().getPlayer().getHealth()));
        enemyHpLabel.setText(Integer.toString(getLevel().getEnemy().getHealth()));
        enemyNameLabel.setText(getLevel().getEnemy().getName());
        enemyNumberLabel.setText(Integer.toString(getLevel().getEnemyNumber()));
        enemyTotalLabel.setText(Integer.toString(getLevel().getEnemyPackSize()));
    }
    
    private void pausedState() {
        isPaused = true;
        topValueLabel.setText("");
        bottomValueLabel.setText("");
        operatorLabel.setText("");
        answerField.setText("");
        // add time used to 'total time' for the session
        displayInstructions();
    }
    
    private void runningState() {
        // the first time around
        if (isPaused) {
            isPaused = false;
            closeInstructions();
            getTimer().startTimer();
        }
        
        newProblem();
        
        // enemy was defeated
        if (!getLevel().getEnemy().isAlive()) {
            // last enemy was defeated
            if (getLevel().getEnemyNumber() == getLevel().getEnemyPackSize()) {
                pausedState();
                advanceLevel();
            }
            // more enemies remain
            else {
                // animation of enemy defeated
                getLevel().newEnemy();
            }
        }
        
        // player was defeated
        else if (!getLevel().getPlayer().isAlive()) {
            pausedState();
            resetLevel();
        }
        
        updateLabels();
    }
    
    private void displayInstructions() {
        subAnchorPane.setEffect(new GaussianBlur());
        instructionsLabel.setVisible(true);
    }
    
    private void closeInstructions() {
       instructionsLabel.setVisible(false);
       subAnchorPane.setEffect(null);
    }
    
    private void newProblem() {
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
        
        answerField.requestFocus();
    }
    
    /**
     * Checks the user-provided answer.
     * Called when the user presses Enter & runningState = true.
     */
    @FXML
    private void validateAnswer() {
        if (isValid()) {
            // answer was correct
            if (Integer.parseInt(answerField.getText()) == problem.getAnswer()) {
                session.incrementProblemsSolved(problem.getProblemType());
                getLevel().getEnemy().reduceHealth();
                // TODO flash to notify that answer was correct
            }
            // answer was wrong
            else {
                session.incrementProblemsMissed(problem.getProblemType());
                getLevel().getPlayer().reduceHealth();
                // TODO flash to notify that answer was wrong
            }
            runningState();
        }
        
        answerField.setText("");
    }
    
    private void advanceLevel() {
        getTimer().stopTimer();
        ((User) getAccount()).setLevel(((User) getAccount()).getLevel() + 1);
        level = new Level(((User) getAccount()).getLevel());
    }
    
    private void resetLevel() {
        getTimer().stopTimer();
        level = new Level(((User) getAccount()).getLevel());
    }
    
    private void addKeyboardListener() {
        handler = (EventHandler<KeyEvent>) (KeyEvent key) -> {
            if (key.getCode() == KeyCode.ENTER) {
                if (isPaused)
                    runningState();
                else
                    validateAnswer();
            }
        };
        
        getMainApp().getMainStage().addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }

    private boolean isValid() {
        // will return true if the values are all integers and the length is
        // greater than zero
        try {
            Integer.parseInt(answerField.getText());
            return answerField.getText().length() > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Displays the Quit Game dialog box.
     *
     */
    @FXML
    private void handleQuitButtonAction(ActionEvent event) {
        getMainApp().showQuitGame(getAccount());
        // getMainApp().getMainStage().addEventHandler(KeyEvent.KEY_PRESSED, handler);
        // TODO pass session to: session.setSessionEndTime(LocalDateTime.now()) & save;
    }

    /**
     * @return the current level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return the timer
     */
    public GameTimer getTimer() {
        return timer;
    }
}
