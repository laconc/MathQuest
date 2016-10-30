package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the statistics screen.
 *
 */
public class StatsController extends Controller {
    
    @Override
    public void start(Account account) {
        super.start(account);
    }
    
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
    }    
}
