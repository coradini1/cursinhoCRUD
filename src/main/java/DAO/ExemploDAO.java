package DAO;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExemploDAO {

    public Usuario getUsuario(String login){
        Usuario usuario = new Usuario();
        try(Connection con = new MySqlConnection().getConexao()){

            String sql = "SELECT * " +
                    "FROM usuario WHERE login = ?";

            PreparedStatement pt = con.prepareStatement(sql);
            pt.setString(1, login);
            System.out.println("sql: "+pt.toString());
            ResultSet rs = pt.executeQuery();

            while (rs.next()){
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Nome do usuario: "+usuario.getNome());

        return usuario;
    }

}
