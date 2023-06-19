package DAO;

import model.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDao {
    private Connection conn;

    public MaterialDao() {
        this.conn = new MySqlConnection().getConexao();
    }

    public void createMaterial(Material material) throws SQLException {
        String sql = "INSERT INTO Materiais (nome, descricao, caminho_arquivo, curso_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getNome());
            stmt.setString(2, material.getDescricao());
            stmt.setString(3, material.getCaminhoArquivo());
            stmt.setInt(4, material.getCursoId());
            stmt.executeUpdate();
        }
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
            material.setCaminhoArquivo(rs.getString("caminho_arquivo"));
            material.setCursoId(rs.getInt("curso_id"));
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
            material.setCaminhoArquivo(rs.getString("caminho_arquivo"));
            material.setCursoId(rs.getInt("curso_id"));
            return material;
        }

        rs.close();
        stmt.close();

        return null;
    }

    public void updateMaterial(Material material) throws SQLException {
        String sql = "UPDATE Materiais SET nome = ?, descricao = ?, caminho_arquivo = ?, curso_id = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, material.getNome());
        stmt.setString(2, material.getDescricao());
        stmt.setString(3, material.getCaminhoArquivo());
        stmt.setInt(4, material.getCursoId());
        stmt.setInt(5, material.getId());
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
