package team.mathquest.model;

import javafx.scene.image.Image;

public class Player extends Entity {
    
    public Player() {
        super.setName("Seamus");
        super.setHealth(20);
        Image image = new Image(getClass().getClassLoader().
                getResourceAsStream("images/SheepImage.png"));
        super.setImage(image);
    }
    
    @Override
    void killEntity() {
        // TODO write method
    }
}
