package team.mathquest.model;

import javafx.stage.Stage;

public abstract class DialogBoxController extends Controller {
    
    private Stage dialogStage;
    
    /**
     * 
     * @return this object's stage 
     */
    public Stage getDialogStage() {
        return dialogStage;
    }
    
    /**
     * Is called by the main application to give this object a reference
     * to itself.
     * 
     * @param dialogStage this object's stage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
