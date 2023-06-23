package controller;

import DAO.MaterialDao;
import model.Material;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    private MaterialDao materialDao;

    @Override
    public void init() throws ServletException {
        super.init();
        materialDao = new MaterialDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String materialIdParam = request.getParameter("id");
        if (materialIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'materialId' é necessário.");
            return;
        }

        try {
            int materialId = Integer.parseInt(request.getParameter("id"));
            Material material = materialDao.getMaterialById(materialId);
            if (material != null && !material.getCaminhoArquivo().isEmpty()) {
                File file = new File(material.getCaminhoArquivo());
                if (file.exists()) {
                    String mimeType = Files.probeContentType(file.toPath());
                    if (mimeType == null) {
                        mimeType = "application/octet-stream";
                    }
                    response.setContentType(mimeType);
                    response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
                    response.setContentLength((int) file.length());
                    InputStream inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        response.getOutputStream().write(buffer, 0, bytesRead);
                    }
                    inputStream.close();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'materialId' deve ser um número inteiro.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
