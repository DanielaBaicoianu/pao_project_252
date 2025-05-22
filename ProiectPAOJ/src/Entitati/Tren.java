package Entitati;

import java.util.List;

public class Tren {
    private Locomotiva locomotiva;
    private List<Vagon> Vagoane;
    private Operator operator;
    private Mecanic mecanic;
    private boolean esteFolosit = false;

    public Tren(Locomotiva locomotiva, List<Vagon> vagoane, Operator operator, Mecanic mecanic) {
        this.locomotiva = locomotiva;
        int greutateTotala = 0;
        for (Vagon v : vagoane) {
            greutateTotala += v.getGreutate();
        }
        if(greutateTotala <= locomotiva.getCapacitate())
            Vagoane = vagoane;
        else {
            Vagoane = null;
            System.out.println("Vagoanele depasesc greutatea admisa de locomotiva!");
        }
        this.operator = operator;
        this.mecanic = mecanic;
        locomotiva.setEsteFolosita(true);
        vagoane.forEach(v -> v.setEsteFolosit(true));
        mecanic.setEsteFolosit(true);
        this.esteFolosit = false;
    }

    public boolean isEsteFolosit() {
        return esteFolosit;
    }

    public void setEsteFolosit(boolean esteFolosit) {
        this.esteFolosit = esteFolosit;
    }

    public Locomotiva getLocomotiva() {
        return locomotiva;
    }

    public void setLocomotiva(Locomotiva locomotiva) {
        this.locomotiva = locomotiva;
    }

    public List<Vagon> getVagoane() {
        return Vagoane;
    }

    public void setVagoane(List<Vagon> vagoane) {
        Vagoane = vagoane;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Mecanic getMecanic() {
        return mecanic;
    }

    public void setMecanic(Mecanic mecanic) {
        this.mecanic = mecanic;
    }

    @Override
    public String toString() {
        return "Tren{" +
                "locomotiva=" + locomotiva +
                ", Vagoane=" + Vagoane +
                ", operator=" + operator +
                ", mecanic=" + mecanic +
                '}';
    }
}
