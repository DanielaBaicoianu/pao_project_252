package Repositories;

import Database.DatabaseConnection;
import Entitati.Furnizor;
import Servicii.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FurnizorRepository implements Repository<Furnizor> {
    private static FurnizorRepository instance;
    private final Connection connection;
    private final AuditService auditService;

    private FurnizorRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.auditService = AuditService.getInstance();
    }

    public static FurnizorRepository getInstance() {
        if (instance == null) {
            instance = new FurnizorRepository();
        }
        return instance;
    }

    @Override
    public void create(Furnizor furnizor) {
        String sql = "INSERT INTO furnizori (nume, email, telefon, adresa) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, furnizor.getNume());
            stmt.setString(2, furnizor.getContact());
            stmt.setString(3, "");
            stmt.setString(4, "");
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    furnizor.setId(rs.getInt(1));
                }
            }
            auditService.logAction("CREATE_FURNIZOR");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Furnizor read(int id) {
        String sql = "SELECT * FROM furnizori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Furnizor furnizor = new Furnizor(
                    rs.getString("nume"),
                    rs.getString("email")
                );
                furnizor.setId(rs.getInt("id"));
                auditService.logAction("READ_FURNIZOR");
                return furnizor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Furnizor furnizor) {
        String sql = "UPDATE furnizori SET nume = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, furnizor.getNume());
            stmt.setString(2, furnizor.getContact());
            stmt.setInt(3, furnizor.getId());
            stmt.executeUpdate();
            auditService.logAction("UPDATE_FURNIZOR");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM furnizori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            auditService.logAction("DELETE_FURNIZOR");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Furnizor> readAll() {
        List<Furnizor> furnizori = new ArrayList<>();
        String sql = "SELECT * FROM furnizori";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Furnizor furnizor = new Furnizor(
                    rs.getString("nume"),
                    rs.getString("email")
                );
                furnizor.setId(rs.getInt("id"));
                furnizori.add(furnizor);
            }
            auditService.logAction("READ_ALL_FURNIZORI");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return furnizori;
    }
} 