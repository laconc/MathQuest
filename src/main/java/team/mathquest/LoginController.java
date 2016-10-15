package team.mathquest;

import team.mathquest.model.Account;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import team.mathquest.model.ReaderWriter;

/**
 * Controller for the login screen.
 *
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passField;
    
    private MainApp mainApp;
    private ReaderWriter rw = new ReaderWriter();
    private ArrayList<Account> accounts;
    
    /**
     * The actions performed when the login button is pressed.
     * 
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        // reads the accounts saved in the Users JSON file
        accounts = rw.readUserList();
        // check username and password
        for (Account account : accounts) {
            if (usernameField.getText().equals(account.getUsername())
                    && passField.getText().equals(account.getPassword()))
                mainApp.showMainMenu(account);
        }
        // displays the error if the account info doesn't match
        errorLabel.setText("Incorrect!");
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp the main application
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    } 
}
