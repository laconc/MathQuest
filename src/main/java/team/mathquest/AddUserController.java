package team.mathquest;

import team.mathquest.model.Admin;
import team.mathquest.model.DialogBoxController;
import team.mathquest.model.ReaderWriter;
import team.mathquest.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for the Add User dialog box.
 *
 */
public class AddUserController extends DialogBoxController {
    
    @FXML
    private CheckBox isAdminCheckbox;
    @FXML
    private Label errorLabel;
    
    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passField;
    
    private ReaderWriter rw = new ReaderWriter();
    private Alert alert;
    private String username;
    private String name;
    private String pass;
    
    /**
     * Adds the new account and returns the user to the previous screen.
     *
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        if (isValid()) {
            if (isAdminCheckbox.isSelected())
                rw.updateAdminList(new Admin(name, username, pass), 'n');
            else
                rw.updateUserList(new User(name, username, pass), 'n');

            displaySaveConfirmation();
            getDialogStage().close();
        }
    }
    
    /**
     * Returns the user to the previous screen without saving.
     *
     */
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        getDialogStage().close();
    }
    
    private boolean isValid() {
        // the textfields should all contain some text
        if (nameField.getText().length() > 0 &&
            usernameField.getText().length() > 0 &&
            passField.getText().length() > 0) {
            
                name = nameField.getText();
                username = usernameField.getText();
                pass = passField.getText();
                return true;
        }
        
        else {
            errorLabel.setVisible(true);
            return false;
        }
    }
    
    private void displaySaveConfirmation() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("The user was created successfully!");
        alert.showAndWait();
    }
}
