package fr.uvsq.cprog.collex;
import static org.junit.Assert.*;
import org.junit.Test;
import fr.uvsq.cprog.collex.AdresseIPInvalideException;

public class NomMachineTest {
    @Test
    public void testNomValide() throws NomMachineInvalideException {
      NomMachine n = new NomMachine("www.uvsq.fr");
      assertEquals("uvsq.fr", n.getNomDomaine());
}


    @Test(expected = NomMachineInvalideException.class)
      public void testNomInvalide() throws NomMachineInvalideException {
      new NomMachine("invalidname");
    }
}