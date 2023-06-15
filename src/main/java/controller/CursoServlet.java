package controller;

import DAO.CursoDao;
import model.Curso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registrar-curso")
public class CursoServlet extends HttpServlet {
    private CursoDao cursoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cursoDao = new CursoDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        int professorId = Integer.parseInt(request.getParameter("professorId"));

        Curso curso = new Curso(nome, descricao, professorId);
        try {
            cursoDao.createCurso(curso);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("menu.jsp");
    }
}
