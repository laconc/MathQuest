package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.ReaderWriter;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller for the Login screen.
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
    private EventHandler handler;
    private ReaderWriter rw = new ReaderWriter();
    private List<Account> accounts = new ArrayList<>();
    
    @Override
    public void start(Account account) {
        super.start(account);
        addKeyboardListener();
    }

    /**
     * Logs in the user if the entered credentials are correct, returns an
     * error otherwise.
     *
     */
    private void authenticate() {
        // reads the accounts saved in the Users.json & Admins.json file
        accounts.addAll(rw.readUserList());
        accounts.addAll(rw.readAdminList());
        
        // check username and password
        for (Account account : accounts) {
            if (usernameField.getText().equals(account.getUsername())
                    && passField.getText().equals(account.getPassword())) {
                getMainApp().showMainMenu(account);
                removeKeyboardListener();
            }
            
        }
        
        // displays the error if the account info doesn't match
        errorLabel.setText("Incorrect!");
    }
    
    private void addKeyboardListener() {
        handler = (EventHandler<KeyEvent>) (KeyEvent key) -> {
            if (key.getCode() == KeyCode.ENTER)
                authenticate();
        };
        
        getMainApp().getMainStage().addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }
    
    private void removeKeyboardListener() {
        getMainApp().getMainStage().removeEventHandler(KeyEvent.KEY_PRESSED, handler);
    }
    
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        authenticate();
    }
    
    /**
     * Displays a dialog box when a user clicks 'Forgot Password?'
     *
     */
    @FXML
    private void handleForgotPassButtonAction(ActionEvent event) {
        displayForgotPasswordDialog();
    }
    
    private void displayForgotPasswordDialog() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Forgot Password?");
        alert.setHeaderText(null);
        alert.setContentText("Contact your local administrator so that "
                + "they may reset your password.");
        alert.showAndWait();
    }
}
