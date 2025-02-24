<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    User currentUser = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Tableau de bord</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js" defer></script>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="dashboard">
    <div class="welcome-section">
        <h1>Bienvenue, <%= currentUser.getName() %> !</h1>
        <p>Que souhaitez-vous faire aujourd'hui ?</p>
    </div>

    <div class="quick-actions">


        <%
            if (currentUser.getRole().equals("admin")) {
        %>
        <a href="./showAllFlights" class="action-card">
            <i class="fas fa-plane"></i>
            <h3>Gestion des Vols</h3>
            <p>Voir, ajouter, modifier ou supprimer des vols</p>
        </a>

        <a href="./showInsertPriceInfo" class="action-card">
            <i class="fas fa-tag"></i>
            <h3>Prix des Vols</h3>
            <p>Gérer les tarifs et les promotions</p>
        </a>

        <a href="./showSettingReservation" class="action-card">
            <i class="fas fa-cog"></i>
            <h3>Paramètres</h3>
            <p>Configurer les règles de réservation</p>
        </a>
        <% } else {%>
        <a href="./showAllFlights" class="action-card">
            <i class="fas fa-plane"></i>
            <h3>Vols</h3>
            <p>Voir les vols disponibles</p>
        </a>
        <a href="./showMyReservations" class="action-card">
            <i class="fas fa-ticket-alt"></i>
            <h3>Mes Réservations</h3>
            <p>Voir et gérer vos réservations</p>
        </a>
        <% } %>
    </div>
</div>

</body>
</html>