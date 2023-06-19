package controller;

import DAO.MaterialDao;
import model.Material;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editar-material")
public class EditarMaterialServlet extends HttpServlet {
    private MaterialDao materialDao;

    @Override
    public void init() throws ServletException {
        super.init();
        materialDao = new MaterialDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String materialId = request.getParameter("id");

        try {
            Material material = materialDao.getMaterialById(Integer.parseInt(materialId));
            if (material != null) {
                request.setAttribute("material", material);
                request.getRequestDispatcher("/editar-material.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/listarmateriais");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/erro.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String materialId = request.getParameter("id");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        try {
            Material material = materialDao.getMaterialById(Integer.parseInt(materialId));
            if (material != null) {
                material.setNome(nome);
                material.setDescricao(descricao);
                materialDao.updateMaterial(material);
                response.sendRedirect(request.getContextPath() + "/listarmateriais");
            } else {
                response.sendRedirect(request.getContextPath() + "/listarmateriais");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/erro.jsp");
        }
    }
}
