package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DnsTest {

    private Path fichier;
    private Dns dns;

    @Before
    public void setUp() throws IOException, AdresseIPInvalideException, NomMachineInvalideException {
        fichier = Paths.get("dns_test.txt");
        Files.deleteIfExists(fichier); // on repart à zéro
        dns = new Dns(fichier);
    }

    @Test
    public void testAddAndGetItem() throws IOException, AdresseIPInvalideException, NomMachineInvalideException {
        AdresseIP ip = new AdresseIP("192.168.1.10");
        NomMachine nom = new NomMachine("serveur.local");
        dns.addItem(ip, nom);

        DnsItem item = dns.getItem(new AdresseIP("192.168.1.10"));
        assertEquals("serveur.local", item.getNomMachine().getNomComplet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDoubleNom() throws IOException, AdresseIPInvalideException, NomMachineInvalideException {
        dns.addItem(new AdresseIP("192.168.1.10"), new NomMachine("serveur.local"));
        dns.addItem(new AdresseIP("192.168.1.11"), new NomMachine("serveur.local"));
    }

    @Test
    public void testPersistence() throws IOException, AdresseIPInvalideException, NomMachineInvalideException {
        dns.addItem(new AdresseIP("192.168.1.10"), new NomMachine("serveur.local"));
        Dns dnsReloaded = new Dns(fichier);

        List<DnsItem> items = dnsReloaded.getItems("local");
        assertEquals(1, items.size());
        assertEquals("192.168.1.10", items.get(0).getAdresseIP().getIp());
    }
}
