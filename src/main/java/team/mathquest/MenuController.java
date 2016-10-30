package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for the main menu screen.
 *
 */
public class MenuController extends Controller {

    @FXML
    private Label nameLabel;
    @FXML
    private Button optionsButton;
    @FXML
    private Button adminToolsButton;
    
    /**
     * @param account the account to set
     */
    @Override
    public void start(Account account) {
        
        super.setAccount(account);
        nameLabel.setText(account.getName());
        
        if (getAccount().getType() == 'a') // admin-specific code
            optionsButton.setDisable(true);
        
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
    private void handleAdminButtonAction(ActionEvent event) {
        getMainApp().showAdminTools(getAccount());
    }
    
    /**
     * Takes the user to the Level select screen.
     *
     */
    @FXML
    private void handlePlayGameButtonAction(ActionEvent event) {
        getMainApp().showLevelSelect(getAccount());
    }
    
    /**
     * Takes the user to the Level select screen.
     *
     */
    @FXML
    private void handleStatsButtonAction(ActionEvent event) {
        getMainApp().showStats(getAccount());
    }
}
