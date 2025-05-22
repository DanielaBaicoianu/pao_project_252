package Repositories;

import Database.DatabaseConnection;
import Entitati.Produs;
import Servicii.AuditService;
import Util.TipProdus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdusRepository implements Repository<Produs> {
    private static ProdusRepository instance;
    private final Connection connection;
    private final AuditService auditService;

    private ProdusRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.auditService = AuditService.getInstance();
    }

    public static ProdusRepository getInstance() {
        if (instance == null) {
            instance = new ProdusRepository();
        }
        return instance;
    }

    @Override
    public void create(Produs produs) {
        String sql = "INSERT INTO produse (nume, pret, tip_produs) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, produs.getNume());
            stmt.setDouble(2, produs.getPret());
            stmt.setString(3, produs.getTip_produs().toString());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    produs.setId(rs.getInt(1));
                }
            }
            auditService.logAction("CREATE_PRODUS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produs read(int id) {
        String sql = "SELECT * FROM produse WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Produs produs = new Produs(
                    rs.getString("nume"),
                    rs.getDouble("pret")
                );
                produs.setId(rs.getInt("id"));
                produs.setTip_produs(TipProdus.valueOf(rs.getString("tip_produs")));
                auditService.logAction("READ_PRODUS");
                return produs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Produs produs) {
        String sql = "UPDATE produse SET nume = ?, pret = ?, tip_produs = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produs.getNume());
            stmt.setDouble(2, produs.getPret());
            stmt.setString(3, produs.getTip_produs().toString());
            stmt.setInt(4, produs.getId());
            stmt.executeUpdate();
            auditService.logAction("UPDATE_PRODUS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM produse WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            auditService.logAction("DELETE_PRODUS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produs> readAll() {
        List<Produs> produse = new ArrayList<>();
        String sql = "SELECT * FROM produse";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produs produs = new Produs(
                    rs.getString("nume"),
                    rs.getDouble("pret")
                );
                produs.setId(rs.getInt("id"));
                produs.setTip_produs(TipProdus.valueOf(rs.getString("tip_produs")));
                produse.add(produs);
            }
            auditService.logAction("READ_ALL_PRODUSE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produse;
    }

    public Produs findByNume(String nume) {
        String sql = "SELECT * FROM produse WHERE nume = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nume);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Produs produs = new Produs(
                    rs.getString("nume"),
                    rs.getDouble("pret")
                );
                produs.setId(rs.getInt("id"));
                produs.setTip_produs(TipProdus.valueOf(rs.getString("tip_produs")));
                return produs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 