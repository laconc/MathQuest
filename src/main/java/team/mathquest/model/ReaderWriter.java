package team.mathquest.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Reads and writes the state to JSON files.
 *
 */
public class ReaderWriter {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Type userType = new TypeToken<ArrayList<User>>(){}.getType();
    private Type adminType = new TypeToken<ArrayList<Admin>>(){}.getType();

    private FileWriter writer;
    private BufferedReader reader;

    private ArrayList<Account> accounts;
    private String json;

    /**
    * Reads the users from Users.json.
    *
    * @return the users in the system
    */
    public ArrayList<Account> readUserList() {
        try {
            reader = new BufferedReader(new FileReader("Users.json"));
            accounts = gson.fromJson(reader, userType);
        } catch (IOException e) {
            System.out.println("Failed to read Users.json.");
        }
        return accounts;
    }

    /**
    * Writes the users to Users.json.
    *
    * @param accounts the users to save
    */
    public void writeUserList(ArrayList<Account> accounts) {
        json = gson.toJson(accounts, userType);
        try {
            writer = new FileWriter("Users.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write Users.json.");
        }
    }
    
    /**
    * Updates a user account.
    *
    * @param account the account to update
    */
    public void updateUserList(User account) {
        try {
            reader = new BufferedReader(new FileReader("Users.json"));
            accounts = gson.fromJson(reader, userType);
            
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getUsername().equals(account.getUsername()))
                    accounts.set(i, account);
            }
            
            json = gson.toJson(accounts, userType);
            writer = new FileWriter("Users.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to update Users.json.");
        }
    }

    /**
    * Reads the admins from Admins.json.
    *
    * @return the admins in the system
    */
    public ArrayList<Account> readAdminList() {
        try {
            reader = new BufferedReader(new FileReader("Admins.json"));
            accounts = gson.fromJson(reader, adminType);
        } catch (IOException e) {
            System.out.println("Failed to read Admins.json.");
        }
        return accounts;
    }

    /**
    * Writes the admins to Admins.json.
    *
    * @param accounts the admins to save
    */
    public void writeAdminList(ArrayList<Account> accounts) {
        json = gson.toJson(accounts, adminType);
        try {
            writer = new FileWriter("Admins.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write Admins.json.");
        }
    }
}
