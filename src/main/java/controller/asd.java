package controller;

import DAO.UsuarioDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registrar")
public class RegistrarServlet extends HttpServlet {
    // ...

    @Override
    public void init() throws ServletException {
        super.init();
        // Inicialize quaisquer recursos necessários, como o objeto UsuarioDao
        UsuarioDao usuarioDao = new UsuarioDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ... Lógica de registro do usuário ...
    }
}
