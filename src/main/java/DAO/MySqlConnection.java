package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/cursinho";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        new MySqlConnection().getConexao();
        System.out.println("Conexão executada com sucesso!!");
    }

    public Connection getConexao() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Classe de conexão com o banco não configurada");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("URL ou Usuário ou Senha do banco errados");
            e.printStackTrace();
        }

        return conn;
    }
}
