package tn.printer.services;

import tn.printer.dao.DemandeImpressionDAO;
import tn.printer.models.DemandeImpression;

import java.sql.Connection;
import java.sql.SQLException;

public class DemandeImpressionService {

    private final DemandeImpressionDAO demandeImpressionDAO;

    public DemandeImpressionService(Connection connection) {
        this.demandeImpressionDAO = new DemandeImpressionDAO(connection);
    }

    public void createDemandeImpression(DemandeImpression demandeImpression) throws SQLException {
        demandeImpressionDAO.save(demandeImpression);
    }
}