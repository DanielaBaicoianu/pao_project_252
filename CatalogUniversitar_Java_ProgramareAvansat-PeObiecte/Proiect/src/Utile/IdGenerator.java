package Utile;

public final class IdGenerator {
    private static int next = 1;
    private IdGenerator() {}
    public static synchronized int generareId() {
        return next++;
    }
}
