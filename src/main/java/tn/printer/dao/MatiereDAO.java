package tn.printer.dao;

import tn.printer.models.Document;
import tn.printer.models.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatiereDAO {

	private final Connection connection;

	public MatiereDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Matiere> getAllMatieres() {
	    List<Matiere> matieres = new ArrayList<>();
	    String query = "SELECT * FROM matiere";

	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int idMatiere = resultSet.getInt("idMatiere");
	            String nom = resultSet.getString("nom");
	            List<Document> documentsMatiere = getDocumentsByMatiereId(idMatiere);
	            Matiere matiere = new Matiere(idMatiere, nom, documentsMatiere);

	            matieres.add(matiere);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return matieres;
	}



	private List<Document> getDocumentsByMatiereId(int matiereId) {
		List<Document> documents = new ArrayList<>();
		String query = "SELECT * FROM document WHERE idMatiere = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, matiereId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int idDocument = resultSet.getInt("idDocument");
				String documentNom = resultSet.getString("documentNom");
				Matiere matiere = new Matiere(resultSet.getInt("idMatiere"), null, null);
				String documentUrl = resultSet.getString("documentUrl");
				Document document = new Document(idDocument, documentNom, documentUrl, matiere);
				documents.add(document);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return documents;
	}

// You can add more methods here for specific queries or operations related to
// Matiere
}
