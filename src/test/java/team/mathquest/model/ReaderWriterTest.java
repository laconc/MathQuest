package team.mathquest.model;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author dash
 */
public class ReaderWriterTest {

    ArrayList<Account> accounts = new ArrayList<>();

    public ReaderWriterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of readUserList method, of class ReaderWriter.
     */
    @org.junit.Test
    public void testReadUserList() {
        System.out.println("readUserList");
        ReaderWriter rw = new ReaderWriter();
        accounts = rw.readUserList();
        String expResult = "dash";
        String result = accounts.get(0).getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of writeUserList method, of class ReaderWriter.
     */
    @org.junit.Test
    public void testWriteUserList() {
        System.out.println("writeUserList");
        ReaderWriter rw = new ReaderWriter();
        accounts.add(new User("John Smith", "john"));
        // rw.writeUserList(accounts);
    }

    /**
     * Test of readAdminList method, of class ReaderWriter.
     */

    @org.junit.Test
    public void testReadAdminList() {
        System.out.println("readAdminList");
        ReaderWriter rw = new ReaderWriter();
        accounts = rw.readAdminList();
        String expResult = "buzz";
        String result = accounts.get(0).getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of writeAdminList method, of class ReaderWriter.
     */

    @org.junit.Test
    public void testWriteAdminList() {
        System.out.println("writeAdminList");
        ReaderWriter rw = new ReaderWriter();
        accounts.add(new User("Buffy Smith", "buffy"));
        // rw.writeAdminList(accounts);
    }
}
