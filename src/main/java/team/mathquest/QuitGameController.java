package team.mathquest;

import team.mathquest.GameController;
import team.mathquest.model.DialogBoxController;
import team.mathquest.model.ReaderWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/**
 * Controller for the Quit Game dialog box.
 *
 */
public class QuitGameController extends DialogBoxController {
    
    private ReaderWriter rw = new ReaderWriter();

    /**
     * Saves the user's progress and returns them to the main menu.
     *
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        ((GameController) getMainApp().getController()).exit();
        ((GameController) getMainApp().getController()).save();
        rw.updateUserList(getAccount(), 'u');
        getMainApp().showMainMenu(getAccount());
    }
    
    /**
     * Returns the user to the main menu without saving.
     *
     */
    @FXML
    private void handleQuitButtonAction(ActionEvent event) {
        ((GameController) getMainApp().getController()).exit();
        getMainApp().showMainMenu(getAccount());
    }
    
    /**
     * Closes this dialog box without performing any action.
     *
     */
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        getDialogStage().close();
    }
}
