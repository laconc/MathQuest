package team.mathquest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.Option;
import team.mathquest.model.User;

/**
 * FXML Controller class
 *
 * @author gluck
 */
public class AdminToolsController extends Controller {

    @Override
    public void start(Account account) {
        super.start(account);
    }
    
    /**
     * Returns the user to the main menu.
     *
     */
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
    }
    
    /**
     * Saves the user's settings and returns them to the main menu.
     *
     */
    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        
        // sends the user back to the main menu
        getMainApp().showMainMenu(getAccount());
    }
}
