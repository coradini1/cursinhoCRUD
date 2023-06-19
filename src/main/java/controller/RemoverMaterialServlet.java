package controller;

import DAO.MaterialDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/remover-material")
public class RemoverMaterialServlet extends HttpServlet {
    private MaterialDao materialDao;

    @Override
    public void init() throws ServletException {
        super.init();
        materialDao = new MaterialDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String materialIdString = request.getParameter("id");
        int materialId = Integer.parseInt(materialIdString);

        try {
            materialDao.deleteMaterial(materialId);
            response.sendRedirect(request.getContextPath() + "/listarmateriais");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/erro.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
