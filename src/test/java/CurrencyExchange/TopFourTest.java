package CurrencyExchange;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// import static org.junit.Assert.assertTrue;

public class TopFourTest {

    final static String file = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "topfour.txt";

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

    @Test
    public void addTest() {
        ArrayList<String> top = TopFour.getTopFour();
        if(!top.contains("TEST")) {
            TopFour.add("TEST");
            top = TopFour.getTopFour();
            assertTrue(top.contains("TEST"));
            TopFour.remove("Test");
        }
        else
            fail();
    }

    @Test
    public void removeTest() {
        ArrayList<String> top = TopFour.getTopFour();
        if(!top.contains("TEST")) {
            TopFour.add("TEST");
            top = TopFour.getTopFour();
            if (top.contains("TEST")) {
                TopFour.remove("test");
                Assertions.assertTrue(top.contains("TEST"));
            }
            else
                fail();
        }
        else
            fail();
    }

    @Test
    public void validateSuccess() {
        ArrayList<String> temp = TopFour.getTopFour();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening topfour.txt");
            System.exit(1);
        }

        writer.write("AUD\nUSD\nGBP\nFAKE");
        writer.close();

        assertFalse(TopFour.validate());

        // restore file to original state
        try {
            writer = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening file topfour.txt 2");
            System.exit(1);
        }

        writer.print(temp.get(0));
        for(int i = 1; i<temp.size(); i++) {
            writer.print(System.lineSeparator() + temp.get(i));
        }
        writer.close();
    }

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

        writer.write("AUD\nUSD\nGBP\nFAKE");
        writer.close();

        assertFalse(TopFour.validate());

        // restore file to original state
        try {
            writer = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening file topfour.txt 2");
            System.exit(1);
        }

        writer.print(temp.get(0));
        for(int i = 1; i<temp.size(); i++) {
            writer.print(System.lineSeparator() + temp.get(i));
        }
        writer.close();
    }

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

        // restore file to original state
        try {
            writer = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("Problems opening file topfour.txt 2");
            System.exit(1);
        }

        writer.print(temp.get(0));
        for(int i = 1; i<temp.size(); i++) {
            writer.print(System.lineSeparator() + temp.get(i));
        }
        writer.close();
    }

}
