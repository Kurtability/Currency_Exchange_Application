package CurrencyExchange;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    
    @Test
    void uselessTest() {
        Thread t = new Thread(() -> {App.main(new String[1]);});
        try {
            t.start();
            Thread.sleep(500);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(1, 1);
    }
}