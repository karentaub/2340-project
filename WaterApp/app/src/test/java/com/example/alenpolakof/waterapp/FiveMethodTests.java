package com.example.alenpolakof.waterapp;
import org.junit.Before;
import java.util.ArrayList;

//import com.example.alenpolakof.waterapp.TempDB;
import org.junit.Test;
// import static org.junit.Assert.assertArrayEquals;
//
import static org.junit.Assert.*;


public class FiveMethodTests {
    TempDB testDB = new TempDB();
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
       testDB.addUser("John", "John", "User", "John");
       testDB.addUser("Jake", "Jake", "Worker", "Jake");
       testDB.addUser("Jean", "Jean", "Manager", "Jean");
       testDB.addUser("Josh", "Josh", "Admin", "Josh");

    }

    @Test(timeout = TIMEOUT)
    public void testOnlyUsers() {
        ArrayList<String> testUsers = testDB.onlyUsers();
        ArrayList<String> actualUsers = new ArrayList<String>();
        actualUsers.add("John");
        assertEquals(testUsers, actualUsers);
    }

    @Test(timeout = TIMEOUT)
    public void testOnlyWorkers() {
        ArrayList<String> testWorkers = testDB.onlyWorkers();
        ArrayList<String> actualWorkers = new ArrayList<String>();
        actualWorkers.add("Jake");
        assertEquals(testWorkers, actualWorkers);
    }

    @Test(timeout = TIMEOUT)
    public void testOnlyManagers() {
        ArrayList<String> testManagers = testDB.onlyManagers();
        ArrayList<String> actualManagers = new ArrayList<String>();
        actualManagers.add("Jean");
        assertEquals(testManagers, actualManagers);
    }

    @Test(timeout = TIMEOUT)
    public void testValidateUser() {
        assertTrue(testDB.validateUser("John", "John"));
        assertTrue(!testDB.validateUser("John", "john"));
        assertTrue(!testDB.validateUser("Jane", "jane"));
    }

 
}
