package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the Admin Tools screen.
 *
 */
public class AdminToolsController extends Controller {

    @Override
    public void start(Account account) {
        super.start(account);
    }
    
    /**
     * Returns the user to the main menu.
     *
     */
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
    }
    
    /**
     * Saves the user's settings and returns them to the main menu.
     *
     */
    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        
        // sends the user back to the main menu
        getMainApp().showMainMenu(getAccount());
    }
}
