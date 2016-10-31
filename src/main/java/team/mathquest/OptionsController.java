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
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 *  Controller for the Options screen.
 *
 */
public class OptionsController extends Controller {
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
    private boolean isOk = false;
    
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
    private void handleCancelButtonAction(ActionEvent event) {
        getMainApp().showMainMenu(getAccount());
    }
    
    /**
     * Saves the user's settings and returns them to the main menu.
     *
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        
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
        if (addCheckbox.isSelected()) {
            ((User) getAccount()).getOptions().setAdditionFlag(true);
            isOk = true;
        }
        else
            ((User) getAccount()).getOptions().setAdditionFlag(false);
        
        if (subCheckbox.isSelected()) {
            ((User) getAccount()).getOptions().setSubtractionFlag(true);
            isOk = true;
        }
        else
            ((User) getAccount()).getOptions().setSubtractionFlag(false);
            
        if (mulCheckbox.isSelected()) {
            ((User) getAccount()).getOptions().setMultiplicationFlag(true);
            isOk = true;
        }
        else
            ((User) getAccount()).getOptions().setMultiplicationFlag(false);
            
        if (divCheckbox.isSelected()) {
            ((User) getAccount()).getOptions().setDivisionFlag(true);
            isOk = true;
        }
        else
            ((User) getAccount()).getOptions().setDivisionFlag(false);
        
        if (isOk) { // checks that at least one problem type was selected
            // write to file
            rw.updateUserList((User) getAccount());
            displaySaveConfirmation();

            // sends the user back to the main menu
            getMainApp().showMainMenu(getAccount());
        }
        else
            displayProblemTypeError();
    }
    
    private void displaySaveConfirmation() {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Save Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Your selections were saved!");
        alert.showAndWait();
    }
    
    private void displayProblemTypeError() {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("At least one problem type needs to be selected.");
        alert.showAndWait();
    }
}
