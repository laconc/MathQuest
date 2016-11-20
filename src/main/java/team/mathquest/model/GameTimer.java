package team.mathquest.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class GameTimer {
    
    private IntegerProperty timeProperty = new SimpleIntegerProperty();
    private EventHandler event;
    private Timeline timeline;
    private double time;
    private double bonusTime;
    
    public GameTimer (Account account, EventHandler event) {
        // as a player progresses, they are allotted more time per level
        bonusTime = ((User) account).getLevel() * .1;
        time = 150 + (150 * bonusTime);
        this.event = event;
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

    /**
     * @return the remaining time
     */
    public IntegerProperty getTimeProperty() {
        return timeProperty;
    }
}
