package fr.uvsq.cprog.collex;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DnsApp {

    public static void main(String[] args) {
        try {
            Path fichier = Paths.get("dns.txt"); // nom du fichier de la base
            Dns dns = new Dns(fichier);          // chargement ou création du fichier
            DnsTUI tui = new DnsTUI(dns);        // interface utilisateur

            System.out.println("Bienvenue dans le DNS TUI !");
            while (true) {
                Commande commande = tui.nextCommande();
                commande.execute();              // exécution de la commande
            }
        } catch (Exception e) {
            System.out.println("Erreur critique : " + e.getMessage());
        }
    }
}
