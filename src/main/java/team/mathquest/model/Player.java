package team.mathquest.model;

import javafx.scene.image.Image;

public class Player extends Entity {
    
    public Player() {
        super.setHealth(20);
        Image image = new Image(getClass().getClassLoader().
                getResourceAsStream("images/SheepImage.png"));
        super.setImage(image);
    }
    
    public void resetHealth() {
        super.setHealth(20);
    }
}
