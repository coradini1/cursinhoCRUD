package controller;

import DAO.MaterialDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
        int materialId = Integer.parseInt(request.getParameter("materialId"));
        try {
            InputStream pdfStream = materialDao.getPDF(materialId);
            if (pdfStream != null) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment; filename=\"material.pdf\"");

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = pdfStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
                pdfStream.close();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
