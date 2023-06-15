package controller;

import DAO.UsuarioDao;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioDao usuarioDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDao = new UsuarioDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Usuario usuario = usuarioDao.getUser(username, password);

            String contextPath = request.getContextPath();
            if (usuario != null) {
                // Successful login, set the "username" and "userId" attributes in the session
                HttpSession session = request.getSession();
                session.setAttribute("username", usuario.getNome());
                session.setAttribute("userId", usuario.getId()); // Assuming the ID property in Usuario is named "id"
                System.out.println("User ID: " + usuario.getId());
                // Redirect to the menu page or any other desired page
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/listarcursos"));
            } else {
                // Invalid username or password, redirect back to the login page
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/index.jsp"));
            }
        } catch (SQLException e) {
            String contextPath = request.getContextPath();
            // Handle database-related errors
            e.printStackTrace();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/erro.jsp"));
        }
    }

}
