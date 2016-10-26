package team.mathquest;

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
     * The actions performed when the main menu button is pressed.
     * > Returns the user to the main menu
     *
     */
    @FXML
    private void handleMainMenuButtonAction(ActionEvent event) {
        super.getMainApp().showMainMenu(super.getAccount());
    }
    
    /**
     * The actions performed when the OK button is pressed.
     * > TODO: Save the settings
     *
     */
    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        // TODO: Save the settings
        super.getMainApp().showMainMenu(super.getAccount());
    }
}
