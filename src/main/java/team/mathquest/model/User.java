package team.mathquest.model;

public class User extends Account {
    
    // needs a dateJoined object
    
    /**
     * This constructor creates an account with the default password '1234'.
     * Uses the constructor from Account.
     * 
     */
    User(String name, String username) {
        super(name, username);
    }
    
    /**
     * This constructor creates an account with the password given.
     * Uses the constructor from Account.
     * 
     */
    User(String name, String username, String pass) {
        super(name, username, pass);
    }
    
    /**
     * @return statistics for this user
     */
    @Override
    public String viewStats() {
        return "Working on it";
    }
    
}
