package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.Option;
import team.mathquest.model.User;
import team.mathquest.model.ReaderWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    
    private Alert alert;
    private ReaderWriter rw = new ReaderWriter();
    
    @Override
    public void start(Account account) {
        super.start(account);
        
        // If the isLocked flag is enabled, grays out the radio buttons
        // and the checkboxes
        if (((User) getAccount()).getOptions().isLocked()) {
            easyButton.setDisable(true);
            normalButton.setDisable(true);
            hardButton.setDisable(true);
            
            addCheckbox.setDisable(true);
            subCheckbox.setDisable(true);
            mulCheckbox.setDisable(true);
            divCheckbox.setDisable(true);
        }
        
        // selects the currently-selected difficulty
        switch (((User) getAccount()).getOptions().getDifficulty()) {
            case EASY:
                easyButton.setSelected(true);
                break;
            case NORMAL:
                normalButton.setSelected(true);
                break;
            case HARD:
                hardButton.setSelected(true);
        }
        
        // selects the currently-selected problem types
        if (((User) getAccount()).getOptions().getAdditionFlag())
            addCheckbox.setSelected(true);
        if (((User) getAccount()).getOptions().getSubtractionFlag())
            subCheckbox.setSelected(true);
        if (((User) getAccount()).getOptions().getMultiplicationFlag())
            mulCheckbox.setSelected(true);
        if (((User) getAccount()).getOptions().getDivisionFlag())
            divCheckbox.setSelected(true);
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
        
        // the difficulty setting
        if (difficultyGroup.getSelectedToggle() == easyButton)
            ((User) getAccount()).getOptions()
                    .setDifficulty(Option.Difficulty.EASY);
        
        else if (difficultyGroup.getSelectedToggle() == normalButton)
            ((User) getAccount()).getOptions()
                    .setDifficulty(Option.Difficulty.NORMAL);
        
        else if (difficultyGroup.getSelectedToggle() == hardButton)
            ((User) getAccount()).getOptions()
                    .setDifficulty(Option.Difficulty.HARD);
        
        //the problem-types setting
        if (addCheckbox.isSelected())
            ((User) getAccount()).getOptions().setAdditionFlag(true);
        else
            ((User) getAccount()).getOptions().setAdditionFlag(false);
        
        if (subCheckbox.isSelected())
            ((User) getAccount()).getOptions().setSubtractionFlag(true);
        else
            ((User) getAccount()).getOptions().setSubtractionFlag(false);
            
        if (mulCheckbox.isSelected())
            ((User) getAccount()).getOptions().setMultiplicationFlag(true);
        else
            ((User) getAccount()).getOptions().setMultiplicationFlag(false);
            
        if (divCheckbox.isSelected())
            ((User) getAccount()).getOptions().setDivisionFlag(true);
        else
            ((User) getAccount()).getOptions().setDivisionFlag(false);
        
        // write to file
        rw.updateUserList((User) getAccount());
        displaySaveConfirmation();
        
        // sends the user back to the main menu
        getMainApp().showMainMenu(getAccount());
    }
    
    private void displaySaveConfirmation() {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Save Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Your selections were saved!");
        alert.showAndWait();
    }
}
