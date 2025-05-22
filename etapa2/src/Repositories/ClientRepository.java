package Repositories;

import Database.DatabaseConnection;
import Entitati.Client;
import Servicii.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements Repository<Client> {
    private static ClientRepository instance;
    private final Connection connection;
    private final AuditService auditService;

    private ClientRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.auditService = AuditService.getInstance();
    }

    public static ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }
        return instance;
    }

    @Override
    public void create(Client client) {
        String sql = "INSERT INTO clienti (nume, email, numar_comenzi, categorie) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, client.getNume());
            stmt.setString(2, client.getEmail());
            stmt.setInt(3, client.getNumarComenzi());
            stmt.setString(4, client.getCategorie());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                }
            }
            auditService.logAction("CREATE_CLIENT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client read(int id) {
        String sql = "SELECT * FROM clienti WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                    rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getString("email")
                );
                // Set additional fields
                for (int i = 0; i < rs.getInt("numar_comenzi"); i++) {
                    client.plaseazaComanda();
                }
                auditService.logAction("READ_CLIENT");
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE clienti SET nume = ?, email = ?, numar_comenzi = ?, categorie = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getNume());
            stmt.setString(2, client.getEmail());
            stmt.setInt(3, client.getNumarComenzi());
            stmt.setString(4, client.getCategorie());
            stmt.setInt(5, client.getId());
            stmt.executeUpdate();
            auditService.logAction("UPDATE_CLIENT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM clienti WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            auditService.logAction("DELETE_CLIENT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> readAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clienti";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client client = new Client(
                    rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getString("email")
                );
                // Set additional fields
                for (int i = 0; i < rs.getInt("numar_comenzi"); i++) {
                    client.plaseazaComanda();
                }
                clients.add(client);
            }
            auditService.logAction("READ_ALL_CLIENTS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Client findByEmail(String email) {
        String sql = "SELECT * FROM clienti WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                    rs.getInt("id"),
                    rs.getString("nume"),
                    rs.getString("email")
                );
                for (int i = 0; i < rs.getInt("numar_comenzi"); i++) {
                    client.plaseazaComanda();
                }
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 