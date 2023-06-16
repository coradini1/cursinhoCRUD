package controller;

import DAO.CursoDao;
import model.Curso;
import model.TipoUsuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registrarcurso")
public class RegistrarCursoServlet extends HttpServlet {
    private CursoDao cursoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cursoDao = new CursoDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Adicionando a verificação do tipo de usuário
        TipoUsuario tipoUsuario = (TipoUsuario) request.getSession().getAttribute("tipoUsuario");
        if (tipoUsuario.equals(TipoUsuario.aluno)) {
            response.sendRedirect("erro.jsp");
            return;
        }

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String contextPath = request.getContextPath();
        HttpSession session = request.getSession(false);
        int professorId = (int) session.getAttribute("userId"); // Retrieve the professor's ID from the session

        Curso curso = new Curso(nome, descricao, professorId);
        try {
            System.out.println(curso.getProfessorId());
            cursoDao.createCurso(curso); // Pass both curso and professorId to the createCurso method
            response.sendRedirect(request.getContextPath() + "/listarcursos");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
