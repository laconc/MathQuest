package team.mathquest.model;

import team.mathquest.MainApp;

public abstract class Controller {
    
    private Account account;
    private MainApp mainApp;
    
    /**
     * Initializes the controller class.
     * @param account the account to set
     */
    public void start(Account account) {
        setAccount(account);
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
