package DAO;

import model.Curso;
import model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {
    private Connection conn;

    public CursoDao() {
        conn = new MySqlConnection().getConexao();
    }

    public void createCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO Cursos (nome, descricao, professor_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getProfessorId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int courseId = rs.getInt(1);
                    curso.setId(courseId);
                }
            }
        }
    }

    public Curso getCursoById(int id) throws SQLException {
        String sql = "SELECT * FROM Cursos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso curso = new Curso();
                    curso.setId(rs.getInt("id"));
                    curso.setNome(rs.getString("nome"));
                    curso.setDescricao(rs.getString("descricao"));
                    return curso;
                }
            }
        }
        return null;
    }

    public List<Curso> getAllCursos() throws SQLException {
        String sql = "SELECT * FROM Cursos";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                List<Curso> cursos = new ArrayList<>();
                while (rs.next()) {
                    Curso curso = new Curso();
                    curso.setId(rs.getInt("id"));
                    curso.setNome(rs.getString("nome"));
                    curso.setDescricao(rs.getString("descricao"));
                    cursos.add(curso);
                }
                return cursos;
            }
        }
    }

    public void updateCurso(Curso curso) throws SQLException {
        String sql = "UPDATE Cursos SET nome = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCurso(int id) throws SQLException {
        String sql = "DELETE FROM Cursos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void vincularMaterial(int courseId, int materialId) throws SQLException {
        String sql = "INSERT INTO CursosMateriais (curso_id, material_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, materialId);
            stmt.executeUpdate();
        }
    }

    public void desvincularMaterial(int courseId, int materialId) throws SQLException {
        String sql = "DELETE FROM CursosMateriais WHERE curso_id = ? AND material_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, materialId);
            stmt.executeUpdate();
        }
    }

    public List<Material> getMateriaisByCursoId(int courseId) throws SQLException {
        String sql = "SELECT Materiais.id, Materiais.nome, Materiais.descricao " +
                "FROM Materiais " +
                "JOIN CursosMateriais ON Materiais.id = CursosMateriais.material_id " +
                "WHERE CursosMateriais.curso_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Material> materiais = new ArrayList<>();
                while (rs.next()) {
                    Material material = new Material();
                    material.setId(rs.getInt("id"));
                    material.setNome(rs.getString("nome"));
                    material.setDescricao(rs.getString("descricao"));
                    materiais.add(material);
                }
                return materiais;
            }
        }
    }
}
