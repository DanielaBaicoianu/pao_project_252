package Entitati;

public class VagonMarfa extends Vagon {
    private String tipMarfa;
    public VagonMarfa(String tipMarfa, int greutate) {
        this.tipMarfa = tipMarfa;
        this.greutate = greutate;
        this.esteFolosit = false;
    }

    public int getGreutate(){
        return greutate;
    }
    @Override
    public String toString() {
        return "MARFA - " + greutate + " kg greutate " + tipMarfa ;
    }
}
