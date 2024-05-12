package tn.printer.dao;

import tn.printer.models.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupeDAO {

    private static Connection connection = null;

    public GroupeDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean save(Group group) {
        String sql = "INSERT INTO groups (groupeId, groupeName) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, group.getGroupeId());
            statement.setString(2, group.getGroupeName());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Group getById(int id) {
        String sql = "SELECT * FROM groups WHERE groupeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String groupName = resultSet.getString("groupeName");
                return new Group(id, groupName, null); // Assuming matieresGroupe is not needed for getById
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        String query = "SELECT * FROM groupe";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idGroupe = resultSet.getInt("idGroupe");
                String nomGroupe = resultSet.getString("nomGroupe");
                Group group = new Group(idGroupe, nomGroupe, null); // Assuming matieresGroupe is not needed for getAll
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public boolean update(Group group) {
        String sql = "UPDATE groups SET groupeName = ? WHERE groupeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, group.getGroupeName());
            statement.setInt(2, group.getGroupeId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM groups WHERE groupeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
