package controller;

import DAO.CursoDao;
import model.Curso;

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
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        HttpSession session = request.getSession(false);
        int professorId = (int) session.getAttribute("userId"); // Replace "userId" with the actual session attribute name storing the professor's ID

        Curso curso = new Curso(nome, descricao);
        try {
            cursoDao.createCurso(curso, professorId);
            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

}
