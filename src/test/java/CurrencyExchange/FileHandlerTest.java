package CurrencyExchange;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
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
        String toDelete = "EUR";
        FileHandler.remove("EUR");
        assertTrue(FileHandler.get("EUR").isEmpty());
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
        result = FileHandler.get("test");
        if(result.isEmpty()) {
            FileHandler.add("test", 1);
            FileHandler.add("test", 1);
            result = FileHandler.get("test");
            assertEquals(result.size(), 2);
            FileHandler.remove("test");
            FileHandler.remove("test");
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
        result = FileHandler.get("doesntExist");
        assertTrue(result.isEmpty());
    }

    @Test
    void addSuccessfully() {
        if(FileHandler.get("doesntExist").isEmpty()) {
            FileHandler.add("doesntExist", 1);
            result = FileHandler.get("doesntExist");
            assertFalse(result.isEmpty());
            FileHandler.remove("doesnt");
        }
        else {
            fail();
        }
    }


    @Test
    void addNegativeValue() {
        if(FileHandler.get("negativeCurrency").isEmpty()) {
            FileHandler.add("negativeCurrency", -1);
            assertTrue(FileHandler.get("negativeCurrency").isEmpty());
        }
        else {
            fail();
        }
    }

    /*@Test
    void addExistingCurrency() {
        if(FileHandler.get("USD").size() == 1){
            FileHandler.add("USD", 2);
            assertEquals(FileHandler.get("USD").size(), 2);
            FileHandler.remove("USD");
        }
        else {
            fail();
        }
    }*/

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
        //TODO
    }

    @Test
    void remove() {

    }

}
