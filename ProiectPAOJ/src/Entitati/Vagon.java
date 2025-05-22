package Entitati;

public abstract class Vagon {
    protected boolean esteFolosit;
    protected int greutate;
    public abstract int getGreutate();

    public boolean isEsteFolosit() {
        return esteFolosit;
    }

    public void setEsteFolosit(boolean esteFolosit) {
        this.esteFolosit = esteFolosit;
    }
}
