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
    * @param users the users to save
    */
    public void writeUserList(ArrayList<Account> users) {
        String json = gson.toJson(users, userType);
        try {
            writer = new FileWriter("Users.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write Users.json.");
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
    * @param admins the admins to save
    */
    public void writeAdminList(ArrayList<Account> admins) {
        String json = gson.toJson(admins, adminType);
        try {
            writer = new FileWriter("Admins.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write Admins.json.");
        }
    }
}
