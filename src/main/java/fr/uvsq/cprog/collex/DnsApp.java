package fr.uvsq.cprog.collex;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DnsApp {

    public static void main(String[] args) {
        try {
            // Charger le fichier config.properties depuis la racine
            Properties props = new Properties();
            try (FileInputStream input = new FileInputStream("config.properties")) {
                props.load(input);
            }

            // Lire la clé dns.file
            String nomFichier = props.getProperty("dns.file");
            if (nomFichier == null || nomFichier.isBlank()) {
                throw new IllegalArgumentException("Clé 'dns.file' absente ou vide dans config.properties");
            }

            // Créer le chemin vers le fichier de base
            Path fichier = Paths.get(nomFichier);

            // Charger la base DNS
            Dns dns = new Dns(fichier);

            // Initialiser l’interface utilisateur
            DnsTUI tui = new DnsTUI(dns);

            System.out.println("Bienvenue dans le DNS TUI !");
            while (true) {
                Commande commande = tui.nextCommande();
                commande.execute();
            }

        } catch (IOException e) {
            System.out.println("Erreur d'accès au fichier : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur critique : " + e.getMessage());
        }
    }
}
