/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.mathquest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import team.mathquest.model.Account;
import team.mathquest.model.Controller;

/**
 * FXML Controller class
 *
 * @author gluck
 */
public class LevelSelectController extends Controller {


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
}
