package CurrencyExchange;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Scanner;

import CurrencyExchange.FileHandler;

public class FileHandlerTest {
    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "currencies.txt";
    ArrayList<String> result;
    /*
    Removes the last line from currencies.txt. This is to restore the file currencies.txt to its previous state when
    tests modify its contents, e.g. when successfully invoking the add function.
     */
    
    @Test
    void testRemove() {
        String toDelete = "AAA";
        FileHandler.add("AAA", 1.4352);
        FileHandler.remove("AAA");
        assertTrue(FileHandler.get("AAA").isEmpty());
    }

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

    @Test
    void getMultiple() {
        result = FileHandler.get("TEST");
        if(result.isEmpty()) {
            FileHandler.add("TEST", 1);
            FileHandler.add("TEST", 1);
            result = FileHandler.get("TEST");
            assertEquals(result.size(), 2);
            FileHandler.remove("TEST");
        }
        else {
            fail();
        }
    }

    @Test
    void getWithEmptyInput() {
        result = FileHandler.get("");
        assertTrue(result.isEmpty());
    }

    @Test
    void getWithNullInput(){
        String empty = null;
        result = FileHandler.get(empty);
        assertTrue(FileHandler.get(empty).isEmpty());
    }

    @Test
    void getNonExistingEntry() {
        result = FileHandler.get("DOESNTEXIST");
        assertTrue(result.isEmpty());
    }

    @Test
    void addSuccessfully() {
        if(FileHandler.get("DOESNTEXIST").isEmpty()) {
            FileHandler.add("DOESNTEXIST", 1);
            result = FileHandler.get("DOESNTEXIST");
            assertFalse(result.isEmpty());
            FileHandler.remove("DOESNTEXIST");
        }
        else {
            fail();
        }
    }

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

    @Test
    void addNoCurrency() {
        String currency = null;
        FileHandler.add(currency, 1);
        assertTrue(FileHandler.get(currency).isEmpty()); // currency should not have been added to file
        currency = "";
        FileHandler.add(currency,1);
        assertTrue(FileHandler.get(currency).isEmpty());
    }

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
