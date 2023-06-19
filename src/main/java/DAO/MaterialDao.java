package DAO;

import model.Material;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDao {
    private Connection conn;

    public MaterialDao() {
        this.conn = new MySqlConnection().getConexao();
    }

    public void createMaterial(Material material, InputStream pdfStream) throws SQLException {
        String sql = "INSERT INTO Materiais (nome, descricao, arquivo_pdf) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getNome());
            stmt.setString(2, material.getDescricao());
            stmt.setBinaryStream(3, pdfStream);
            stmt.executeUpdate();
        }
    }
    public void savePDF(int materialId, InputStream pdfStream) throws SQLException {
        String sql = "UPDATE Materiais SET arquivo_pdf = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBlob(1, pdfStream);
            stmt.setInt(2, materialId);
            stmt.executeUpdate();
        }
    }

    public InputStream getPDF(int materialId) throws SQLException {
        String sql = "SELECT arquivo_pdf FROM Materiais WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, materialId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Blob blob = rs.getBlob("arquivo_pdf");
                    return blob.getBinaryStream();
                }
            }
        }
        return null;
    }

    public List<Material> getMateriais() throws SQLException {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM Materiais";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Material material = new Material();
            material.setId(rs.getInt("id"));
            material.setNome(rs.getString("nome"));
            material.setDescricao(rs.getString("descricao"));
            materiais.add(material);
        }

        rs.close();
        stmt.close();

        return materiais;
    }

    public Material getMaterialById(int materialId) throws SQLException {
        String sql = "SELECT * FROM Materiais WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, materialId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Material material = new Material();
            material.setId(rs.getInt("id"));
            material.setNome(rs.getString("nome"));
            material.setDescricao(rs.getString("descricao"));
            return material;
        }

        rs.close();
        stmt.close();

        return null;
    }

    public void updateMaterial(Material material) throws SQLException {
        String sql = "UPDATE Materiais SET nome = ?, descricao = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, material.getNome());
        stmt.setString(2, material.getDescricao());
        stmt.setInt(3, material.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void deleteMaterial(int materialId) throws SQLException {
        String sql = "DELETE FROM Materiais WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, materialId);
        stmt.executeUpdate();
        stmt.close();
    }
}
