package team.mathquest;

import team.mathquest.model.Controller;
import team.mathquest.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *  Controller for the Level Select screen.
 *
 */
public class LevelSelectController extends Controller {
    
    /**
     * Sends the user to the level that they were at when they last played.
     *
     */
    @FXML
    private void handleContinueGameButtonAction(ActionEvent event) {
        getMainApp().showGame(getAccount());
    }
    
    /**
     * Sends the user to the first level.
     *
     */
    @FXML
    private void handleLevelOneButtonAction(ActionEvent event) {
        ((User) getAccount()).setLevel(1);
        getMainApp().showGame(getAccount());
    }
    
    /**
     * Sends the user to the second level.
     *
     */
    @FXML
    private void handleLevelTwoButtonAction(ActionEvent event) {
        ((User) getAccount()).setLevel(2);
        getMainApp().showGame(getAccount());
    }
    
    /**
     * Sends the user to the third level.
     *
     */
    @FXML
    private void handleLevelThreeButtonAction(ActionEvent event) {
        ((User) getAccount()).setLevel(3);
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
