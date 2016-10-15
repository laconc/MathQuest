package team.mathquest;

import team.mathquest.model.Account;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for the main menu screen.
 *
 */
public class MenuController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Button playGameButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button statsButton;
    @FXML
    private Button adminToolsButton;
    
    private MainApp mainApp;
    private Account account;
    
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

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
        nameLabel.setText(account.getName());
    }
}
