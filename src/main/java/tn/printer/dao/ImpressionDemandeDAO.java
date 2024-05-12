package tn.printer.dao;

import tn.printer.models.ImpressionDemande;
import tn.printer.connection.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImpressionDemandeDAO {
    private Connection connection;

    public ImpressionDemandeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ImpressionDemande> getAllImpressionDemandes() {
        List<ImpressionDemande> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demande_impression";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ImpressionDemande demande = new ImpressionDemande();
                demande.setDocument(resultSet.getString("idDocument"));
                demande.setDate(resultSet.getDate("date"));
                demande.setNombreCopies(resultSet.getInt("nombreCopies"));
                // You need to retrieve enseignant and groupe data from the database
                // For simplicity, let's assume you have columns named "enseignant" and "groupe"
                demande.setEnseignant(resultSet.getString("idEnseignant"));
                
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }
}
