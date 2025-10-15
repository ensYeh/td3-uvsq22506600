package fr.uvsq.cprog.collex;
import fr.uvsq.cprog.collex.AdresseIPInvalideException;

public class AdresseIP {

    private final String ip;
    public AdresseIP(String ip) throws AdresseIPInvalideException {
       if (!estValide(ip)) {
        throw new AdresseIPInvalideException("Adresse IP invalide : " + ip);
        }
      this.ip = ip;
    }

    private boolean estValide(String ip) {
        String[] parties = ip.split("\\.");
        if (parties.length != 4) return false;
        try {
            for (String p : parties) {
            int valeur = Integer.parseInt(p);
            if (valeur < 0 || valeur > 255) return false;
            }
            return true;
        } catch (NumberFormatException e) {
        return false;
        }
    }

    public String getIp() {
       return ip;
    }

    @Override
        public String toString() {
        return ip;
    }
}