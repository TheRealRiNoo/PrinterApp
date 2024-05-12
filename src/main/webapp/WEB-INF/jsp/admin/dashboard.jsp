<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="tn.printer.models.Matiere,tn.printer.models.Group, tn.printer.models.Document, tn.printer.dao.MatiereDAO, tn.printer.dao.DocumentDAO, tn.printer.dao.GroupeDAO"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enseignant Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
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

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        select,
        input[type="date"],
        input[type="number"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, admiiin!</h1>
        <form action="submitDemandeImpression" method="post">
            <!-- Hidden input field to store user ID -->
			<input type="hidden" name="enseignantID" value="1">
            
            <label for="matiere">Matière:</label>
            <select name="matiereId" id="matiere">
                <option value="">Select a Matière</option>
                <% 
                    MatiereDAO matiereDAO = new MatiereDAO(tn.printer.connection.MysqlConnection.getConnection());
                    List<Matiere> matieres = matiereDAO.getAllMatieres();
                    for (Matiere matiere : matieres) {
                %>
                    <option value="${matiere.getIdMatiere()}"><%= matiere.getNom() %></option>
                <% } %>
            </select>

            <label for="document">Document:</label>
            <select name="documentId" id="document">
                <option value="">Select a Document</option>
                <% 
                    DocumentDAO documentDAO = new DocumentDAO(tn.printer.connection.MysqlConnection.getConnection());
                    List<Document> documents = documentDAO.getAllDocuments();
                    for (Document document : documents) {
                %>
                    <option value="${document.getIdDocument()}"><%= document.getDocumentNom() %></option>
                <% } %>
            </select>
            
            <!-- You can add functionality to upload a new document here -->
            
			<label for="groupe">Groupe:</label>
			<select name="groupeId" id="groupe">
			    <option value="">Choisir un Groupe</option>
			    <% 
			        GroupeDAO groupeDAO = new GroupeDAO(tn.printer.connection.MysqlConnection.getConnection());
			        List<Group> groupes = groupeDAO.getAllGroups();
			        for (Group groupe : groupes) {
			    %>
			        <option value="<%= groupe.getGroupeId() %>"><%= groupe.getGroupeName() %></option>
			    <% } %>
			</select>

            <label for="date">Date:</label>
            <input type="date" name="date" id="date">

            <label for="nombreCopies">Nombre de Copies:</label>
            <input type="number" name="nombreCopies" id="nombreCopies">

            <input type="submit" value="Valider">
        </form>
    </div>
</body>
</html>
