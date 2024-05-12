package tn.printer.controller;

import tn.printer.connection.MysqlConnection;
import tn.printer.dao.UtilisateurDAO;
import tn.printer.models.Utilisateur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
    private UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        utilisateurDAO = new UtilisateurDAO(MysqlConnection.getConnection());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Utilisateur utilisateur = utilisateurDAO.getUtilisateurByEmail(email);

        if (utilisateur != null && utilisateur.getMotDePasse().equals(password)) {
            int role = utilisateur.getRole();
            switch (role) {
                case 1:
                    request.setAttribute("utilisateur", utilisateur);
                    request.getRequestDispatcher("/WEB-INF/jsp/admin/dashboard.jsp").forward(request, response);
                    break;
                case 2:
                    request.setAttribute("utilisateur", utilisateur);
                    request.getRequestDispatcher("/WEB-INF/jsp/enseignant/dashboard.jsp").forward(request, response);
                    break;
                case 3:
                    request.setAttribute("utilisateur", utilisateur);
                    request.getRequestDispatcher("/WEB-INF/jsp/agent/dashboard.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("utilisateur", utilisateur);
                    request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("utilisateur", utilisateur);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
