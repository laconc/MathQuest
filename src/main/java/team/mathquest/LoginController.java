package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.ReaderWriter;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller for the login screen.
 *
 */
public class LoginController extends Controller {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passField;
    
    private Alert alert;
    private ReaderWriter rw = new ReaderWriter();
    private ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Logs in the user if the entered credentials are correct, returns an
     * error otherwise.
     *
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        // reads the accounts saved in the Users.json & Admins.json file
        accounts.addAll(rw.readUserList());
        accounts.addAll(rw.readAdminList());
        // check username and password
        for (Account account : accounts) {
            if (usernameField.getText().equals(account.getUsername())
                    && passField.getText().equals(account.getPassword()))
                getMainApp().showMainMenu(account);
        }
        // displays the error if the account info doesn't match
        errorLabel.setText("Incorrect!");
    }
    
    /**
     * Display a dialog box when a user clicks 'Forgot Password?'
     *
     */
    @FXML
    private void handleForgotPassButtonAction(ActionEvent event) {
        displayForgotPaswordDialog();
    }
    
    private void displayForgotPaswordDialog() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Forgot Password?");
        alert.setHeaderText(null);
        alert.setContentText("Contact your local admininstrator so that they may reset your password.");
        alert.showAndWait();
    }
}
