package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.Level;
import team.mathquest.model.MathProblem;
import team.mathquest.model.MathProblem.ProblemType;
import team.mathquest.model.Session;
import team.mathquest.model.User;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import team.mathquest.model.Timer;

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
    private Timer timer;
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
        
        level = new Level(((User) getAccount()).getLevel());
        timer = new Timer(account);
        playerImage.setImage(getLevel().getPlayer().getImage());
        enemyImage.setImage(getLevel().getEnemy().getImage());
        
        levelNumberLabel.textProperty().bind(
                new SimpleIntegerProperty(getLevel().getLevel()).asString());
        playerNameLabel.setText(getLevel().getPlayer().getName());
        playerHpLabel.textProperty().bind(
                new SimpleIntegerProperty(getLevel().getPlayer().getHealth()).asString());
        enemyHpLabel.textProperty().bind(
                new SimpleIntegerProperty(getLevel().getEnemy().getHealth()).asString());
        enemyNameLabel.textProperty().bind(
                new SimpleStringProperty(getLevel().getEnemy().getName()));
        enemyNumberLabel.textProperty().bind(
                new SimpleIntegerProperty(getLevel().getEnemyNumber()).asString());
        enemyTotalLabel.textProperty().bind(
                new SimpleIntegerProperty(getLevel().getEnemyPackSize()).asString());
        session.setSessionStartTime(LocalDateTime.now());
        pauseState();
    }
    
    private void pauseState() {
        isPaused = true;
        topValueLabel.setText("");
        bottomValueLabel.setText("");
        operatorLabel.setText("");
        displayInstructions();
    }
    
    private void runningState() {
        isPaused = false;
        closeInstructions();
        
        timerLabel.textProperty().bind(
                new SimpleDoubleProperty(getTimer().getTimeRemaining()).asString());
        
        // TODO start timer
        // TODO while in timer loop, check isAlive for self and foe
        displayProblem();
    }
    
    private void displayInstructions() {
        subAnchorPane.setEffect(new GaussianBlur());
        instructionsLabel.setVisible(true);
    }
    
    private void closeInstructions() {
       instructionsLabel.setVisible(false);
       subAnchorPane.setEffect(null);
    }
    
    private void displayProblem() {
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
            if (Integer.parseInt(answerField.getText()) == problem.getAnswer()) {
                session.incrementProblemsSolved(problem.getProblemType());
                // answer was correct
                getLevel().getEnemy().reduceHealth();
                
                if (!getLevel().getEnemy().isAlive()) { // enemy was defeated
                    if (getLevel().getEnemyNumber() < getLevel().getEnemyPackSize()) {
                        getLevel().newEnemy();
                        displayProblem(); //TODO move this into the timer loop
                    }
                    else {
                        advanceLevel();
                        pauseState();
                    }
                }
                // TODO flash to notify that answer was correct
            } else {
                // answer was wrong
                session.incrementProblemsMissed(problem.getProblemType());
                getLevel().getPlayer().reduceHealth();
                
                if (!getLevel().getPlayer().isAlive()) { // player was defeated
                    resetLevel();
                    pauseState();
                }
                // TODO flash to notify that answer was wrong
            }
            
            answerField.setText("");
        }
    }
    
    private void advanceLevel() {
        ((User) getAccount()).setLevel(((User) getAccount()).getLevel() + 1);
        level = new Level(((User) getAccount()).getLevel());
    }
    
    private void resetLevel() {
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
     * @return the current level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }
}
