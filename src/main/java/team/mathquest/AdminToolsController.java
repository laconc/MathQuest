package team.mathquest;


import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.ReaderWriter;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 * Controller for the Admin Tools screen.
 *
 */
public class AdminToolsController extends Controller {
    
    @FXML
    private ChoiceBox editUserChoiceBox;
    
    private ReaderWriter rw = new ReaderWriter();
    private List<Account> accounts = new ArrayList<>();
    
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
        getMainApp().showAddUser(getAccount());
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
}
