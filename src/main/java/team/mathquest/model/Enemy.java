package team.mathquest.model;

import java.util.Random;
import javafx.scene.image.Image;

public class Enemy extends Entity {
    
    private enum Creature {
        WOLF ("Wolf", "images/WolfImage.png");
        
        private String name;
        private String imageSource;
        
        Creature(String name, String imageSource) {
            this.name = name;
            this.imageSource = imageSource;
        }
    }
    
    private enum Strength {
        WEAK ("Weak", 5),
        STRONG ("Strong", 10);
        
        private String description;
        private int health;
        
        Strength(String description, int hp) {
            this.description = description;
            this.health = hp;
        }
    }
    
    private Random random = new Random();
    private Strength strength;
    private Creature creature;
    
    public Enemy() {
        // randomly selects a level of strength and a type of creature
        creature = Creature.values()[random.nextInt(Creature.values().length)];
        strength = Strength.values()[random.nextInt(Strength.values().length)];
        
        super.setName(strength.description + " " + creature.name);
        super.setHealth(strength.health);
        super.setImage(new Image(creature.imageSource));
    }
}
