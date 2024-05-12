<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="tn.printer.models.ImpressionDemande, tn.printer.dao.ImpressionDemandeDAO"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agent Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Agent Dashboard</h1>
        <table>
            <tr>
                <th>Document</th>
                <th>Date</th>
                <th>Nombre de Copies</th>
                <th>Enseignant</th>
                <th>Groupe</th>
            </tr>
            <% 
                ImpressionDemandeDAO impressionDemandeDAO = new ImpressionDemandeDAO(tn.printer.connection.MysqlConnection.getConnection());
                List<ImpressionDemande> demandes = impressionDemandeDAO.getAllImpressionDemandes();
                for (ImpressionDemande demande : demandes) {
            %>
                <tr>
                    <td><%= demande.getDocument() %></td>
                    <td><%= demande.getDate() %></td>
                    <td><%= demande.getNombreCopies() %></td>
                    <td><%= demande.getEnseignant() %></td>
                    <td><%= demande.getGroupe() %></td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
