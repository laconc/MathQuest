package team.mathquest.model;

import java.time.LocalDate;

public class User extends Account {
    
    private LocalDate dateJoined;
    private Option options;

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
        // TODO: Write the function
        return "Working on it";
    }
}
