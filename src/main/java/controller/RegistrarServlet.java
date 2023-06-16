package controller;

import DAO.UsuarioDao;
import model.TipoUsuario;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registrarUser")
public class RegistrarServlet extends HttpServlet {
    private UsuarioDao usuarioDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDao = new UsuarioDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        int idade = Integer.parseInt(request.getParameter("idade"));
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(request.getParameter("tipoUsuario"));

        try {
            if (usuarioDao.getUserByName(nome) != null) {
                // Username already taken, display an error message
                request.setAttribute("errorMessage", "O nome de usuário já está em uso. Por favor, escolha outro nome.");
                if(!response.isCommitted()) {
                    request.getRequestDispatcher("/registrar/registrar.jsp").forward(request, response);
                }
                return;
            } else {
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setSenha(senha);
                usuario.setIdade(idade);
                usuario.setTipoUsuario(tipoUsuario);

                usuarioDao.create(usuario);
                // Redirect to a confirmation page
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }
        } catch (SQLException e) {
            // Handle database-related errors
            e.printStackTrace();
            // Redirect to an error page
            if(!response.isCommitted()) {
                response.sendRedirect(request.getContextPath() + "/erro.jsp");
            }
        }
    }
}