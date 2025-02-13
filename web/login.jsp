<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Connexion</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/login.css">
</head>
<body>
<div class="login-container">
    <h2>Connexion à SkyReserve</h2>
    <form action="./login" method="post">
        <% if (error != null) { %>
        <div class="error"><%= error %></div>
        <% } %>
        <div class="form-group">
            <label for="name">Nom d'utilisateur:</label>
            <input type="text" id="name" name="name"
                   value="<%= request.getAttribute("name") != null ? request.getAttribute("name") : "" %>" required>
        </div>
        <div class="form-group">
            <label for="password">Mot de passe:</label>
            <input type="password" id="password" name="password"
                   value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>" required>
        </div>
        <button type="submit">Se connecter</button>
    </form>
</div>
</body>
</html>