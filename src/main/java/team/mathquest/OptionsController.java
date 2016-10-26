package team.mathquest;

import java.net.URL;
import java.util.ResourceBundle;
import team.mathquest.model.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 *  Controller for the options screen.
 *
 */
public class OptionsController extends Controller {
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button okButton;
    @FXML
    private CheckBox addCheckbox;
    @FXML
    private CheckBox subCheckbox;
    @FXML
    private CheckBox mulCheckbox;
    @FXML
    private CheckBox divCheckbox;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // If account settings isLocked, gray out the radio buttons and
        // the checkboxes
    }
    
    /**
     * Returns the user to the main menu.
     *
     */
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        super.getMainApp().showMainMenu(super.getAccount());
    }
    
    /**
     * Saves the user's settings and return them to the main menu.
     *
     */
    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        // TODO: Save the settings
        super.getMainApp().showMainMenu(super.getAccount());
    }
}
