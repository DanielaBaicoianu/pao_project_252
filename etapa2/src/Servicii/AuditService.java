package Servicii;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static AuditService instance;
    private static final String AUDIT_FILE = "audit.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        // Initialize the instance when the class is loaded
        instance = new AuditService();
    }

    private AuditService() {
        // Create the audit file with headers if it doesn't exist
        try (PrintWriter writer = new PrintWriter(new FileWriter(AUDIT_FILE, true))) {
            if (new java.io.File(AUDIT_FILE).length() == 0) {
                writer.println("nume_actiune,timestamp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AuditService getInstance() {
        return instance;
    }

    public void logAction(String actionName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AUDIT_FILE, true))) {
            String timestamp = LocalDateTime.now().format(formatter);
            writer.println(actionName + "," + timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 