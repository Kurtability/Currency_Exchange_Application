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
    void findSuccess() {
        result = FileHandler.find("USD");
        boolean in = true;
        for(String s : result) {
            if(!s.contains("USD")) {
                in = false;
            }
        }
        assertTrue(in);
    }

    @Test
    void findMultiple() {
        result = FileHandler.find("MYR");
        if(result.size() == 1) {
            FileHandler.add("MYR", 1);
            result = FileHandler.find("MYR");
            assertEquals(result.size(), 2);
            removeLastLine();
        }
        else {
            fail();
        }
    }

    @Test
    void findWithEmptyInput() {
        result = FileHandler.find("");
        assertTrue(result.isEmpty(), "null input, empty output");
    }

    @Test
    void findWithNullInput(){
        String empty = null;
        result = FileHandler.find(empty);
        assertTrue(FileHandler.find(empty).isEmpty());
    }

    @Test
    void findNonExistingEntry() {
        result = FileHandler.find("doesntExist");
        assertTrue(result.isEmpty(), "String \'NotFound\' not in currency.txt");
    }

    @Test
    void addSuccessfully() {
        if(FileHandler.find("doesntExist").isEmpty()) {
            FileHandler.add("doesntExist", 1);
            result = FileHandler.find("doesntExist");
            assertFalse(result.isEmpty());
            removeLastLine();
        }
        else {
            fail();
        }
    }


    @Test
    void addNegativeValue() {
        if(FileHandler.find("negativeCurrency").isEmpty()) {
            FileHandler.add("negativeCurrency", -1);
            assertTrue(FileHandler.find("negativeCurrency").isEmpty());
        }
        else {
            fail();
        }
    }

    @Test
    void addExistingCurrency() {
        if(FileHandler.find("USD").size() == 1){
            FileHandler.add("USD", 2);
            assertEquals(FileHandler.find("USD").size(), 2);
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
        assertTrue(FileHandler.find(currency).isEmpty()); // currency should not have been added to file
        currency = "";
        FileHandler.add(currency,1);
        assertTrue(FileHandler.find(currency).isEmpty());
    }

    @Test
    void showAllCurrencies() {
        //TODO
    }

    @Test
    void remove() {

    }

}
