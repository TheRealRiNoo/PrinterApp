package tn.printer.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tn.printer.connection.MysqlConnection;
import tn.printer.dao.DemandeImpressionDAO;
import tn.printer.dao.UtilisateurDAO;
import tn.printer.dao.DocumentDAO;
import tn.printer.dao.GroupeDAO;
import tn.printer.dao.MatiereDAO;
import tn.printer.models.DemandeImpression;
import tn.printer.models.Document;
import tn.printer.models.Utilisateur;
import tn.printer.models.Group;
import tn.printer.models.Matiere;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

//@WebServlet(name = "DemandeImpressionServlet", urlPatterns = "/submitDemandeImpression")
public class DemandeImpressionController extends HttpServlet {
    private DemandeImpressionDAO demandeImpressionDAO;
    private DocumentDAO documentDAO;
    private MatiereDAO matiereDAO;
    private GroupeDAO groupeDAO;
    private UtilisateurDAO utilisateurDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = null;
        // Get a connection to the database
        connection = MysqlConnection.getConnection();

        // Initialize DAO objects with the connection
        demandeImpressionDAO = new DemandeImpressionDAO(connection);
        documentDAO = new DocumentDAO(connection);
        matiereDAO = new MatiereDAO(connection);
        groupeDAO = new GroupeDAO(connection);
        utilisateurDAO = new UtilisateurDAO(connection);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve request parameters
        String document = request.getParameter("document");
        int nombreCopies = Integer.parseInt(request.getParameter("nombreCopies"));
        
        // Retrieve enseignant ID from request parameters
        String enseignantIdString = request.getParameter("enseignantID");
        if (enseignantIdString == null || enseignantIdString.isEmpty()) {
            // Handle case where enseignant ID is not provided
            response.getWriter().write("Erreur: ID de l'enseignant non spécifié.");
            return;
        }

        // Parse enseignant ID to integer
        int enseignantID;
        try {
            enseignantID = Integer.parseInt(enseignantIdString);
        } catch (NumberFormatException e) {
            // Handle case where enseignant ID is not a valid integer
            response.getWriter().write("Erreur: ID de l'enseignant non valide.");
            return;
        }

        // Retrieve group ID from request parameters
        String groupeIdString = request.getParameter("groupeId");
        if (groupeIdString == null || groupeIdString.isEmpty()) {
            // Handle case where group ID is not provided
            response.getWriter().write("Erreur: Groupe non sélectionné.");
            return;
        }

        // Parse group ID to integer
        int groupId;
        try {
            groupId = Integer.parseInt(groupeIdString);
        } catch (NumberFormatException e) {
            // Handle case where group ID is not a valid integer
            response.getWriter().write("Erreur: ID de groupe non valide.");
            return;
        }

        Date date = new Date(); // Or parse from string if available in the request

        // Retrieve utilisateur object based on ID
        Utilisateur utilisateur = UtilisateurDAO.getUtilisateurById(enseignantID);

        // Retrieve group object based on ID
        Group groupe = GroupeDAO.getById(groupId);

        // Check if utilisateur and group are not null
        if (utilisateur != null && groupe != null) {
            // Submit the printing request
            boolean success = submitDemandeImpression(document, date, nombreCopies, utilisateur, groupe);

            // Respond to the client
            if (success) {
                response.getWriter().write("Demande d'impression soumise avec succès.");
            } else {
                response.getWriter().write("Échec de la soumission de la demande d'impression.");
            }
        } else {
            // Handle the case where utilisateur or group is null
            response.getWriter().write("Erreur: Enseignant ou Groupe non défini.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all matieres
        List<Matiere> matieresList = matiereDAO.getAllMatieres();

        // Default matiereId to display all documents if none is selected
        int matiereId = -1;

        // Check if a matiereId is specified in the request parameter
        String matiereIdParam = request.getParameter("matiereId");
        if (matiereIdParam != null && !matiereIdParam.isEmpty()) {
            matiereId = Integer.parseInt(matiereIdParam);
        }

        // Retrieve documents based on matiereId
        List<Document> documentsList = documentDAO.getDocumentsByMatiereId(matiereId);

        // Set request attributes to pass data to JSP page
        request.setAttribute("documentsList", documentsList);
        request.setAttribute("matieresList", matieresList);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/enseignant/dashboard.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            // Handle the case where the dispatcher is null
            throw new ServletException("RequestDispatcher is null");
        }
    }

    private boolean submitDemandeImpression(String document, Date date, int nombreCopies, Utilisateur utilisateur, Group groupe) {
        DemandeImpression demandeImpression = new DemandeImpression(document, date, nombreCopies, utilisateur, groupe);
        return demandeImpressionDAO.save(demandeImpression);
    }
}
