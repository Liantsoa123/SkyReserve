<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Paramètres de Réservation</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/insertFlight.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/messages.css">
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Paramètres de Réservation</h2>

    <jsp:include page="components/messages.jsp"/>

    <form action="./insertSettingReservation" method="post">
        <input type="hidden" name="SettingReservation.setting_id" value="1">
        <div class="form-group">
            <label for="reservation">Nombre d'heures avant le vol pour la réservation:</label>
            <input type="number"
                   id="reservation"
                   name="SettingReservation.reservation"
                   value="${SettingReservation.reservation}">
        </div>

        <div class="form-group">
            <label for="cancelation">Critère d'annulation (Nombre d'heures avant le vol):</label>
            <input type="number"
                   id="cancelation"
                   name="SettingReservation.cancelation"
                   step="0.1"
                   value="${SettingReservation.cancelation}">
        </div>

        <div class="form-actions">
            <button type="button" class="btn-secondary" onclick="window.location.href='./dashboard'">
                <i class="fas fa-times"></i> Annuler
            </button>
            <button type="submit" class="btn-primary">
                <i class="fas fa-save"></i> Enregistrer
            </button>
        </div>
    </form>
</div>

</body>
</html>