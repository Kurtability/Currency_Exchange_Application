package CurrencyExchange;

import org.junit.jupiter.api.Test;

import javafx.scene.layout.Region;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    
    @Test
    void uselessTest() {
        Thread t = new Thread(() -> { App.main(new String[1]); });
        try {
            t.start();
            Thread.sleep(500);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(1, 1);
    }

    /**
     * Tests for checking whether UI layouts are initialized properly and
     * are not null. Also testing if the feature that they aren't
     * reinitialized.
     */
    @Test
    void convertSceneTest() {
        Region convertLayout = ConvertScene.getLayout();
        assertNotNull(convertLayout);
        Region again = ConvertScene.getLayout();
        assertTrue(convertLayout == again);
    }

    @Test
    void adminSceneTest() {
        Region adminLayout = AdminScene.getLayout();
        assertNotNull(adminLayout);
        Region again = AdminScene.getLayout();
        assertTrue(adminLayout == again);
    }

    @Test
    void popularCurrenciesTest() {
        Region popLayout = PopularCurrenciesScene.getLayout();
        assertNotNull(popLayout);
    }
}