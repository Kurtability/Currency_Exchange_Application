package CurrencyExchange;

import org.junit.jupiter.api.Test;

// import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// import static org.junit.Assert.assertTrue;

public class TopFourTest {

    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "topfour.txt";

    // method to restore topfour.txt to its previous state. It requires oldFile to contain topfour.txt's prev state.
    private void restoreFile(ArrayList<String> oldFile) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening file topfour.txt 2");
            System.exit(1);
        }

        writer.print(oldFile.get(0));
        for(int i = 1; i<oldFile.size(); i++) {
            writer.print(System.lineSeparator() + oldFile.get(i));
        }
        writer.close();
    }

    /*
    This method should return the top four currencies. This test will check if its done its job correctly by checking whats
    returned by getTopFour() with the contents of the file directly.
     */
    @Test
    public void getTopFourTest() {
        ArrayList<String> top = TopFour.getTopFour();
        String line;
        boolean valid = true;

        Scanner reader = null;
        try {
            reader = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening topfour.txt");
            System.exit(1);
        }

        while(reader.hasNextLine()) {
            line = reader.nextLine();
            if(!top.contains(line)) {
                valid = false;
            }
        }
        reader.close();
        assertTrue(valid);
    }

    /*
     This test will replace the contents of currencies.txt with "TEST1", "TEST2", "TEST3" and "TEST4". It will populate
     topfour.txt with the same values and then check to see if the file is valid. This should pass.
     */
    @Test
    public void validateSuccess() {
        ArrayList<String> temp = TopFour.getTopFour();
        ArrayList<String> currenciesText = new ArrayList<>();

        String doc = "src/main/resources/currencies.txt";

        Scanner reader = null;
        try {
            reader = new Scanner(new File(doc));
        } catch (FileNotFoundException e) {
            System.out.println("Problem opening currencies.txt");
            e.printStackTrace();
            System.exit(1);
        }

        while(reader.hasNextLine()) {
            currenciesText.add(reader.nextLine());
        }

        reader.close();

        FileHandler.add("TEST1", 1);
        FileHandler.add("TEST2", 1);
        FileHandler.add("TEST3", 1);
        FileHandler.add("TEST4", 1);

        TopFour.add("TEST1", "TEST2", "TEST3", "TEST4");

        assertTrue(TopFour.validate());

        // Restore currencies.txt
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(doc));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening currencies.txt");
            System.exit(1);
        }

        if(!currenciesText.isEmpty()) {
            writer.print(currenciesText.get(0));
        }
        for(int i=1; i<currenciesText.size(); i++) {
            writer.print(System.lineSeparator() + currenciesText.get(i));
        }
        writer.close();

        // restore topfour.txt
        restoreFile(temp);
    }

    /*
    All entries in topfour.txt should first exist in currencies.txt. If an entry in topfour.txt doesn't also exist in
    currencies.txt then the validation method should fail.
     */
    @Test
    public void validateFailNotInCurrencies() {
        ArrayList<String> temp = TopFour.getTopFour();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening topfour.txt");
            System.exit(1);
        }

        writer.write("AUD\nUSD\nGBP\nFAKE"); // FAKE not in currencies.txt
        writer.close();

        assertFalse(TopFour.validate());

        restoreFile(temp);
    }

    /*
    topfour.txt should only have four entries, no more, no less.
     */
    @Test
    public void validateFailTooManyCurrencies() {
        ArrayList<String> temp = TopFour.getTopFour();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening topfour.txt");
            System.exit(1);
        }

        writer.write("AUD\nUSD\nGBP\nEUR\nMYR");
        writer.close();

        assertFalse(TopFour.validate());

        restoreFile(temp);
    }

}
