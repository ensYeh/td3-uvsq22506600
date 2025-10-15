package fr.uvsq.cprog.collex;
import static org.junit.Assert.*;
import org.junit.Test;
import fr.uvsq.cprog.collex.AdresseIPInvalideException;


public class AdresseIPTest {
    @Test
    public void testAdresseValide() throws AdresseIPInvalideException {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", ip.toString());
}

    @Test(expected = AdresseIPInvalideException.class)
        public void testAdresseInvalide() throws AdresseIPInvalideException {
        new AdresseIP("999.999.0.1");
    }
}