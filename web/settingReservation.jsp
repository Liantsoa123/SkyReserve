<%@ page import="java.util.HashMap" %>
<%@ page import="model.SettingReservation" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    HashMap<String, String> error = new HashMap<String, String>();
    if (request.getAttribute("error") != null) {
        error = (HashMap<String, String>) request.getAttribute("error");
    }
    SettingReservation settingReservation = null;
    if (request.getAttribute("settingReservation") != null) {
        settingReservation = (SettingReservation) request.getAttribute("settingReservation");
    }
%>

<!DOCTYPE html>
<html data-theme="light" >
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Paramètres de Réservation</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/insertFlight.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/messages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js" defer></script>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Paramètres de Réservation</h2>

    <jsp:include page="components/messages.jsp"/>

    <form action="./insertSettingReservation" method="post">
        <input type="hidden" name="url" value="/showSettingReservation" >

        <input type="hidden" name="SettingReservation.setting_reservation_id"
            <%
                        if (settingReservation != null) { %>
               value="<%=settingReservation.getSetting_reservation_id()%>"
            <%
                    } else { %>
               value="<%=request.getParameter("SettingReservation.setting_reservation_id")!=null?request.getParameter("SettingReservation.setting_reservation_id"):"-1"%>"
            <% } %> >

        <% if (error.get("reservation") != null) {%>
        <div class="error-message"><%=error.get("reservation")%>
        </div>
        <% } %>
        <div class="form-group">
            <label for="reservation">Nombre d'heures avant le vol pour la réservation:</label>
            <input type="text"
                   id="reservation"
                   step="0.1"
                   name="SettingReservation.reservation"
                <%
                        if (request.getParameter("SettingReservation.reservation")!= null) { %>
                   value="<%=request.getParameter("SettingReservation.reservation")%>"
                <%
                    } else { %>
                   value="<%=settingReservation!=null?settingReservation.getReservation():""%>"
                <% } %> >
        </div>

        <% if (error.get("cancelation") != null) {%>
        <div class="error-message"><%=error.get("cancelation")%>
        </div>
        <% } %>
        <div class="form-group">
            <label for="cancelation">Critère d'annulation (Nombre d'heures avant le vol):</label>
            <input type="text"
                   id="cancelation"
                   name="SettingReservation.cancelation"
                   step="0.1"
                <%
                        if (request.getParameter("SettingReservation.cancelation") != null) { %>
                   value="<%=request.getParameter("SettingReservation.cancelation")%>"
                <%
                    } else { %>
                   value="<%=settingReservation!=null?settingReservation.getCancelation():""%>"
                <% } %> >
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