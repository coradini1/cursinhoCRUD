package controller;

import DAO.MaterialDao;
import model.Material;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@WebServlet("/registrar-material")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // Configura o tamanho máximo do arquivo para 10 MB
public class RegistrarMaterialServlet extends HttpServlet {
    private MaterialDao materialDao;

    @Override
    public void init() throws ServletException {
        super.init();
        materialDao = new MaterialDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        Part arquivoPDF = request.getPart("arquivoPDF");

        Material material = new Material();
        material.setNome(nome);
        material.setDescricao(descricao);

        try (InputStream pdfStream = arquivoPDF.getInputStream()) {
            materialDao.createMaterial(material, pdfStream);
            response.sendRedirect(request.getContextPath() + "/listarcursos");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/erro.jsp");
        }
    }
}
