package tn.printer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.printer.connection.MysqlConnection;
import tn.printer.models.Utilisateur;

public class UtilisateurDAO {
    private static Connection connection;

    public UtilisateurDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new utilisateur
    public boolean addUtilisateur(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateur (nom_utilisateur, email, mot_de_passe, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getNomUtilisateur());
            statement.setString(2, utilisateur.getEmail());
            statement.setString(3, utilisateur.getMotDePasse());
            statement.setInt(4, utilisateur.getRole());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve a utilisateur by ID
    public static Utilisateur getUtilisateurById(int id) {
        String query = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Utilisateur(
                    resultSet.getInt("idUtilisateur"),
                    resultSet.getString("nomUtilisateur"),
                    resultSet.getString("email"),
                    resultSet.getString("motDePasse"),
                    resultSet.getInt("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to update an existing utilisateur
    public boolean updateUtilisateur(Utilisateur utilisateur) {
        String query = "UPDATE utilisateur SET nomUtilisateur = ?, email = ?, motDePasse = ?, role = ? WHERE idUtilisateur = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getNomUtilisateur());
            statement.setString(2, utilisateur.getEmail());
            statement.setString(3, utilisateur.getMotDePasse());
            statement.setInt(4, utilisateur.getRole());
            statement.setInt(5, utilisateur.getIdUtilisateur());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a utilisateur
    public boolean deleteUtilisateur(int id) {
        String query = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur(
                                 resultSet.getInt("idUtilisateur"),
                                 resultSet.getString("nomUtilisateur"),
                                 resultSet.getString("email"),
                                 resultSet.getString("motDePasse"),
                                 resultSet.getInt("role")                    
                );
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    
    public Utilisateur getUtilisateurByEmail(String email) {
        Utilisateur utilisateur = null;
        String query = "SELECT * FROM utilisateur WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(resultSet.getInt("idUtilisateur"));
                utilisateur.setNomUtilisateur(resultSet.getString("nomUtilisateur"));
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateur.setMotDePasse(resultSet.getString("motDePasse"));
                utilisateur.setRole(resultSet.getInt("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

}
