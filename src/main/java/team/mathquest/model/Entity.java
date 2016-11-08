package team.mathquest.model;

import javafx.scene.image.Image;

public abstract class Entity {
    
    private String name;
    private int health;
    private Image image;

    /**
     * @return the entity's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the entity's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the entity's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the entity's health
     */
    public void setHealth(int health) {
        this.health = health;
    }
    
    public void reduceHealth() {
        health -= 1;
        
        if (health == 0)
            killEntity();
    }
    
    abstract void killEntity();

    /**
     * @return the entity's image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the entity's image
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
