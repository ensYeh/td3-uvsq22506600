package fr.uvsq.cprog.collex;
import fr.uvsq.cprog.collex.NomMachineInvalideException;

public class NomMachine {

    private final String nomComplet;

    public NomMachine(String nomComplet) throws NomMachineInvalideException {
      if (!estValide(nomComplet)) {
        throw new NomMachineInvalideException("Nom de machine invalide : " + nomComplet);
        }
      this.nomComplet = nomComplet;
    }

    private boolean estValide(String nom) {
      return nom != null && nom.contains(".") && !nom.startsWith(".") && !nom.endsWith(".");
    }

    public String getNomComplet() {
      return nomComplet;
    }

    public String getNomDomaine() {
        int index = nomComplet.indexOf('.');
        if (index == -1) return "";
          return nomComplet.substring(index + 1);
    }

    @Override
        public String toString() {
        return nomComplet;
    }
}