package Exceptii;

public class ExceptieGaraLipsa extends RuntimeException {
    public ExceptieGaraLipsa() {
        super("GARA NU A FOST GASITA");
    }
}
