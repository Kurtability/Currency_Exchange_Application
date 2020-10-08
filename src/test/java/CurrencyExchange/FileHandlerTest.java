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
    private void removeLastLine() {
        Scanner currencies = null;
        PrintWriter tempFile = null;
        File temp = new File("tempFile");
        File input = new File(file);

        try{
            tempFile = new PrintWriter(temp);
            currencies = new Scanner(input);
        }
        catch(FileNotFoundException e) {
            System.out.println("Can't open files");
            System.exit(0);
        }

        String line;
        boolean secondLastLine = false;
        if(currencies.hasNextLine()) {
            tempFile.print(currencies.nextLine());
        }
        while(currencies.hasNextLine() && !secondLastLine) {
            line = currencies.nextLine();
            if(!currencies.hasNextLine()) {
                // We've reached the second last line
                secondLastLine = true;
            }
            else {
                tempFile.print(System.lineSeparator() + line);
            }
        }
        currencies.close();
        tempFile.close();

        boolean rename = temp.renameTo(input);
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
            removeLastLine();
            removeLastLine();
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
            removeLastLine();
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

    @Test
    void addExistingCurrency() {
        if(FileHandler.get("USD").size() == 1){
            FileHandler.add("USD", 2);
            assertEquals(FileHandler.get("USD").size(), 2);
            removeLastLine();
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
        //TODO
    }

    @Test
    void remove() {

    }

}
