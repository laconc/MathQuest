package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.DialogBoxController;
import team.mathquest.model.MathProblem.ProblemType;
import team.mathquest.model.Option.Difficulty;
import team.mathquest.model.ReaderWriter;
import team.mathquest.model.User;

import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Controller for the Edit User dialog box.
 *
 */
public class EditUserController extends DialogBoxController {
    
    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passField;
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
    private List<ProblemType> typeList;
    private List<Difficulty> difficultyList;
    
    @Override
    public void start(Account account) {
        super.start(account);
        
        typeList = Arrays.asList(ProblemType.ADDITION,
                                 ProblemType.SUBTRACTION,
                                 ProblemType.MULTIPLICATION,
                                 ProblemType.DIVISION);

        difficultyList = Arrays.asList(Difficulty.EASY,
                                       Difficulty.NORMAL,
                                       Difficulty.HARD);
        
        nameField.setText(getAccount().getName());
        usernameField.setText(getAccount().getUsername());
        passField.setText(getAccount().getPassword());

        if (getAccount().getType() == 'a') {
            // hides the user-specific settings
            for (int i = 0; i < 3; i++)
                buttonList.get(i).setDisable(true);
            
            for (int i = 0; i < 4; i++)
                checkboxList.get(i).setDisable(true);
            
            lockDifficultyCheckbox.setDisable(true);
            resetStatsCheckbox.setDisable(true);
        }
        
        else if (getAccount().getType() == 'u') {
            // updates the UI with the currently-selected difficulty
            for (int i = 0; i < 3; i++)
                if (((User) getAccount()).getOptions().getDifficulty()
                        == difficultyList.get(i))
                    buttonList.get(i).setSelected(true);
            
            // updates the UI with the currently-selected problem types
            for (int i = 0; i < 4; i++)
                if (((User) getAccount()).getOptions().getFlag(typeList.get(i)))
                    checkboxList.get(i).setSelected(true);
        }
    }
    
    /**
     * Saves the changes and returns the user to the previous screen.
     *
     */
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        // the textfields should all contain some text
        if (nameField.getText().length() > 0 &&
            usernameField.getText().length() > 0 &&
            passField.getText().length() > 0) {
            
            getAccount().setName(nameField.getText());
            getAccount().setUsername(usernameField.getText());
            getAccount().setPass(passField.getText());

            if (getAccount().getType() == 'u') {
                if (lockDifficultyCheckbox.isSelected()) {
                    ((User) getAccount()).getOptions().setLocked(true);
                }
                if (resetStatsCheckbox.isSelected()) {
                    ((User) getAccount()).resetGameHistory();
                }

                // the difficulty setting
                for (int i = 0; i < 3; i++) {
                    if (difficultyGroup.getSelectedToggle() == buttonList.get(i)) {
                        ((User) getAccount()).getOptions()
                                .setDifficulty(difficultyList.get(i));
                    }
                }

                //the problem-types setting
                for (int i = 0; i < 4; i++) {
                    if (checkboxList.get(i).isSelected()) {
                        ((User) getAccount()).getOptions()
                                .setFlag(typeList.get(i), true);
                        isOk = true;
                    } else {
                        ((User) getAccount()).getOptions()
                                .setFlag(typeList.get(i), false);
                    }
                }
                
                // checks that at least one problem type was selected
                if (isOk) {
                    // write to file
                    rw.updateUserList(getAccount(), 'u');
                    displaySaveConfirmation();
                    getDialogStage().close();
                }
                else
                    displayProblemTypeError();
            }
            
            else if (getAccount().getType() == 'a') {
                // write to file
                rw.updateAdminList(getAccount(), 'u');
                displaySaveConfirmation();
                getDialogStage().close();
            }
        }
        else
            displayEmptyTextboxError();
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
    
    private void displayEmptyTextboxError() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("All of the textboxes need to be filled.");
        alert.showAndWait();
    }
}
