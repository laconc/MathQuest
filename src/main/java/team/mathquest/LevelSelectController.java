package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *  Controller for the Level Select screen.
 *
 */
public class LevelSelectController extends Controller {


    @Override
    public void start(Account account) {
        super.start(account);
    }
    /**
     * Returns the user to the main menu.
     *
     */
    @FXML
    private void handleLevelOneButtonAction(ActionEvent event) {
        getMainApp().showGame(getAccount());
    }
    /**
     * Returns the user to the main menu.
     *
     */
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
    }
}
