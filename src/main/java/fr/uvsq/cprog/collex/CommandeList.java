package fr.uvsq.cprog.collex;

import java.util.List;

public class CommandeList implements Commande {

    private Dns dns;
    private String domaine;
    private boolean trierParIP;

    public CommandeList(Dns dns, String domaine, boolean trierParIP) {
        this.dns = dns;
        this.domaine = domaine;
        this.trierParIP = trierParIP;
    }

    @Override
    public void execute() {
        try {
            List<DnsItem> items = dns.getItems(domaine);
            if (trierParIP) {
                items.sort((i1, i2) -> i1.getAdresseIP().getIp().compareTo(i2.getAdresseIP().getIp()));
            } else {
                items.sort((i1, i2) -> i1.getNomMachine().getNomComplet().compareTo(i2.getNomMachine().getNomComplet()));
            }
            for (DnsItem item : items) {
                System.out.println(item.getAdresseIP().getIp() + " " + item.getNomMachine().getNomComplet());
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
