package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection {
    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/cursinho";
        String user = "root";
        String password = "password";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Successful to the database" + url);
            String query = "Insert into usuarios(id,nome,idade,tipoUsuario,senha) values(3,'asdasd',13,'ADMIN','123')";
            Statement statement = connection.createStatement();
            statement.execute(query);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}