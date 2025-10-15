package fr.uvsq.cprog.collex;
import static org.junit.Assert.*;
import org.junit.Test;
import fr.uvsq.cprog.collex.*;

public class DnsItemTest {
    @Test
    public void testToString() throws AdresseIPInvalideException, NomMachineInvalideException {
        DnsItem item = new DnsItem(new AdresseIP("193.51.31.90"), new NomMachine("www.uvsq.fr"));
        assertEquals("193.51.31.90 www.uvsq.fr", item.toString());
    }
}