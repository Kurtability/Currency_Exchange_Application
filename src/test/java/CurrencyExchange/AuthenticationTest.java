package CurrencyExchange;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationTest {

    @Test
    public void test_checkCredentials(){
        assertEquals(false, new Authentication().checkCredentials("admin","123"));
        assertEquals(false, new Authentication().checkCredentials("",""));
        assertEquals(false, new Authentication().checkCredentials("admin",""));
        assertEquals(true, new Authentication().checkCredentials("admin","musk"));

    }



}
