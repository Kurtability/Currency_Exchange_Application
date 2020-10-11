package CurrencyExchange;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationTest {

    @Test
    public void test_checkCredentials(){
        assertEquals(false, Authentication.checkCredentials("admin","123"));
        assertEquals(false, Authentication.checkCredentials("",""));
        assertEquals(false, Authentication.checkCredentials("admin",""));
        assertEquals(true, Authentication.checkCredentials("admin","musk"));

    }



}
