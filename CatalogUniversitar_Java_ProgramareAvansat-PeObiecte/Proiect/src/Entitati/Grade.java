package Entitati;

public class Grade {
    private final int idNota;
    private final Enrollment inscriere;
    private final double valoareNota;

    public Grade(int idNota, Enrollment inscriere, double valoareNota) {
        this.idNota = idNota;
        this.inscriere = inscriere;
        this.valoareNota = valoareNota;
    }

    public int getIdNota() {
        return idNota;
    }
    public Enrollment getInscriere() {
        return inscriere;
    }
    public double getValoareNota() {
        return valoareNota;
    }
}
