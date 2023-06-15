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
import java.util.List;

@WebServlet("/listarcursos")

public class ListarCursosServlet extends HttpServlet {
    private CursoDao cursoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cursoDao = new CursoDao();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        try {
            List<Curso> cursos = cursoDao.getAllCursos();
            System.out.println(cursos);
            request.setAttribute("cursos", cursos);
            request.getRequestDispatcher( "/view/menu.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(contextPath + "/erro.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteId = request.getParameter("deleteId");
        if (deleteId != null) {
            int courseId = Integer.parseInt(deleteId);
            try {
                cursoDao.deleteCurso(courseId);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/erro.jsp");
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/listarcursos");
    }
}

