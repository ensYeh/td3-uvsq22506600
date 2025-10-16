package fr.uvsq.cprog.collex;

public class CommandeGetNom implements Commande {

    private Dns dns;
    private String ip;

    public CommandeGetNom(Dns dns, String ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public void execute() {
        try {
            DnsItem item = dns.getItem(new AdresseIP(ip));
            System.out.println(item.getNomMachine().getNomComplet());
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}

