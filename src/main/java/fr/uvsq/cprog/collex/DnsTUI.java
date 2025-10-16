package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {

    private Dns dns;
    private Scanner scanner;

    public DnsTUI(Dns dns) {
        this.dns = dns;
        this.scanner = new Scanner(System.in);
    }

    public Commande nextCommande() {
        System.out.print("> ");
        String input = scanner.nextLine().trim();
        String[] tokens = input.split(" ");

        if (tokens[0].equalsIgnoreCase("add") && tokens.length == 3) {
            return new CommandeAdd(dns, tokens[1], tokens[2]);
        } else if (tokens[0].equalsIgnoreCase("ls")) {
            boolean trierParIP = tokens.length > 2 && tokens[1].equals("-a");
            String domaine = trierParIP ? tokens[2] : tokens[1];
            return new CommandeList(dns, domaine, trierParIP);
        } else if (tokens[0].equalsIgnoreCase("quit")) {
            return new CommandeQuit();
        } else if (tokens[0].matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            return new CommandeGetNom(dns, tokens[0]);
        } else {
            return new CommandeGetIP(dns, tokens[0]);
        }
    }

    public void affiche(String message) {
        System.out.println(message);
    }
}
