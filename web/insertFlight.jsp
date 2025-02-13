<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.City" %>
<%
    List<City> cities = (List<City>) request.getAttribute("cities");

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Ajouter un Vol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/insertFlight.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/navbar.css">
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Ajouter un Nouveau Vol</h2>

    <form action="./insertFlight" method="post">
        <input type="hidden" name="Flight.flight_id" value="1">

        <% if (request.getParameter("departure_city")!=null) {%>
            <div class="error-message"> <%=request.getParameter("departure_city")%> </div>
        <% } %>
        <div class="form-group">
            <label for="departureCity">Ville de départ:</label>
            <select name="Flight.departure_city" id="departureCity">
                <option value="">Sélectionner une ville</option>
                <% if (cities != null) for (City city : cities) { %>
                <option value="<%= city.getCity_id() %>"><%= city.getCity_name() %>
                </option>
                <% } %>
            </select>
        </div>

        <% if (request.getParameter("arrival_city")!=null) {%>
        <div class="error"> <%=request.getParameter("arrival_city")%> </div>
        <% } %>
        <div class="form-group">
            <label for="arrivalCity">Ville d'arrivée:</label>
            <select name="Flight.arrival_city" id="arrivalCity">
                <option value="">Sélectionner une ville</option>
                <% if (cities != null) for (City city : cities) { %>
                <option value="<%= city.getCity_id() %>"><%= city.getCity_name() %>
                </option>
                <% } %>
            </select>
        </div>

        <% if (request.getParameter("departure_date")!=null) {%>
        <div class="error"> <%=request.getParameter("departure_date")%> </div>
        <% } %>
        <div class="form-group">
            <label for="departureDate">Date et heure de départ:</label>
            <input type="datetime-local" id="departureDate" name="Flight.departure_date">
        </div>

        <% if (request.getParameter("arrival_date")!=null) {%>
        <div class="error"> <%=request.getParameter("arrival_date")%> </div>
        <% } %>
        <div class="form-group">
            <label for="arrivalDate">Date et heure d'arrivée:</label>
            <input type="datetime-local" id="arrivalDate" name="Flight.arrival_date">
        </div>

        <div class="form-actions">
            <button type="button" class="btn-secondary" onclick="window.location.href='#'">
                <i class="fas fa-times"></i> Annuler
            </button>
            <button type="submit" class="btn-primary">
                <i class="fas fa-plus"></i> Ajouter le vol
            </button>
        </div>
    </form>
</div>
</body>
</html>