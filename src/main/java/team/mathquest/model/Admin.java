package team.mathquest.model;

public class Admin extends Account {
    // TODO create the rest of the objects
    
    /**
     * This constructor creates an account with the default password '1234'.
     * Uses the constructor from Account.
     *
     */
    public Admin(String name, String username) {
        super(name, username);
    }

    /**
     * This constructor creates an account with the password given.
     * Uses the constructor from Account.
     *
     */
    public Admin(String name, String username, String pass) {
        super(name, username, pass);
    }
    
    /**
     * @return the type of account, 'a' for admin, 'u' for user
     */
    @Override
    public char getType() {
        return 'a';
    }

    /**
     * @return accesses the statistics for the users that they are overseeing
     */
    @Override
    public String viewStats() {
        // TODO write the function
        return "Working on it";
    }
}
