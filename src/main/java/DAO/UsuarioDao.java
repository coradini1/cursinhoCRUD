package DAO;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDao {

    private Connection conn;

    public UsuarioDao() {
        this.conn = new MySqlConnection().getConexao();
    }


    public Usuario createUser(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, idade, senha, tipoUsuario) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setInt(2, usuario.getIdade());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getTipoUsuario().toString());
        stmt.executeUpdate();
        stmt.close();
    }



}

}
