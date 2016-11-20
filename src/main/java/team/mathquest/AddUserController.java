package team.mathquest;

import team.mathquest.model.Admin;
import team.mathquest.model.DialogBoxController;
import team.mathquest.model.ReaderWriter;
import team.mathquest.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

/**
 * Controller for the Add User dialog box.
 *
 */
public class AddUserController extends DialogBoxController {
    
    @FXML
    private CheckBox isAdmin;
    
    private ReaderWriter rw = new ReaderWriter();
    private String username;
    private String name;
    private String pass;
    
    /**
     * Adds the new account and returns the user to the previous screen.
     *
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        if(name != null || username != null || pass != null){
        if (isAdmin.isSelected())
            rw.updateAdminList(new Admin(name, username, pass));
        else
            rw.updateUserList(new User(name, username, pass));
        
        getDialogStage().close();
        }
        else{
        //requiredFieldsError.setVisible(true); 
        }
    }
}
