<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.City" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model.Flight" %>
<%
    List<City> cities = (List<City>) request.getAttribute("cities");
    HashMap<String , String> error = new HashMap<String , String>();
    if(request.getAttribute("error") != null){
        error = (HashMap<String , String>) request.getAttribute("error");
    }
    Flight flight = null;
    if(request.getAttribute("flight") != null){
        flight = (Flight) request.getAttribute("flight");
    }

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Ajouter un Vol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/insertFlight.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/messages.css">
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2><%=flight!=null?"Modifier":"Ajouter"%> un Nouveau Vol</h2>

    <jsp:include page="components/messages.jsp"/>

    <form action="./insertFlight" method="post">
        <input type="hidden" name="Flight.flight_id" value="<%=flight!=null?flight.getFlight_id():"-1"%>">

        <% if (error.get("departure_city_id")!=null) {%>
            <div class="error-message"> <%=error.get("departure_city_id")%> </div>
        <% } %>
        <div class="form-group">
            <label for="departureCity">Ville de départ:</label>
            <select name="Flight.departure_city_id" id="departureCity">
                <option value="-1">Sélectionner une ville</option>
                <% if (cities != null) {
                    for (City city : cities) {
                        String selected = "";
                        if (flight != null) {
                            selected = (flight.getDeparture_city_id() == city.getCity_id())
                                    ? "selected"
                                    : "";
                        }else {
                            selected = (request.getParameter("Flight.departure_city_id") != null
                                    && Integer.parseInt(request.getParameter("Flight.departure_city_id")) == city.getCity_id())
                                    ? "selected"
                                    : "";
                        }

                %>
                <option value="<%= city.getCity_id() %>" <%= selected %>><%= city.getCity_name() %></option>
                <%
                        }
                    } %>
            </select>
        </div>


        <% if (error.get("arrival_city_id")!=null) {%>
        <div class="error-message"> <%=error.get("arrival_city_id")%> </div>
        <% } %>
        <div class="form-group">
            <label for="arrivalCity">Ville d'arrivée:</label>
            <select name="Flight.arrival_city_id" id="arrivalCity">
                <option value="-1">Sélectionner une ville</option>
                <% if (cities != null) {
                    for (City city : cities) {
                        String selected = "";
                        if (flight != null) {
                            selected = (flight.getArrival_city_id() == city.getCity_id())
                                    ? "selected"
                                    : "";
                        }else {
                        selected = (request.getParameter("Flight.arrival_city_id") != null
                                && Integer.parseInt(request.getParameter("Flight.arrival_city_id")) == city.getCity_id())
                                ? "selected"
                                : "";
                        }
                %>
                <option value="<%= city.getCity_id() %>" <%= selected %>><%= city.getCity_name() %></option>
                <%
                        }
                    } %>
            </select>
        </div>

        <% if (error.get("departure_date")!=null) {%>
        <div class="error-message"> <%=error.get("departure_date")%> </div>
        <% } %>
        <div class="form-group">
            <label for="departureDate">Date et heure de départ:</label>
            <% if (flight != null ) { %>
                <input type="datetime-local" id="departureDate" name="Flight.departure_date" value="<%=flight.getDeparture_date()%>">
            <% } else { %>
            <input type="datetime-local" id="departureDate" name="Flight.departure_date" value="<%=request.getParameter("Flight.departure_date")!=null?request.getParameter("Flight.departure_date"):""  %>" >
            <% } %>
        </div>

        <% if (error.get("arrival_date")!=null) {%>
        <div class="error-message"> <%=error.get("arrival_date")%> </div>
        <% } %>
        <div class="form-group">
            <label for="arrivalDate">Date et heure d'arrivée:</label>
            <% if (flight != null ) { %>
                <input type="datetime-local" id="arrivalDate" name="Flight.arrival_date" value="<%=flight.getArrival_date()%>">
            <% } else { %>
            <input type="datetime-local" id="arrivalDate" name="Flight.arrival_date" value="<%=request.getParameter("Flight.departure_date")!=null?request.getParameter("Flight.arrival_date"):""  %>">
            <% } %>
        </div>

        <div class="form-actions">
            <button type="button" class="btn-secondary" onclick="window.location.href='./showAllFlights'">
                <i class="fas fa-times"></i> Annuler
            </button>
            <button type="submit" class="btn-primary">
                <i class="fas <%=flight!=null?"fa-edit":"fa-plus"%>"></i><%=flight!=null?"Modifier":"Ajouter"%> le vol
            </button>
        </div>
    </form>
</div>
</body>
</html>