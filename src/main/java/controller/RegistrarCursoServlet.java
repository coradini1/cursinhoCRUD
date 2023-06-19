package controller;

import DAO.CursoDao;
import DAO.MaterialDao;
import model.Curso;
import model.TipoUsuario;
import model.Material;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/registrarcurso")
public class RegistrarCursoServlet extends HttpServlet {
    private CursoDao cursoDao;
    private MaterialDao materialDao;

    @Override
    public void init() throws ServletException {
        super.init();
        cursoDao = new CursoDao();
        materialDao = new MaterialDao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Material> materiais = materialDao.getMateriais();
            System.out.println("Materiais: " + materiais);
            request.setAttribute("materiais", materiais);
            request.getRequestDispatcher("/registrar-curso.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TipoUsuario tipoUsuario = (TipoUsuario) request.getSession().getAttribute("tipoUsuario");
        if (tipoUsuario.equals(TipoUsuario.aluno)) {
            response.sendRedirect("erro.jsp");
            return;
        }

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        HttpSession session = request.getSession(false);
        int professorId = (int) session.getAttribute("userId");

        Curso curso = new Curso(nome, descricao, professorId);
        try {
            cursoDao.createCurso(curso);

            // Obtenha os IDs dos materiais selecionados do formulário
            String[] materiaisSelecionados = request.getParameterValues("materiaisSelecionados");
            if (materiaisSelecionados != null) {
                // Converta os IDs dos materiais para uma lista de inteiros
                List<String> materiaisIds = Arrays.asList(materiaisSelecionados);
                for (String materialId : materiaisIds) {
                    // Obtenha o material pelo ID
                    Material material = materialDao.getMaterialById(Integer.parseInt(materialId));
                    // Vincule o material ao curso
                    cursoDao.vincularMaterial(curso.getId(), material.getId());
                }
            }

            response.sendRedirect(request.getContextPath() + "/listarcursos");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
