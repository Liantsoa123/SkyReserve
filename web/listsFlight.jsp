<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.City" %>
<%@ page import="model.Flight" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.Plane" %>
<%@ page import="model.User" %>
<%
    List<Flight> flights = (List<Flight>) request.getAttribute("flights");
    List<City> cities = (List<City>) request.getAttribute("cities");
    List<Plane> planes = (List<Plane>) request.getAttribute("planes");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    User currentUser = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Recherche de Vols</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/searchFlight.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js" defer></script>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Recherche de Vols</h2>

    <% if (currentUser.getRole().equals("admin")) { %>
    <div style="margin-bottom: 1rem; text-align: right;">
        <a href="./showInsertFlight" class="btn-primary"
           style="text-decoration: none; display: inline-flex; align-items: center; gap: 0.5rem;">
            <i class="fas fa-plus"></i>
            Ajouter un vol
        </a>
    </div>
    <% } %>

    <div class="search-form">
        <form action="./searchFlights" method="POST">
            <div class="form-row">
                <div class="form-group">
                    <label for="departureCity">Ville de départ:</label>
                    <select name="departureCityId" id="departureCity">
                        <option value="-1">Toutes les villes</option>
                        <% if (cities != null) {
                            String selectedDepartureCityId = request.getParameter("departureCityId");
                            for (City city : cities) { %>
                        <option value="<%= city.getCity_id() %>" <%= selectedDepartureCityId != null && selectedDepartureCityId.equals(String.valueOf(city.getCity_id())) ? "selected" : "" %>><%= city.getCity_name() %>
                        </option>
                        <% }
                        } %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="arrivalCity">Ville d'arrivée:</label>
                    <select name="arrivalCityId" id="arrivalCity">
                        <option value="-1">Toutes les villes</option>
                        <% if (cities != null) {
                            String selectedArrivalCityId = request.getParameter("arrivalCityId");
                            for (City city : cities) { %>
                        <option value="<%= city.getCity_id() %>" <%= selectedArrivalCityId != null && selectedArrivalCityId.equals(String.valueOf(city.getCity_id())) ? "selected" : "" %>><%= city.getCity_name() %>
                        </option>
                        <% }
                        } %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="departureDate">Date de départ:</label>
                    <input type="date" id="departureDate" name="departureDate"
                           value="<%=request.getParameter("departureDate")!=null?request.getParameter("departureDate"):""%>">
                </div>

                <button type="submit" class="btn-primary">
                    <i class="fas fa-search"></i> Rechercher
                </button>
            </div>
        </form>
    </div>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Avion</th>
                <th>Ville de départ</th>
                <th>Ville d'arrivée</th>
                <th>Date de départ</th>
                <th>Date d'arrivée</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% if (flights != null && !flights.isEmpty()) {
                for (Flight flight : flights) {
                    City departureCity = null;
                    City arrivalCity = null;
                    for (City city : cities) {
                        if (city.getCity_id() == flight.getDeparture_city_id()) {
                            departureCity = city;
                        }
                        if (city.getCity_id() == flight.getArrival_city_id()) {
                            arrivalCity = city;
                        }
                    }
                    Plane plane = null;
                    for (Plane p : planes) {
                        if (p.getPlane_id() == flight.getPlane_id()) {
                            plane = p;
                        }
                    }
            %>
            <tr>
                <td><%= plane != null ? plane.getModel() : "N/A" %>
                </td>
                <td><%= departureCity != null ? departureCity.getCity_name() : "N/A" %>
                </td>
                <td><%= arrivalCity != null ? arrivalCity.getCity_name() : "N/A" %>
                </td>
                <td><%= dateFormat.format(flight.getDeparture_date()) %>
                </td>
                <td><%= dateFormat.format(flight.getArrival_date()) %>
                </td>
                <td class="actions">
                    <% if (currentUser.getRole().equals("admin")) { %>
                    <button onclick="editFlight(<%= flight.getFlight_id() %>)" class="btn-edit" title="Modifier">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button onclick="deleteFlight(<%= flight.getFlight_id() %>)" class="btn-delete" title="Supprimer">
                        <i class="fas fa-trash"></i>
                    </button>
                    <% } else { %>
                    <button onclick="reserveFlight(<%= flight.getFlight_id() %>)" class="btn-reserve" title="Réserver">
                        <i class="fas fa-plane-departure"></i>
                    </button>
                    <% } %>
                </td>
            </tr>
            <% }
            } else { %>
            <tr>
                <td colspan="5" class="no-data">Aucun vol trouvé</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<script>
    function editFlight(flightId) {
        window.location.href = './showUpdateFlight?flightId=' + flightId;
    }

    function deleteFlight(flightId) {
        if (confirm('Êtes-vous sûr de vouloir supprimer ce vol ?')) {
            window.location.href = './deleteFlight?flightId=' + flightId;
        }
    }

    function reserveFlight(flightId) {
        window.location.href = './reserve?flightId=' + flightId;
    }
</script>

</body>
</html>