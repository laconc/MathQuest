package team.mathquest.model;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import team.mathquest.MainApp;

public abstract class Controller implements Initializable {
    
    private Account account;
    private MainApp mainApp;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
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
    }
    
    /**
     * @return the reference to the main application
     */
    public MainApp getMainApp() {
        return mainApp;
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
