package tn.printer.dao;

import tn.printer.models.Document;
import tn.printer.models.Matiere;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {

    private final Connection connection;

   
   
    public DocumentDAO(Connection connection) {
        this.connection = connection;
    }
    

    public List<Document> getAllDocuments() {
        List<Document> documentsList = new ArrayList<>();

        String query = "SELECT * FROM document";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idDocument = resultSet.getInt("idDocument");
                String documentNom = resultSet.getString("documentNom");
                String documentUrl = resultSet.getString("documentUrl");
                int matiereId = resultSet.getInt("idMatiere"); // Assuming idMatiere is the foreign key for Matiere

                // Assuming you have a method to retrieve Matiere object by id from MatiereDAO
                Matiere matiere = getMatiereById(matiereId);

                Document document = new Document(idDocument, documentNom, documentUrl, matiere);
                documentsList.add(document);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return documentsList;
    }

 
    private Matiere getMatiereById(int matiereId) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Document> getDocumentsByMatiereId(int matiereId) {
        List<Document> documents = new ArrayList<>();
        String query = "SELECT d.idDocument, d.documentNom, d.documentUrl, m.idMatiere, m.nom AS matiereNom " +
                       "FROM document d " +
                       "JOIN matiere m ON d.idMatiere = m.idMatiere " +
                       "WHERE d.idMatiere = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, matiereId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idDocument = resultSet.getInt("idDocument");
                String documentNom = resultSet.getString("documentNom");
                String documentUrl = resultSet.getString("documentUrl");
                int idMatiere = resultSet.getInt("idMatiere");
                String matiereNom = resultSet.getString("matiereNom");
                // Create Matiere object
                Matiere matiere = new Matiere(idMatiere, matiereNom);
                // Create Document object
                Document document = new Document(idDocument, documentNom, documentUrl, matiere);
                documents.add(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    // You can add other methods here as needed, such as methods to insert, update, or delete documents
}
