package DAO;

import model.Curso;

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
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getProfessorId());
            stmt.executeUpdate();
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
                System.out.println(cursos);
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
}
