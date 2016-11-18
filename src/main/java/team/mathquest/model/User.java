package team.mathquest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends Account {
    
    private LocalDate dateJoined;
    private Option options;
    private List<Session> gameHistory = new ArrayList<>();
    private int level;

    /**
     * This constructor creates an account with the default password '1234'.
     * Uses the constructor from Account.
     *
     */
    public User(String name, String username) {
        super(name, username);
        this.dateJoined = LocalDate.now();
        options = new Option();
    }

    /**
     * This constructor creates an account with the password given.
     * Uses the constructor from Account.
     *
     */
    public User(String name, String username, String pass) {
        super(name, username, pass);
        this.dateJoined = LocalDate.now();
        options = new Option();
    }

    /**
     * @return the date the user's account was created
     */
    public LocalDate getDateJoined() {
        return dateJoined;
    }

    /**
     * @param dateJoined the date the user's account was created
     */
    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    /**
     * @return the user's options
     */
    public Option getOptions() {
        return options;
    }

    /**
     * @param options the user's options to set
     */
    public void setOptions(Option options) {
        this.options = options;
    }
    
    /**
     * @return the type of account, 'a' for admin, 'u' for user
     */
    @Override
    public char getType() {
        return 'u';
    }

    /**
     * @return statistics for this user
     */
    @Override
    public String viewStats() {
        // TODO write the function
        return "Working on it";
    }

    /**
     * @return this user's game history
     */
    public List<Session> getGameHistory() {
        return gameHistory;
    }

    /**
     * @param session the session to add to this user's history
     */
    public void addHistorySession(Session session) {
        gameHistory.add(session);
    }

    /**
     * @return the player's current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
