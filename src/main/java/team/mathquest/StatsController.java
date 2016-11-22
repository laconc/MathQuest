package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.ReaderWriter;
import team.mathquest.model.Statistic;
import team.mathquest.model.User;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Controller for the Statistics screen.
 *
 */
public class StatsController extends Controller {
    
    @FXML
    private ChoiceBox usernameChoiceBox;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label gamesPlayedLabel;
    @FXML
    private Label levelsCompletedLabel;
    @FXML
    private Label totalCorrectLabel;
    @FXML
    private Label totalIncorrectLabel;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private Label averageSolveTimeLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Label mostCorrectLabel;
    @FXML
    private Label leastCorrectLabel;
    @FXML
    private Label addProblemsSolvedLabel;
    @FXML
    private Label subProblemsSolvedLabel;
    @FXML
    private Label mulProblemsSolvedLabel;
    @FXML
    private Label divProblemsSolvedLabel;
    
    private List<Account> accounts = new ArrayList<>();
    private ReaderWriter rw = new ReaderWriter();
    private Statistic stats;
    
    @Override
    public void start(Account account) {
        super.start(account);
        
        if (getAccount().getType() == 'a') {
            usernameChoiceBox.setVisible(true);
            accounts = rw.readUserList();
            // fills the choice box with all of the user accounts
            usernameChoiceBox.getItems().add("Select User");
            usernameChoiceBox.getItems().addAll(accounts);
            usernameChoiceBox.getSelectionModel().selectFirst();
            addChoiceBoxListener();
        }
        else if (getAccount().getType() == 'u') {
            usernameLabel.setVisible(true);
            usernameLabel.setText("User: " + getAccount().getName());
            drawLabels(((User) getAccount()));
        }
    }
    
    private void drawLabels(User account) {
        stats = new Statistic(account.getGameHistory());
        
        gamesPlayedLabel.setText("Games Played: "
                + stats.getGamesPlayed());
        levelsCompletedLabel.setText("Stages Completed: "
                + stats.getLevelsCleared());
        totalCorrectLabel.setText("Total Correct Answers: "
                + stats.getTotalCorrect());
        totalIncorrectLabel.setText("Total Incorrect Answers: "
                + stats.getTotalMissed());
        totalTimeLabel.setText("Total Time: "
                + stats.getTotalTime());
        averageSolveTimeLabel.setText("Average Time to Solve a Problem: "
                + stats.getAverageSolveTime());
        accuracyLabel.setText("Overall Accuracy: "
                + stats.getAccuracy());
        mostCorrectLabel.setText("Most Correct Problem Type: "
                + stats.getMostCorrect());
        leastCorrectLabel.setText("Least Correct Problem Type: "
                + stats.getLeastCorrect());
        addProblemsSolvedLabel.setText("Addition Problems Solved: "
                + stats.getAddProblemsSolved());
        subProblemsSolvedLabel.setText("Subtraction Problems Solved: "
                + stats.getSubProblemsSolved());
        mulProblemsSolvedLabel.setText("Multiplication Problems Solved: "
                + stats.getMulProblemsSolved());
        divProblemsSolvedLabel.setText("Division Problems Solved: "
                + stats.getDivProblemsSolved());
    }
    
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
    }    

    private void addChoiceBoxListener() {
        usernameChoiceBox.getSelectionModel().selectedIndexProperty()
            .addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue ov, Number v, Number new_v) {
                    if (new_v.intValue() != 0)
                        drawLabels((User) accounts.get(new_v.intValue() - 1));
            }
        });
    }
}
