package Repositories;

import Database.DatabaseConnection;
import Entitati.Client;
import Entitati.Comanda;
import Entitati.Produs;
import Servicii.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaRepository implements Repository<Comanda> {
    private static ComandaRepository instance;
    private final Connection connection;
    private final AuditService auditService;
    private final ProdusRepository produsRepository;
    private final ClientRepository clientRepository;

    private ComandaRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.auditService = AuditService.getInstance();
        this.produsRepository = ProdusRepository.getInstance();
        this.clientRepository = ClientRepository.getInstance();
    }

    public static ComandaRepository getInstance() {
        if (instance == null) {
            instance = new ComandaRepository();
        }
        return instance;
    }

    @Override
    public void create(Comanda comanda) {
        String sql = "INSERT INTO comenzi (client_id, status, data_comanda) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, comanda.getClient().getId());
            stmt.setString(2, comanda.getStatus());
            stmt.setTimestamp(3, new Timestamp(comanda.getDataPlasare().getTime()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    comanda.setId(rs.getInt(1));
                    // Insert order items
                    insertOrderItems(comanda);
                }
            }
            auditService.logAction("CREATE_COMANDA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertOrderItems(Comanda comanda) throws SQLException {
        String sql = "INSERT INTO comanda_produse (comanda_id, produs_id, cantitate, pret_unitar) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Produs produs : comanda.getProduse()) {
                stmt.setInt(1, comanda.getId());
                stmt.setInt(2, produs.getId());
                stmt.setInt(3, 1); // Assuming quantity is 1 for simplicity
                stmt.setDouble(4, produs.getPret());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public Comanda read(int id) {
        String sql = "SELECT c.*, cp.produs_id, cp.cantitate, cp.pret_unitar " +
                    "FROM comenzi c " +
                    "LEFT JOIN comanda_produse cp ON c.id = cp.comanda_id " +
                    "WHERE c.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Comanda comanda = null;
            List<Produs> produse = new ArrayList<>();

            while (rs.next()) {
                if (comanda == null) {
                    Client client = clientRepository.read(rs.getInt("client_id"));
                    if (client != null) {
                        comanda = new Comanda(client);
                        comanda.setId(rs.getInt("id"));
                        comanda.setStatus(rs.getString("status"));
                    }
                }

                int produsId = rs.getInt("produs_id");
                if (produsId > 0) {
                    Produs produs = produsRepository.read(produsId);
                    if (produs != null) {
                        produse.add(produs);
                    }
                }
            }

            if (comanda != null) {
                for (Produs produs : produse) {
                    comanda.adaugaProdus(produs);
                }
                auditService.logAction("READ_COMANDA");
                return comanda;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Comanda comanda) {
        String sql = "UPDATE comenzi SET client_id = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, comanda.getClient().getId());
            stmt.setString(2, comanda.getStatus());
            stmt.setInt(3, comanda.getId());
            stmt.executeUpdate();

            // Delete existing order items
            deleteOrderItems(comanda.getId());
            // Insert new order items
            insertOrderItems(comanda);

            auditService.logAction("UPDATE_COMANDA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteOrderItems(int comandaId) throws SQLException {
        String sql = "DELETE FROM comanda_produse WHERE comanda_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, comandaId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) {
        try {
            // Delete order items first
            deleteOrderItems(id);

            // Delete the order
            String sql = "DELETE FROM comenzi WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
            auditService.logAction("DELETE_COMANDA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comanda> readAll() {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT id FROM comenzi";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Comanda comanda = read(rs.getInt("id"));
                if (comanda != null) {
                    comenzi.add(comanda);
                }
            }
            auditService.logAction("READ_ALL_COMENZI");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comenzi;
    }
} 