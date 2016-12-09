package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.GameTimer;
import team.mathquest.model.Level;
import team.mathquest.model.MathProblem;
import team.mathquest.model.MathProblem.ProblemType;
import team.mathquest.model.Session;
import team.mathquest.model.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    
    private Alert alert;
    private EventHandler handler;
    private Level level;
    private GameTimer timer;
    private MathProblem problem;
    private Session session = new Session();
    private boolean isPaused = true;
    private double totalTime = 0;
    private LocalTime time;
    
    @Override
    public void start(Account account) {
        super.start(account);
        addKeyboardListener();
        
        problem = new MathProblem(((User) getAccount()).getOptions().getDifficulty(),
                ((User) getAccount()).getOptions().getFlag(ProblemType.ADDITION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.SUBTRACTION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.MULTIPLICATION),
                ((User) getAccount()).getOptions().getFlag(ProblemType.DIVISION));
        
        timer = new GameTimer(account,
                (EventHandler<ActionEvent>) (ActionEvent t) -> {
                    displayOutOfTimeNotification();
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
        playerNameLabel.setText(((User) getAccount()).getOptions().getPlayerName());
        playerHpLabel.setText(Integer.toString(getLevel().getPlayer().getHealth()));
        enemyHpLabel.setText(Integer.toString(getLevel().getEnemy().getHealth()));
        enemyNameLabel.setText(getLevel().getEnemy().getName());
        enemyNumberLabel.setText("Enemy "
                + Integer.toString(getLevel().getEnemyNumber())
                + " of " + Integer.toString(getLevel().getEnemyPackSize()));
    }
    
    private void pausedState() {
        if (time != null)
            totalTime += Duration.between(time, LocalTime.now()).getSeconds();
        isPaused = true;
        topValueLabel.setText("");
        bottomValueLabel.setText("");
        operatorLabel.setText("");
        answerField.setText("");
        displayInstructions();
    }
    
    private void runningState() {
        if (isPaused) {
            isPaused = false;
            closeInstructions();
            getTimer().startTimer();
            time = LocalTime.now();
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
                // TODO animation of enemy defeated
                getLevel().newEnemy();
            }
        }
        
        // player was defeated
        else if (!getLevel().getPlayer().isAlive()) {
            pausedState();
            resetLevel();
            displayPlayerDeathNotification();
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
        displayLevelAdvanceNotification();
        ((User) getAccount()).setLevel(((User) getAccount()).getLevel() + 1);
        session.incrementLevelsCompleted();
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
        
        getMainApp().getScene().addEventHandler(KeyEvent.KEY_PRESSED, handler);
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
    }
    
    public void exit() {
        getTimer().stopTimer();
    }
    
    public void save() {
        if (time != null)
            totalTime += Duration.between(time, LocalTime.now()).getSeconds();
        session.setTotalTime(totalTime);
        session.setSessionEndTime(LocalDateTime.now());
        session.incrementGamesPlayed();
        ((User) getAccount()).addHistorySession(session);
    }
    
    // Player has died
    private void displayPlayerDeathNotification() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(getLevel().getPlayer().getName() + " Died!");
        alert.setHeaderText(null);
        alert.setContentText(((User) getAccount()).getOptions().getPlayerName()
                + " ran out of health! Try again!");
        alert.showAndWait();
    }
    
    // Player ran out of time
    private void displayOutOfTimeNotification() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Out of Time!");
        alert.setHeaderText(null);
        alert.setContentText(((User) getAccount()).getOptions().getPlayerName()
                + " needs to be faster if he wants to save his flock! Try again!");
        alert.show();
    }
    
    // Player has advanced to the next level
    private void displayLevelAdvanceNotification() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Great Job!");
        alert.setHeaderText(null);
        alert.setContentText(((User) getAccount()).getOptions().getPlayerName()
                + " has defeated all of the enemies!"
                + " Brace yourself for the next level!");
        alert.showAndWait();
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
