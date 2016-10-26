package team.mathquest;

import javafx.event.ActionEvent;
import team.mathquest.model.Account;
import team.mathquest.model.Controller;

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
    private Button playGameButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button statsButton;
    @FXML
    private Button adminToolsButton;
    
    /**
     * @param account the account to set
     */
    @Override
    public void setAccount(Account account) {
        super.setAccount(account);
        nameLabel.setText(account.getName());
    }
    
    /**
     * The actions performed when the options button is pressed.
     *
     */
    @FXML
    private void handleOptionsButtonAction(ActionEvent event) {
        super.getMainApp().showOptions(super.getAccount());
    }
}
