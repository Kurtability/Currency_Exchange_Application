package CurrencyExchange;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import CurrencyExchange.FileHandler;

public class FileHandlerTest {

    @Test
    void find() {
        String line = null;
        line = FileHandler.find("USD");
        assertEquals("USD,1", line, "USD found in Currencies.txt");
    }

    @Test
    void findNull() {
        String line = null;
        line = FileHandler.find("");
        assertNull(line, "null input, null output");
    }

    @Test
    void findDoesntExist() {
        String line = null;
        line = FileHandler.find("NotFound");
        assertNull(line, "String \'NotFound\' not in currency.txt");
    }

    @Test
    void add() {

    }

    @Test
    void remove() {

    }

}
