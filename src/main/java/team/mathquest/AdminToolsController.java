package team.mathquest;


import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.ReaderWriter;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller for the Admin Tools screen.
 *
 */
public class AdminToolsController extends Controller {
    
    private ReaderWriter rw = new ReaderWriter();
    private List<Account> accounts = new ArrayList<>();
    
    @Override
    public void start (Account account) {
        super.start(account);
        
        // fills the choice box with the logged-in admin account and
        // all of the user accounts
        // TODO fiter for users that they manage
        accounts.add(getAccount());
        accounts.addAll(rw.readUserList());
        // TODO fill choice box
    }
    
    /**
     * Displays the Add User dialog box.
     *
     */
    @FXML
    private void handleAddUserButtonAction(ActionEvent event) {
        getMainApp().showAddUser(getAccount());
    }
    
    /**
     * Displays the Edit User dialog box.
     *
     */
    @FXML
    private void handleEditUserButtonAction(ActionEvent event) {
        getMainApp().showEditUser(getAccount()); // TODO pass selected account
    }
    
    /**
     * Returns the user to the main menu.
     *
     */
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
    }
}
