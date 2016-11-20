package team.mathquest.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class GameTimer {
    
    private EventHandler event;
    private IntegerProperty timeProperty;
    private Timeline timeline;
    private double time;
    private double bonusTime;
    
    public GameTimer (Account account) {
        timeProperty = new SimpleIntegerProperty();
        // as a player progresses, they are allotted more time per level
        bonusTime = ((User) account).getLevel() * .1;
        
        switch(((User) account).getOptions().getDifficulty()) {
            case EASY:
                time = 200 + (200 * bonusTime);
                break;
            case NORMAL:
                time = 150 + (150 * bonusTime);
                break;
            case HARD:
                time = 100 + (100 * bonusTime);
        }
    }
    
    public void startTimer() {
        getTimeProperty().set((int) time);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(((int) time) + 1),
                            // time ran out
                            event,
                            new KeyValue(getTimeProperty(), 0)));
        timeline.playFromStart();
    }

    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }
    
    public void setEvent(EventHandler event) {
        this.event = event;
    }

    /**
     * @return the remaining time
     */
    public IntegerProperty getTimeProperty() {
        return timeProperty;
    }
}
