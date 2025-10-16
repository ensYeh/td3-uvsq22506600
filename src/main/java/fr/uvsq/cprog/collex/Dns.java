package fr.uvsq.cprog.collex;
import fr.uvsq.cprog.collex.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Classe représentant un serveur DNS simulé.
 */
public class Dns {
    private final Map<String, DnsItem> parNom = new HashMap<>();
    private final Map<String, DnsItem> parIP = new HashMap<>();
    private final Path fichier;

    public Dns(Path fichier) throws IOException, AdresseIPInvalideException, NomMachineInvalideException {
        this.fichier = fichier;
        chargerBase();
    }

    /**
     * Charge la base de données à partir du fichier texte.
     */
    private void chargerBase() throws IOException, AdresseIPInvalideException, NomMachineInvalideException {
        if (!Files.exists(fichier)) return;
        List<String> lignes = Files.readAllLines(fichier);
        for (String ligne : lignes) {
            if (ligne.isBlank()) continue;
            String[] parts = ligne.split(" ");
            if (parts.length != 2) continue;
            AdresseIP ip = new AdresseIP(parts[1]);
            NomMachine nom = new NomMachine(parts[0]);
            DnsItem item = new DnsItem(ip, nom);
            parNom.put(nom.getNomComplet(), item);
            parIP.put(ip.getIp(), item);
        }
    }

    /**
     * Recherche un item par nom de machine.
     */
    public DnsItem getItem(NomMachine nom) {
        return parNom.get(nom.getNomComplet());
    }

    /**
     * Recherche un item par adresse IP.
     */
    public DnsItem getItem(AdresseIP ip) {
        return parIP.get(ip.getIp());
    }

    /**
     * Retourne la liste des items d’un domaine donné.
     */
    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> resultat = new ArrayList<>();
        for (DnsItem item : parNom.values()) {
            if (item.getNomMachine().getNomDomaine().equalsIgnoreCase(domaine)) {
                resultat.add(item);
            }
        }
        resultat.sort(Comparator.comparing(i -> i.getNomMachine().getNomComplet()));
        return resultat;
    }

    /**
     * Ajoute un nouvel item dans la base de données et met à jour le fichier.
     */
    public void addItem(AdresseIP ip, NomMachine nom)
            throws IOException, IllegalArgumentException {
        if (parIP.containsKey(ip.getIp()))
            throw new IllegalArgumentException("ERREUR : L’adresse IP existe déjà !");
        if (parNom.containsKey(nom.getNomComplet()))
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");

        DnsItem item = new DnsItem(ip, nom);
        parIP.put(ip.getIp(), item);
        parNom.put(nom.getNomComplet(), item);
        sauvegarder();
    }

    /**
     * Écrit la base de données mise à jour dans le fichier.
     */
    private void sauvegarder() throws IOException {
    List<String> lignes = new ArrayList<>();
    for (DnsItem item : parNom.values()) {
        lignes.add(item.getNomMachine().getNomComplet() + " " + item.getAdresseIP().getIp());
    }
    Files.write(fichier, lignes);
}
}
