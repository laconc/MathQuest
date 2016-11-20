package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.DialogBoxController;
import team.mathquest.model.ReaderWriter;
import team.mathquest.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the Edit User dialog box.
 *
 */
public class EditUserController extends DialogBoxController {
    
    private ReaderWriter rw = new ReaderWriter();
    
    @Override
    public void start (Account account) {
        super.start(account);
        
        if (getAccount().getType() == 'a') {
            // TODO hide the user-specific settings
        }
    }
    
    /**
     * Saves the changes and returns the user to the previous screen.
     *
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        // TODO save changes
        
        getDialogStage().close();
    }
    
    /**
     * Returns the user to the previous screen without saving.
     *
     */
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        getDialogStage().close();
    }
}
