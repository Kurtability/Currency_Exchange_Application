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
    final static String currencyText = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "currencies.txt";

    // method to restore topfour.txt to its previous state. It requires oldFile to contain topfour.txt's prev state.
    private void restoreFile(ArrayList<String> oldFile) {
        restoreFile(oldFile, new File(file));
    }


    // method to restore a file to its previous state. It requires oldFile to contain the file's previous state.
    private void restoreFile(ArrayList<String> oldFile, File doc){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(doc);
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening file " + doc.getName());
            System.exit(1);
        }

        writer.print(oldFile.get(0));
        for (int i = 1; i < oldFile.size(); i++) {
            writer.print(System.lineSeparator() + oldFile.get(i));
        }
        writer.close();
    }


    // Helper method. This stores the contents of currency.txt in memory.
    private ArrayList<String> getCurrenciesText() {
        ArrayList<String> result = new ArrayList<>();

        Scanner reader = null;
        try {
            reader = new Scanner(new File(currencyText));
        } catch (FileNotFoundException e) {
            System.out.println("Problem opening currencies.txt");
            e.printStackTrace();
            System.exit(1);
        }

        while (reader.hasNextLine()) {
            result.add(reader.nextLine());
        }

        reader.close();
        return result;
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

        while (reader.hasNextLine()) {
            line = reader.nextLine();
            if (!top.contains(line)) {
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
        ArrayList<String> currenciesText = getCurrenciesText();

        FileHandler.add("TEST1", 1);
        FileHandler.add("TEST2", 1);
        FileHandler.add("TEST3", 1);
        FileHandler.add("TEST4", 1);

        TopFour.add("TEST1", "TEST2", "TEST3", "TEST4");

        assertTrue(TopFour.validate());

        // Restore currencies.txt
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(currencyText));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening currencies.txt");
            System.exit(1);
        }

        if (!currenciesText.isEmpty()) {
            writer.print(currenciesText.get(0));
        }
        for (int i = 1; i < currenciesText.size(); i++) {
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

    /*
    Basic use case - replace all the entries in topfour.txt with new currencies. Before adding them we should make sure
    the original text doesn't already have these strings as members.
     */
    @Test
    public void addSuccess() {
        ArrayList<String> temp = TopFour.getTopFour();
        boolean pass = true;

        String[] strings = { "S1", "S2", "S3", "S4" };
        for(String s : strings) {
            if(temp.contains(s)) {
                pass = false; // These strings shouldn't be in the original topfour text
            }
        }

        if(pass) {
            TopFour.add(strings[0], strings[1], strings[2], strings[3]);
            ArrayList<String> newTemp = TopFour.getTopFour();
            for(String s : strings) {
                if(!newTemp.contains(s)){
                    pass = false; // These strings should be in topfour.txt now
                }
            }
            if(newTemp.size() != 4) {
                pass = false; // topfour.txt should only have 4 entries
            }
        }
        restoreFile(temp);
        assertTrue(pass);
    }

    /*
    Add a currency that is null. The method should fail and there will be no changes to the file.
     */
    @Test
    public void addFailNullEntry() {
        ArrayList<String> temp = TopFour.getTopFour();
        String s1 = null;
        TopFour.add(s1, "s2", "s3", "s4");

        // The topfour.txt file should be have no changes
        boolean pass = true;
        ArrayList<String> newTemp = TopFour.getTopFour();
        for(String s : temp) {
            if(!newTemp.contains(s)) {
                pass = false;
            }
        }
        assertTrue(pass);
    }

    /*
    Add a currency that is an empty string. The method should fail and there will be no changes to the file.
     */
    @Test
    public void addFailEmptyEntry() {
        ArrayList<String> temp = TopFour.getTopFour();
        String s1 = "";
        TopFour.add(s1, "s2", "s3", "s4");

        // The topfour.txt file should be have no changes
        boolean pass = true;
        ArrayList<String> newTemp = TopFour.getTopFour();
        for(String s : temp) {
            if(!newTemp.contains(s)) {
                pass = false;
            }
        }
        assertTrue(pass);
    }

    /*
    This test makes sure that what
     */
    @Test
    public void getValuesSuccess() {
        ArrayList<String> temp = TopFour.getTopFour();
        ArrayList<String> newTemp = getCurrenciesText();

        // delete the contents of currency.txt
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(currencyText));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        writer.close();
        String[] strs = { "ONE,1", "TWO,1", "THREE,1", "FOUR,1", "ONE,2", "TWO,2", "THREE,2", "ONE,3" };

        FileHandler.add("One", 1);
        FileHandler.add("Two", 1);
        FileHandler.add("Three", 1);
        FileHandler.add("Four", 1);

        FileHandler.add("One", 2);
        FileHandler.add("Two", 2);
        FileHandler.add("Three", 2);
        FileHandler.add("One", 3);

        TopFour.add("One", "Two", "Three", "Four");

        /*
         The next line of code should output something like the following:
         [[ONE,2.0,DATETIMEOBJECT, ONE,3.0,DATETIMEOBJECT],
         [TWO,1.0,DATETIMEOBJECT, TWO,2.0,DATETIMEOBJECT],
         [THREE,1.0,DATETIMEOBJECT, THREE,2.0,DATETIMEOBJECT],
         [FOUR,1.0,DATETIMEOBJECT]]
         */

        ArrayList<ArrayList<String>> results = TopFour.getValues();
        boolean valid = true;

        ArrayList<String> al = results.get(0);
        String entry = al.get(0);
        if(!entry.contains("ONE,2")) {
            valid = false;
            System.out.println("fails at 1");
        }
        entry = al.get(1);
        if(!entry.contains("ONE,3")) {
            valid = false;
        }

        al = results.get(1);
        entry = al.get(0);
        if(!entry.contains("TWO,1")) {
            valid = false;
            System.out.println("fails at 2");
        }
        entry = al.get(1);
        if(!entry.contains("TWO,2")) {
            valid = false;
        }

        al = results.get(2);
        entry = al.get(0);
        if(!entry.contains("THREE,1")) {
            valid = false;
            System.out.println("fails at 3");
        }
        entry = al.get(1);
        if(!entry.contains("THREE,2")) {
            valid = false;
        }

        al = results.get(3);
        entry = al.get(0);
        if(!entry.contains("FOUR,1")) {
            valid = false;
        }

        assertTrue(valid);
        restoreFile(newTemp, new File(currencyText));
        restoreFile(temp);
    }

}
