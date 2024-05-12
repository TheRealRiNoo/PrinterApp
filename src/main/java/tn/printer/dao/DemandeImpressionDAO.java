package tn.printer.dao;

import tn.printer.models.DemandeImpression;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DemandeImpressionDAO {

    private Connection connection;

    public DemandeImpressionDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean save(DemandeImpression demandeImpression) {
        String sql = "INSERT INTO demande_impression (idDemande, document, date, nombreCopies, idEnseignant, idAgentImpression) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, demandeImpression.getIdDemande());
            statement.setString(2, demandeImpression.getDocument());
            statement.setDate(3, new java.sql.Date(demandeImpression.getDateDemande().getTime()));
            statement.setInt(4, demandeImpression.getNombreCopies());
            statement.setInt(5, demandeImpression.getEnseignant().getIdUtilisateur());
            statement.setInt(6, demandeImpression.getGroupe().getGroupeId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
