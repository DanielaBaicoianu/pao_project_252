package Database;

public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/magazin_muzical";
    private static final String USER = "dariadragomir";
    private static final String PASSWORD = "postgres";

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
} 