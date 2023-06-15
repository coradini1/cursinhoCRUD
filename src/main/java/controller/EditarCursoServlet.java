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

@WebServlet("/editar-curso")
public class EditarCursoServlet extends HttpServlet {
    private CursoDao cursoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cursoDao = new CursoDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int courseId = Integer.parseInt(idParam);
            try {
                Curso curso = cursoDao.getCursoById(courseId);
                if (curso != null) {
                    request.setAttribute("curso", curso);
                    request.getRequestDispatcher("/view/editar-curso.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/erro.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/erro.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/listarcursos");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Curso curso = new Curso();
        curso.setId(courseId);
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