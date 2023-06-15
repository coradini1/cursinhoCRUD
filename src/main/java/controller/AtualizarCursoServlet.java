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

@WebServlet("/atualizar-curso")
public class AtualizarCursoServlet extends HttpServlet {
    private CursoDao cursoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cursoDao = new CursoDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Curso curso = new Curso();
        curso.setId(id);
        curso.setNome(nome);
        curso.setDescricao(descricao);

        try {
            cursoDao.updateCurso(curso);
            response.sendRedirect(request.getContextPath() + "/listarcursos");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/erro.jsp");
        }
    }
}
