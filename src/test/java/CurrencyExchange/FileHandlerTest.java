package CurrencyExchange;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Scanner;

public class FileHandlerTest {
    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "currencies.txt";
    ArrayList<String> result;

    // Makes a copy of currencies.txt, stores it in memory as a list
    private ArrayList<String> fileCopy() {
        ArrayList<String> fc = new ArrayList<>();
        Scanner reader = null;

        try {
            reader = new Scanner(new File(file));
        } catch(FileNotFoundException e) {
            System.out.println("Can't open currencies.txt");
            System.exit(1);
        }

        while(reader.hasNextLine()) {
            fc.add(reader.nextLine());
        }
        reader.close();
        return fc;
    }

    // Helper method to see if the file currencies.txt is the same as the list returned by fileCopy(). Returns true
    // if the files are the same
    private boolean fileEquality(ArrayList<String> fc) {
        Scanner reader = null;

        try {
            reader = new Scanner(new File(file));
        } catch(FileNotFoundException e) {
            System.out.println("Can't open currencies.txt");
            System.exit(1);
        }

        boolean equality = true;
        int counter = 0;
        while(reader.hasNext() && equality) {
            if(counter < fc.size()) {
                if(!reader.nextLine().equals(fc.get(counter))) {
                    equality = false;
                }
            counter++;
            }
        }
        reader.close();
        return equality;
    }

    // Helper method to restore the file to its original form.
    private void restoreFile(ArrayList<String> fc) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new File(file));
        } catch(FileNotFoundException e) {
            System.out.println("Can't open currencies.txt");
            e.printStackTrace();
            System.exit(1);
        }

        if(!fc.isEmpty()) {
            writer.print(fc.get(0));
        }
        for(int i=1; i<fc.size(); i++) {
            writer.print(System.lineSeparator() + fc.get(i));
        }

        writer.close();
    }

    // This test checks whether the helper methods work as intended.
    @Test
    public void helperMethods() {
        ArrayList<String> oldFile = fileCopy();
        assertTrue(fileEquality(oldFile));
        FileHandler.remove("AUD");
        assertFalse(fileEquality(oldFile));
        restoreFile(oldFile);
        assertTrue(fileEquality(oldFile));
    }

    /*
    testRemove adds a fake string to the file 'currencies.txt' and then removes it. This is a regular case.
     */
    @Test
    void testRemove() {
        // String toDelete = "AAA";
        FileHandler.add("AAA", 1.4352);
        FileHandler.remove("AAA");
        assertTrue(FileHandler.get("AAA").isEmpty());
    }

    /*
    This test will see what happens if we try to remove a currency from currency.txt that doesn't exist there.
     */
    @Test
    void testRemoveDoesntExist() {
        result = FileHandler.get("DoesntExist");
        if(result.isEmpty()) {
            FileHandler.remove("DoesntExist");
            result = FileHandler.get("DoesntExist");
            assertTrue(result.isEmpty());
        }
        else {
            fail("Something went wrong. The string DoesntExist should not be in currency.txt. Please check file");
        }
    }

    /*
    This test will see what happens if we try to remove an empty string. There should be no changes in the file.
     */
    @Test
    void testRemoveEmptyString() {
        ArrayList<String> oldFile = fileCopy();
        FileHandler.remove("");
        assertTrue(fileEquality(oldFile));
    }

    /*
    This test will see what happens if we enter a null value in FileHandler.remove() There should be no change to the file.
     */
    @Test
    void testRemoveNullString() {
        String zero = null;
        ArrayList<String> oldFile = fileCopy();
        FileHandler.remove(zero);
        assertTrue(fileEquality(oldFile));
    }

    /*
    Try using remove() using a number as a parameter. The method should only remove entries whose names match the string
    as the given parameter. There should be no changes to the file currency.txt
     */
    @Test
    void testRemoveNumber() {
        String number = "1";
        ArrayList<String> oldFile = fileCopy();
        FileHandler.remove(number);
        assertTrue(fileEquality(oldFile));
    }

    /*
    Basic case. Try to remove a currency that has multiple entries
     */
    @Test
    void removeMultiple() {
        ArrayList<String> oldFile = fileCopy();
        for(int i=1; i<3; i++) {
            FileHandler.add("test", i);
        }
        FileHandler.remove("test");
        assertTrue(fileEquality(oldFile));
    }

    /*
    Basic use case for get(). All currencies are measured against the AUD, thus the currency.txt file MUST contain the AUD
    with a value of 1.
     */
    @Test
    void getSuccess() {
        result = FileHandler.get("AUD");
        boolean in = true;
        for(String s : result) {
            if(!s.contains("AUD")) {
                in = false;
            }
        }
        assertTrue(in);
    }

    /*
    Basic use case for get(). Returns multiple entries for get
     */
    @Test
    void getMultiple() {
        result = FileHandler.get("TEST");
        if (result.isEmpty()) {
            FileHandler.add("TEST", 1);
            FileHandler.add("TEST", 1);
            result = FileHandler.get("TEST");
            assertEquals(result.size(), 2);
            FileHandler.remove("TEST");
        } else {
            fail();
        }
    }

    /*
    Tests what happens when FileHandler.get() is passed with an empty string. An empty arraylist should be returned
     */
    @Test
    void getWithEmptyInput() {
        result = FileHandler.get("");
        assertTrue(result.isEmpty());
    }

    /*
    Tests what happens when FileHandler.get() is used with a null value. Should return with an empty arraylist
     */
    @Test
    void getWithNullInput(){
        String empty = null;
        result = FileHandler.get(empty);
        assertTrue(result.isEmpty());
    }

    /*
    Try get with a value that doesn't exist in the file. Should return an empty list.
     */
    @Test
    void getNonExistingEntry() {
        result = FileHandler.get("DOESNTEXIST");
        assertTrue(result.isEmpty());
    }

    /*
    Try to add a value that is not in the file.
     */
    @Test
    void addSuccessfully() {
        if(FileHandler.get("DOESNTEXIST").isEmpty()) {
            FileHandler.add("DOESNTEXIST", 1.078);
            result = FileHandler.get("DOESNTEXIST");
            assertFalse(result.isEmpty());
            FileHandler.remove("DOESNTEXIST");
        }
        else {
            fail();
        }
    }

    /*
    Try to add a currency with a negative value. All values should be greater than 0, so this should do nothing.
     */
    @Test
    void addNegativeValue() {
        if(FileHandler.get("NEGATIVECURRENCY").isEmpty()) {
            FileHandler.add("NEGATIVECURRENCY", -1);
            assertTrue(FileHandler.get("NEGATIVECURRENCY").isEmpty());
        }
        else {
            fail();
        }
    }

    /*
    Try to add a currency with 0 value. All values should be greater than 0, so doing this should do nothing.
     */
    @Test
    void addZeroValue() {
        FileHandler.add("ZEROVALUE", 0);
        assertTrue(FileHandler.get("ZEROVALUE").isEmpty());
    }

    /*
    Basic use case - add a value and a legal, customised date
     */
    @Test
    void addSuccessfullyWithDate() {
        if(FileHandler.get("DOESNTEXIST").isEmpty()) {
            FileHandler.add("DOESNTEXIST", 1, LocalDateTime.now());
            result = FileHandler.get("DOESNTEXIST");
            assertFalse(result.isEmpty());
            FileHandler.remove("DOESNTEXIST");
        }
        else {
            fail();
        }
    }

    /*
    Try to add a currency with a future date. Should not do anything.
     */
    @Test
    void addCurrencyWithFutureDate() {
        if(FileHandler.get("FutureDate").isEmpty()) {
            FileHandler.add("FutureDate", 1, LocalDateTime.now().plusDays(1));
            assertTrue(FileHandler.get("FutureDate").isEmpty());
        }
        else {
            fail();
        }
    }

    /*
    Add currency with the name == null and then name == "". Both should do nothing.
     */
    @Test
    void addNoCurrency() {
        String currency = null;
        FileHandler.add(currency, 1);
        assertTrue(FileHandler.get(currency).isEmpty()); // currency should not have been added to file
        currency = "";
        FileHandler.add(currency,1);
        assertTrue(FileHandler.get(currency).isEmpty());
    }

    /*
    Print out the set of all currency names in currencies.txt
     */
    @Test
    void showAllCurrencies() {
        result = FileHandler.getAllCurrencies();
        String name;
        boolean violated = false;
        Scanner reader = null;

        try {
            reader = new Scanner(new FileInputStream(file));
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem opening file");
            System.exit(1);
        }

        while(reader.hasNextLine() && !violated) {
            name = reader.nextLine().split(",")[0];
            if(!result.contains(name)) {
                violated = true;
            }
        }
        assertFalse(violated);
    }

}
