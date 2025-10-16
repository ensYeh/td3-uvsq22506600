package fr.uvsq.cprog.collex;

public class CommandeGetIP implements Commande {

    private Dns dns;
    private String nomMachine;

    public CommandeGetIP(Dns dns, String nomMachine) {
        this.dns = dns;
        this.nomMachine = nomMachine;
    }

    @Override
    public void execute() {
        try {
            DnsItem item = dns.getItem(new NomMachine(nomMachine));
            System.out.println(item.getAdresseIP().getIp());
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
