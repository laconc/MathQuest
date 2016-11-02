package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for the Main Menu screen.
 *
 */
public class MenuController extends Controller {

    @FXML
    private Label nameLabel;
    @FXML
    private Button playGameButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button adminToolsButton;
    
    @Override
    public void start(Account account) {
        
        super.setAccount(account);
        nameLabel.setText(account.getName());
        
        if (getAccount().getType() == 'a') { // admin-specific code
            playGameButton.setDisable(true);
            optionsButton.setDisable(true);
        }
        
        else if (getAccount().getType() == 'u') // user-specific code
            adminToolsButton.setDisable(true);
    }
    
    /**
     * Takes the user to the Options screen.
     *
     */
    @FXML
    private void handleOptionsButtonAction(ActionEvent event) {
        getMainApp().showOptions(getAccount());
    }
    /**
     * Takes the user to the Admin Tools screen.
     *
     */
    @FXML
    private void handleAdminToolsButtonAction(ActionEvent event) {
        getMainApp().showAdminTools(getAccount());
    }
    
    /**
     * Takes the user to the Level Select screen.
     *
     */
    @FXML
    private void handlePlayGameButtonAction(ActionEvent event) {
        getMainApp().showLevelSelect(getAccount());
    }
    
    /**
     * Takes the user to the Statistics screen.
     *
     */
    @FXML
    private void handleStatsButtonAction(ActionEvent event) {
        getMainApp().showStats(getAccount());
    }
}
