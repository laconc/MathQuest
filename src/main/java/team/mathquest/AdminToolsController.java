package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Admin;
import team.mathquest.model.Controller;
import team.mathquest.model.ReaderWriter;
import team.mathquest.model.User;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Controller for the Admin Tools screen.
 *
 */
public class AdminToolsController extends Controller {
    
    @FXML
    private ChoiceBox editUserChoiceBox;
    @FXML
    private CheckBox setAdminCheckbox;
    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passField;
    
    private Alert alert;
    private ReaderWriter rw = new ReaderWriter();
    private List<Account> accounts = new ArrayList<>();
    private String name;
    private String username;
    private String pass;
    
    @Override
    public void start (Account account) {
        super.start(account);
        
        // fills the choice box with the logged-in admin account and
        // all of the user accounts
        accounts.add(getAccount());
        accounts.addAll(rw.readUserList());
        editUserChoiceBox.getItems().addAll(accounts);
    }
    
    /**
     * Displays the Add User dialog box.
     *
     */
    @FXML
    private void handleAddUserButtonAction(ActionEvent event) {
        if (isValid()) {
            if (setAdminCheckbox.isSelected())
                rw.updateAdminList(new Admin(name, username, pass), 'n');
            else
                rw.updateUserList(new User(name, username, pass), 'n');

            displaySaveConfirmation();
        }
    }
    
    /**
     * Displays the Edit User dialog box.
     *
     */
    @FXML
    private void handleEditUserButtonAction(ActionEvent event) {
        if (editUserChoiceBox.getValue() != null)
            getMainApp().showEditUser(((Account) editUserChoiceBox.getValue()));
    }
    
    /**
     * Returns the user to the main menu.
     *
     */
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
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
            displayEmptyTextboxError();
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
    
    private void displayEmptyTextboxError() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("All of the textboxes need to be filled.");
        alert.showAndWait();
    }
}
