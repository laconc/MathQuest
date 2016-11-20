package team.mathquest;

import java.util.List;
import team.mathquest.model.Account;
import team.mathquest.model.DialogBoxController;
import team.mathquest.model.ReaderWriter;
import team.mathquest.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import team.mathquest.model.MathProblem;
import team.mathquest.model.Option;

/**
 * Controller for the Edit User dialog box.
 *
 */
public class EditUserController extends DialogBoxController {
    
    @FXML
    private CheckBox lockDifficultyCheckbox;
    @FXML
    private CheckBox resetStatsCheckbox;
    @FXML
    private ToggleGroup difficultyGroup;
    @FXML
    private List<RadioButton> buttonList;
    @FXML
    private List<CheckBox> checkboxList;
    
    private ReaderWriter rw = new ReaderWriter();
    private Alert alert;
    private boolean isOk = false;
    private List<MathProblem.ProblemType> typeList;
    private List<Option.Difficulty> difficultyList;
    
    @Override
    public void start (Account account) {
        super.start(account);
        
        if (getAccount().getType() == 'a') {
            // TODO hide the user-specific settings
        }
    }
    
    /**
     * Saves the changes and returns the user to the previous screen.
     *
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        if (lockDifficultyCheckbox.isSelected())
            ((User) getAccount()).getOptions().setLocked(true);
        if (resetStatsCheckbox.isSelected())
            ((User) getAccount()).resetGameHistory();
        
        // the difficulty setting
        for (int i = 0; i < 3; i++)
            if (difficultyGroup.getSelectedToggle() == buttonList.get(i))
                ((User) getAccount()).getOptions()
                    .setDifficulty(difficultyList.get(i));
        
        //the problem-types setting
        for (int i = 0; i < 4; i++) {
            if (checkboxList.get(i).isSelected()) {
                ((User) getAccount()).getOptions()
                    .setFlag(typeList.get(i), true);
                isOk = true;
            }
            else
                ((User) getAccount()).getOptions()
                    .setFlag(typeList.get(i), false);
        }
        
        if (isOk) { // checks that at least one problem type was selected
            // write to file
            rw.updateUserList((User) getAccount(), 'u');
            displaySaveConfirmation();

            // sends the user back to the previous screen
            getDialogStage().close();
        }
        else
            displayProblemTypeError();
    }
    
    /**
     * Returns the user to the previous screen without saving.
     *
     */
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        getDialogStage().close();
    }
    
    private void displaySaveConfirmation() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Your selections have been saved!");
        alert.showAndWait();
    }
    
    /**
     * Displays if at least one problem type wasn't selected.
     *
     */
    private void displayProblemTypeError() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("At least one problem type needs to be selected.");
        alert.showAndWait();
    }
}
