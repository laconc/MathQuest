package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.ReaderWriter;
import team.mathquest.model.Statistic;
import team.mathquest.model.User;

import java.util.ArrayList;
import java.util.List;
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
    private ChoiceBox usernameChoicebox;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label currentLevelLabel;
    @FXML
    private Label levelsClearedLabel;
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
    
    private ReaderWriter rw = new ReaderWriter();
    private Statistic stats;
    
    @Override
    public void start(Account account) {
        super.start(account);
        
        if (getAccount().getType() == 'a') {
            usernameChoicebox.setVisible(true);
            // fills the choice box with all of the user accounts
            usernameChoicebox.getItems().add("Select User");
            usernameChoicebox.getItems().addAll(rw.readUserList());
            usernameChoicebox.getSelectionModel().selectFirst();
            // add listener for choice change
            // onchange: drawLabels(getAccount());
        }
        else if (getAccount().getType() == 'u') {
            usernameLabel.setVisible(true);
            usernameLabel.setText("User: " + getAccount().getName());
            stats = new Statistic(((User) getAccount()).getGameHistory());
            drawLabels(((User) getAccount()));
        }
    }
    
    private void drawLabels(User account) {
        currentLevelLabel.setText("Current Stage: "
                + Integer.toString(account.getLevel()));
    }
    
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
    }    
}
