package team.mathquest.model;

public abstract class Account {
    private String name;
    private String username;
    private String pass;
    
    /**
     * This constructor creates an account with the default password '1234'.
     * 
     */
    Account(String name, String username) {
        this.name = name;
        this.username = username;
        this.pass = "1234";
    }
    
    /**
     * This constructor creates an account with the password given.
     * 
     */
    Account(String name, String username, String pass) {
        this.name = name;
        this.username = username;
        this.pass = pass;
    }
    
    public abstract String viewStats();

    /**
     * @return the account's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets the account's name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the account's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username sets the account's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the account's password
     */
    public String getPassword() {
        return pass;
    }

    /**
     * @param pass sets the account's password
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
