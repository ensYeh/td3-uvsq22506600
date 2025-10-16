package fr.uvsq.cprog.collex;

public class CommandeAdd implements Commande {

    private Dns dns;
    private String ip;
    private String nomMachine;

    public CommandeAdd(Dns dns, String ip, String nomMachine) {
        this.dns = dns;
        this.ip = ip;
        this.nomMachine = nomMachine;
    }

    @Override
    public void execute() {
        try {
            dns.addItem(new AdresseIP(ip), new NomMachine(nomMachine));
            System.out.println("Ajout effectué : " + ip + " " + nomMachine);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
