package DAO;

import model.TipoUsuario;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    private Connection conn;

    public UsuarioDao() {
        this.conn = new MySqlConnection().getConexao();
    }

    public void create(Usuario usuario) throws SQLException {
        if (isUsernameTaken(usuario.getNome())) {
            throw new SQLException("Username is already taken.");
        }

        String sql = "INSERT INTO usuarios (nome, idade, senha, tipoUsuario) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setInt(2, usuario.getIdade());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getTipoUsuario().toString());
        stmt.executeUpdate();
        stmt.close();
    }

    public Usuario getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setIdade(rs.getInt("idade"));
            usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipoUsuario")));
            return usuario;
        }

        return null; // User not found
    }

    public Usuario getUserByName(String username) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nome = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setIdade(rs.getInt("idade"));
            usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipoUsuario")));
            return usuario;
        }

        return null; // User not found
    }

    private boolean isUsernameTaken(String username) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM usuarios WHERE nome = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt("count");
        rs.close();
        stmt.close();
        return count > 0;
    }
}
