package team.mathquest;

import java.util.Arrays;
import java.util.List;
import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.MathProblem.ProblemType;
import team.mathquest.model.Option.Difficulty;
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
 *  Controller for the Options screen.
 *
 */
public class OptionsController extends Controller {
    @FXML
    private ToggleGroup difficultyGroup;
    @FXML
    private Button saveButton;
    @FXML
    private List<RadioButton> buttonList;
    @FXML
    private List<CheckBox> checkboxList;
    
    private Alert alert;
    private ReaderWriter rw = new ReaderWriter();
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
        
        // If the isLocked flag is enabled: grays out the radio buttons
        // and the checkboxes, and disables the Save button
        if (((User) getAccount()).getOptions().isLocked()) {
            for (int i = 0; i < 3; i++)
                buttonList.get(i).setDisable(true);
            
            for (int i = 0; i < 4; i++)
                checkboxList.get(i).setDisable(true);
            
            saveButton.setDisable(true);
        }
        
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
        alert.setContentText("Your selections have been saved!");
        alert.showAndWait();
    }
    
    /**
     * Displays if at least one problem type wasn't selected.
     *
     */
    private void displayProblemTypeError() {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("At least one problem type needs to be selected.");
        alert.showAndWait();
    }
}
