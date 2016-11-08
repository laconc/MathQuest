package team.mathquest;

import team.mathquest.model.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *  Controller for the Level Select screen.
 *
 */
public class LevelSelectController extends Controller {
    
    /**
     * Sends the user to the first level.
     * TODO Send user to the right level
     */
    @FXML
    private void handleLevelOneButtonAction(ActionEvent event) {
        getMainApp().showGame(getAccount());
    }
    
    /**
     * Sends the user to the second level.
     * TODO Send user to the right level
     */
    @FXML
    private void handleLevelTwoButtonAction(ActionEvent event) {
        getMainApp().showGame(getAccount());
    }
    
    /**
     * Sends the user to the third level.
     * TODO Send user to the right level
     */
    @FXML
    private void handleLevelThreeButtonAction(ActionEvent event) {
        getMainApp().showGame(getAccount());
    }
    
    /**
     * Sends the user to the level that they were at when they last played.
     * TODO Send user to the right level
     */
    @FXML
    private void handleContinueGameButtonAction(ActionEvent event) {
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
