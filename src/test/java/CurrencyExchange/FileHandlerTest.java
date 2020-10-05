package CurrencyExchange;

import org.junit.After;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Scanner;

import CurrencyExchange.FileHandler;

public class FileHandlerTest {
    /*
    Removes the last line from currencies.txt. This is to restore the file currencies.txt to its previous state when
    tests modify its contents, e.g. when successfully invoking the add function.
     */
    private void removeLastLine() {
        Scanner currencies = null;
        PrintWriter tempFile = null;
        File temp = new File("tempFile");
        File input = new File("currencies.txt");

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
        tempFile.print(currencies.nextLine());
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
        String line = null;
        line = FileHandler.find("USD");
        assertEquals("USD,1", line, "USD found in Currencies.txt");
    }

    @Test
    void findWithEmptyInput() {
        String line = null;
        line = FileHandler.find("");
        assertNull(line, "null input, null output");
    }

    @Test
    void findWithNullInput(){
        String empty = null;
        assertNull(FileHandler.find(empty));
    }

    @Test
    void findNonExistingEntry() {
        String line = null;
        line = FileHandler.find("NotFound");
        assertNull(line, "String \'NotFound\' not in currency.txt");
    }

    @Test
    void addSuccessfully() {
        String result = null;
        if(FileHandler.find("doesntExist") == null) {
            FileHandler.add("doesntExist", 1);
            result = FileHandler.find("doesntExist");
        }
        assertNotNull(result);
        removeLastLine();
    }


    @Test
    void addNegativeValue() {
        if(FileHandler.find("negativeCurrency") == null) {
            FileHandler.add("negativeCurrency", -1);
        }
        assertNull(FileHandler.find("negativeCurrency"));
    }

    @Test
    void addExistingCurrency() {
        FileHandler.add("USD", 2);
        // method add() should prevent USD,2 from being added to currencies.txt
        assertNotEquals("USD,2", FileHandler.find("USD"));
    }

    @Test
    void addNoCurrency() {
        String currency = null;
        FileHandler.add(currency, 1);
        assertNull(FileHandler.find(currency)); // currency should not have been added to file
        currency = "";
        FileHandler.add(currency,1);
        assertNull(FileHandler.find(currency));
    }

    @Test
    void remove() {

    }

}
