package team.mathquest;

import team.mathquest.model.Account;
import team.mathquest.model.Controller;
import team.mathquest.model.DialogBoxController;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class MainApp extends Application {

    private Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setTitle("MathQuest");
        
        showLogin();
    }

    /**
    * Displays the Login screen.
    *
    */
    public void showLogin() {
        String resource = "/fxml/Login.fxml";
        swapScene(resource, null);
    }

    /**
    * Displays the Main Menu screen.
    *
    * @param account the account that was used to log in
    */
    public void showMainMenu(Account account) {
        String resource = "/fxml/Menu.fxml";
        swapScene(resource, account);
    }
    
    /**
    * Displays the Level Select screen.
    *
    * @param account the account that was used to log in
    */
    public void showLevelSelect(Account account) {
        String resource = "/fxml/LevelSelect.fxml";
        swapScene(resource, account);
    }
    
    /**
    * Displays the Game screen.
    *
    * @param account the account that was used to log in
    */
    public void showGame(Account account) {
        String resource = "/fxml/Game.fxml";
        swapScene(resource, account);
    }
    
    /**
    * Displays the Options screen.
    *
    * @param account the account that was used to log in
    */
    public void showOptions(Account account) {
        String resource = "/fxml/Options.fxml";
        swapScene(resource, account);
    }
    
    /**
    * Displays the Admin Tools screen.
    *
    * @param account the account that was used to log in
    */
    public void showAdminTools(Account account) {
        String resource = "/fxml/AdminTools.fxml";
        swapScene(resource, account);
    }
    
    /**
    * Displays the Quit Game dialog box.
    *
    * @param account the account that was used to log in
    */
    public void showQuitGame(Account account) {
        String resource = "/fxml/QuitGame.fxml";
        String title = "Quit Game";
        displayDialog(resource, account, title);
    }
    
    /**
    * Displays the Statistics screen.
    *
    * @param account the account that was used to log in
    */
    public void showStats(Account account) {
        String resource = "/fxml/Stats.fxml";
        swapScene(resource, account);
    }
    
    /**
    * Changes the active scene and controller.
    * 
    * @param resource the FXML file to load
    * @param account the account that was used to log in
    */
    private void swapScene(String resource, Account account) {
        try {
            // prepares the new scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(resource));
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            // scene.getStylesheets().add("/styles/Styles.css");

            // swaps to the new scene
            mainStage.close(); // closing it first to force it to re-center
            mainStage.setScene(scene);
            mainStage.show();

            // gives the controller access to the main app & passes the
            // account info
            Controller controller = loader.getController();
            controller.setMainApp(this);
            controller.start(account);

        } catch (IOException e) {
            System.out.println("Error swapping the scene.");
        }
    }
    
    /**
    * Opens a second screen and changes the active controller.
    * 
    * @param resource the FXML file to load
    * @param account the account that was used to log in
    * @param title the title of the dialog box
    */
    private void displayDialog(String resource, Account account, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(resource));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initOwner(mainStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // gives the controller access to the main app & passes it a
            // reference to itself
            DialogBoxController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.start(account);

            dialogStage.showAndWait();
            
        } catch (IOException e) {
            System.out.println("Error displaying the dialog box.");
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
