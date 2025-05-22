package Repositories;

import Database.DatabaseConnection;
import Entitati.Factura;
import Entitati.Comanda;
import Servicii.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepository implements Repository<Factura> {
    private static FacturaRepository instance;
    private final Connection connection;
    private final AuditService auditService;
    private final ComandaRepository comandaRepository;

    private FacturaRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.auditService = AuditService.getInstance();
        this.comandaRepository = ComandaRepository.getInstance();
    }

    public static FacturaRepository getInstance() {
        if (instance == null) {
            instance = new FacturaRepository();
        }
        return instance;
    }

    @Override
    public void create(Factura factura) {
        String sql = "INSERT INTO facturi (numar_factura, data_emitere, client_id, total, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, "FACT-" + factura.getId());
            stmt.setTimestamp(2, new Timestamp(factura.getDataFactura().getTime()));
            stmt.setInt(3, factura.getComanda().getClient().getId()); // Using client_id as furnizor_id for now
            stmt.setDouble(4, factura.getTotal());
            stmt.setString(5, "NEPLATITA");
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    factura.setId(rs.getInt(1));
                }
            }
            auditService.logAction("CREATE_FACTURA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Factura read(int id) {
        String sql = "SELECT * FROM facturi WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Comanda comanda = comandaRepository.read(rs.getInt("furnizor_id")); // Using furnizor_id as client_id for now
                Factura factura = new Factura(comanda, rs.getDouble("total"));
                factura.setId(rs.getInt("id"));
                factura.setDataFactura(rs.getTimestamp("data_emitere"));
                auditService.logAction("READ_FACTURA");
                return factura;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Factura factura) {
        String sql = "UPDATE facturi SET numar_factura = ?, data_emitere = ?, furnizor_id = ?, total = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "FACT-" + factura.getId());
            stmt.setTimestamp(2, new Timestamp(factura.getDataFactura().getTime()));
            stmt.setInt(3, factura.getComanda().getClient().getId()); // Using client_id as furnizor_id for now
            stmt.setDouble(4, factura.getTotal());
            stmt.setString(5, "NEPLATITA");
            stmt.setInt(6, factura.getId());
            stmt.executeUpdate();
            auditService.logAction("UPDATE_FACTURA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM facturi WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            auditService.logAction("DELETE_FACTURA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Factura> readAll() {
        List<Factura> facturi = new ArrayList<>();
        String sql = "SELECT * FROM facturi";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Comanda comanda = comandaRepository.read(rs.getInt("client_id")); // Using furnizor_id as client_id for now
                Factura factura = new Factura(comanda, rs.getDouble("total"));
                factura.setId(rs.getInt("id"));
                factura.setDataFactura(rs.getTimestamp("data_emitere"));
                facturi.add(factura);
            }
            auditService.logAction("READ_ALL_FACTURI");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturi;
    }
} 