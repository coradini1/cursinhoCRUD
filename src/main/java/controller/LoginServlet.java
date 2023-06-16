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

                HttpSession session = request.getSession();
                session.setAttribute("username", usuario.getNome());
                session.setAttribute("userId", usuario.getId());
                session.setAttribute("tipoUsuario", usuario.getTipoUsuario());
                System.out.println("User ID: " + usuario.getId());

                response.sendRedirect(response.encodeRedirectURL(contextPath + "/listarcursos"));
            } else {

                response.sendRedirect(response.encodeRedirectURL(contextPath + "/index.jsp"));
            }
        } catch (SQLException e) {
            String contextPath = request.getContextPath();

            e.printStackTrace();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/erro.jsp"));
        }
    }
}
