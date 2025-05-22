package Repositories;

import Database.DatabaseConnection;
import Entitati.Recenzie;
import Entitati.Client;
import Entitati.Produs;
import Servicii.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecenzieRepository implements Repository<Recenzie> {
    private static RecenzieRepository instance;
    private final Connection connection;
    private final AuditService auditService;
    private final ClientRepository clientRepository;
    private final ProdusRepository produsRepository;

    private RecenzieRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.auditService = AuditService.getInstance();
        this.clientRepository = ClientRepository.getInstance();
        this.produsRepository = ProdusRepository.getInstance();
    }

    public static RecenzieRepository getInstance() {
        if (instance == null) {
            instance = new RecenzieRepository();
        }
        return instance;
    }

    @Override
    public void create(Recenzie recenzie) {
        String sql = "INSERT INTO recenzii (client_id, produs_id, rating, comentariu, data_creare) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, recenzie.getClient().getId());
            stmt.setInt(2, recenzie.getProdus().getId());
            stmt.setInt(3, recenzie.getRating());
            stmt.setString(4, recenzie.getComentariu());
            stmt.setTimestamp(5, new Timestamp(recenzie.getDataCreare().getTime()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    recenzie.setId(rs.getInt(1));
                }
            }
            auditService.logAction("CREATE_RECENZIE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Recenzie read(int id) {
        String sql = "SELECT * FROM recenzii WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = clientRepository.read(rs.getInt("client_id"));
                Produs produs = produsRepository.read(rs.getInt("produs_id"));
                Recenzie recenzie = new Recenzie(client, produs, rs.getString("comentariu"), rs.getInt("rating"));
                recenzie.setId(rs.getInt("id"));
                recenzie.setDataCreare(rs.getTimestamp("data_creare"));
                auditService.logAction("READ_RECENZIE");
                return recenzie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Recenzie recenzie) {
        String sql = "UPDATE recenzii SET client_id = ?, produs_id = ?, rating = ?, comentariu = ?, data_creare = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, recenzie.getClient().getId());
            stmt.setInt(2, recenzie.getProdus().getId());
            stmt.setInt(3, recenzie.getRating());
            stmt.setString(4, recenzie.getComentariu());
            stmt.setTimestamp(5, new Timestamp(recenzie.getDataCreare().getTime()));
            stmt.setInt(6, recenzie.getId());
            stmt.executeUpdate();
            auditService.logAction("UPDATE_RECENZIE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM recenzii WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            auditService.logAction("DELETE_RECENZIE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Recenzie> readAll() {
        List<Recenzie> recenzii = new ArrayList<>();
        String sql = "SELECT * FROM recenzii";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client client = clientRepository.read(rs.getInt("client_id"));
                Produs produs = produsRepository.read(rs.getInt("produs_id"));
                Recenzie recenzie = new Recenzie(client, produs, rs.getString("comentariu"), rs.getInt("rating"));
                recenzie.setId(rs.getInt("id"));
                recenzie.setDataCreare(rs.getTimestamp("data_creare"));
                recenzii.add(recenzie);
            }
            auditService.logAction("READ_ALL_RECENZII");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzii;
    }

    // Additional method to get reviews for a specific product
    public List<Recenzie> getRecenziiByProdus(int produsId) {
        List<Recenzie> recenzii = new ArrayList<>();
        String sql = "SELECT * FROM recenzii WHERE produs_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, produsId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client = clientRepository.read(rs.getInt("client_id"));
                Produs produs = produsRepository.read(rs.getInt("produs_id"));
                Recenzie recenzie = new Recenzie(client, produs, rs.getString("comentariu"), rs.getInt("rating"));
                recenzie.setId(rs.getInt("id"));
                recenzie.setDataCreare(rs.getTimestamp("data_creare"));
                recenzii.add(recenzie);
            }
            auditService.logAction("READ_RECENZII_BY_PRODUS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzii;
    }

    // Additional method to get reviews by a specific client
    public List<Recenzie> getRecenziiByClient(int clientId) {
        List<Recenzie> recenzii = new ArrayList<>();
        String sql = "SELECT * FROM recenzii WHERE client_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client = clientRepository.read(rs.getInt("client_id"));
                Produs produs = produsRepository.read(rs.getInt("produs_id"));
                Recenzie recenzie = new Recenzie(client, produs, rs.getString("comentariu"), rs.getInt("rating"));
                recenzie.setId(rs.getInt("id"));
                recenzie.setDataCreare(rs.getTimestamp("data_creare"));
                recenzii.add(recenzie);
            }
            auditService.logAction("READ_RECENZII_BY_CLIENT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recenzii;
    }
} 