package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Curso;

public class CursoDao {
    private Connection conn;

    public CursoDao() {
        this.conn = new MySqlConnection().getConexao();
    }

    public void createCurso(Curso curso, int professorId) throws SQLException {
        String sql = "INSERT INTO Cursos (nome, descricao, professor_id) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, curso.getNome());
        stmt.setString(2, curso.getDescricao());
        stmt.setInt(3, professorId);
        stmt.executeUpdate();
        stmt.close();
    }

    public Curso getCursoById(int id) throws SQLException {
        String sql = "SELECT * FROM Cursos WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Curso curso = null;
        if (rs.next()) {
            curso = new Curso();
            curso.setId(rs.getInt("id"));
            curso.setNome(rs.getString("nome"));
            curso.setDescricao(rs.getString("descricao"));
        }

        rs.close();
        stmt.close();
        return curso;
    }

    public List<Curso> getAllCursos() throws SQLException {
        String sql = "SELECT * FROM Cursos";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Curso> cursos = new ArrayList<>();
        while (rs.next()) {
            Curso curso = new Curso();
            curso.setId(rs.getInt("id"));
            curso.setNome(rs.getString("nome"));
            curso.setDescricao(rs.getString("descricao"));
            cursos.add(curso);
        }

        rs.close();
        stmt.close();
        return cursos;
    }

    public void updateCurso(Curso curso) throws SQLException {
        String sql = "UPDATE Cursos SET nome = ?, descricao = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, curso.getNome());
        stmt.setString(2, curso.getDescricao());
        stmt.setInt(3, curso.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void deleteCurso(int id) throws SQLException {
        String sql = "DELETE FROM Cursos WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
