package Entitati;

public class VagonCalatori extends Vagon{
    private int capacitate;
    private int locuriOcupate;

    public VagonCalatori(int capacitate, int greutate, int locuriOcupate) {
        this.capacitate = capacitate;
        this.greutate = greutate;
        this.locuriOcupate = locuriOcupate;
        this.esteFolosit = false;
    }

    public int getGreutate(){
        return greutate + locuriOcupate * 80;
    }

    @Override
    public String toString() {
        return "CALATORI - " + capacitate + " locuri " + greutate + " kg greutate";
    }
}
