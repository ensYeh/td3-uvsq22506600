package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Properties;

public class DnsTest {

    private Path fichier;
    private Dns dns;

    @Before
    public void setUp() throws IOException, AdresseIPInvalideException, NomMachineInvalideException {
        // Charger le fichier config.properties
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            props.load(input);
        }

        // Lire la clé dns.test.file pour la base de test
        String cheminFichierTest = props.getProperty("dns.test.file");
        if (cheminFichierTest == null || cheminFichierTest.isBlank()) {
            throw new IllegalArgumentException("Clé 'dns.test.file' absente ou vide dans config.properties");
        }

        fichier = Paths.get(cheminFichierTest);

        // Supprimer l’ancien fichier pour repartir à zéro
        Files.deleteIfExists(fichier);

        // Initialiser la base DNS pour les tests
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
