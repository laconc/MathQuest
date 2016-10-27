package team.mathquest;

import team.mathquest.model.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

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
    private ToggleGroup difficultyGroup;
    @FXML
    private RadioButton easyButton;
    @FXML
    private RadioButton normalButton;
    @FXML
    private RadioButton hardButton;
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
        getMainApp().showMainMenu(getAccount());
    }
    
    /**
     * Saves the user's settings and return them to the main menu.
     *
     */
    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        // TODO: Save the settings
        getMainApp().showMainMenu(getAccount());
    }
}
