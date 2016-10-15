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

public class ReaderWriter {
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Type userType = new TypeToken<ArrayList<User>>(){}.getType();
    
    private FileWriter writer;
    private BufferedReader reader;
    
    private ArrayList<Account> accounts;
    
    /**
    * Reads the users from Accounts.json.
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
    * Writes the users to Accounts.json.
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
}
