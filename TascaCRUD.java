import java.sql.*;
import java.util.*;
import java.util.Date;

public class TascaCRUD {
    private Connection conn;

    // Constructor per establir connexió amb la base de dades
    public TascaCRUD(Connection conn) {
        this.conn = conn;
    }

    // Crear la base de dades i taules (també amb dades inicials)
    public void crearBaseDeDades() throws SQLException {
        String createDb = "CREATE DATABASE IF NOT EXISTS gestio_tasques";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createDb);
        }

        String useDb = "USE gestio_tasques";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(useDb);
        }

        String createTables = """
            CREATE TABLE IF NOT EXISTS Equip (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nom VARCHAR(100) NOT NULL
            );
            CREATE TABLE IF NOT EXISTS Tasca (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nom VARCHAR(100) NOT NULL,
                descripcio TEXT,
                prioritat INT,
                data_lim DATE,
                equip_id INT,
                FOREIGN KEY (equip_id) REFERENCES Equip(id)
            );
        """;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTables);
        }
    }

    // Insereix un registre a la taula Tasca
    public void inserirTasca(String nom, String descripcio, int prioritat, Date data_lim, int equip_id) throws SQLException {
        String sql = "INSERT INTO Tasca (nom, descripcio, prioritat, data_lim, equip_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nom);
            ps.setString(2, descripcio);
            ps.setInt(3, prioritat);
            ps.setDate(4, (java.sql.Date) data_lim);
            ps.setInt(5, equip_id);
            ps.executeUpdate();
        }
    }

    // Mostrar totes les tasques amb paginació
    public List<Tasca> mostrarTasques(int limit, int offset) throws SQLException {
        List<Tasca> tasques = new ArrayList<>();
        String sql = "SELECT * FROM Tasca LIMIT ? OFFSET ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tasques.add(new Tasca(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("descripcio"),
                            rs.getInt("prioritat"),
                            rs.getDate("data_lim"),
                            rs.getInt("equip_id")
                    ));
                }
            }
        }
        return tasques;
    }
}
