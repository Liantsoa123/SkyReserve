<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Flight" %>
<%@ page import="model.SeatType" %>
<%@ page import="dto.SeatAvailabilityDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.City" %>
<%
    Flight flight = (Flight) request.getAttribute("flight");
    List<SeatType> seatTypes = (List<SeatType>) request.getAttribute("seatTypes");
    List<SeatAvailabilityDTO> seatAvailability = (List<SeatAvailabilityDTO>) request.getAttribute("seatAvailability");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    City departure_city = (City) request.getAttribute("departure_city");
    City arrival_city = (City) request.getAttribute("arrival_city");
%>

<!DOCTYPE html>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Réserver un vol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/insertFlight.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reservation.css">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js" defer></script>

</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Réserver un Vol</h2>

    <div class="flight-details">
        <h3>Détails du Vol</h3>
        <p><strong>Ville de depart:</strong> <%=departure_city.getCity_name()%></p>
        <p><strong>Ville d'arrivée:</strong> <%=arrival_city.getCity_name()%></p>
        <p><strong>Date de départ:</strong> <%= dateFormat.format(flight.getDeparture_date()) %>
        </p>
        <p><strong>Date d'arrivée:</strong> <%= dateFormat.format(flight.getArrival_date()) %>
        </p>
    </div>

    <h3>Disponibilité des Sièges</h3>
    <div class="availability-grid">
        <% for (SeatAvailabilityDTO availability : seatAvailability) { %>
        <div class="availability-card">
            <h4><%= availability.getSeatType().getType_name() %>
            </h4>
            <div class="seats-info">
                <span>Places disponibles: <%= availability.getAvailableSeats() %></span>
                <span>Total: <%= availability.getTotalSeats() %></span>
            </div>
            <div class="seats-info" >
                <span>Prix:</span>
                <span><%=availability.getUnitPrice()%></span>
            </div>
            <div class="seats-info" >
                <span>Promotion: <%=availability.getDiscountPercentage()%></span>
                <span>nombres: <%=availability.getNumberPromotions()%></span>
            </div>
        </div>
        <% } %>
    </div>

    <h3>Formulaire de Réservation</h3>
    <form action="./insertReservation" method="post">
        <input type="hidden" name="Reservation.flight_id" value="<%= flight.getFlight_id() %>">

        <div class="form-group">
            <label for="seatType">Type de siège:</label>
            <select name="Reservation.seat_type_id" id="seatType" required>
                <option value="">Sélectionner un type de siège</option>
                <% for (SeatType seatType : seatTypes) { %>
                <option value="<%= seatType.getSeat_type_id() %>">
                    <%= seatType.getType_name() %>
                </option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label for="seatsNumber">Nombre de places:</label>
            <input type="number"
                   id="seatsNumber"
                   name="Reservation.seats_number"
                   min="1"
                   required>
        </div>

        <div class="form-actions">
            <button type="button" class="btn-secondary" onclick="window.location.href='./showAllFlights'">
                <i class="fas fa-times"></i> Annuler
            </button>
            <button type="submit" class="btn-primary">
                <i class="fas fa-plane"></i> Réserver
            </button>
        </div>
    </form>
</div>

<script>
    // Validation du nombre de places en fonction de la disponibilité
    document.getElementById('seatType').addEventListener('change', function () {
        const seatTypeId = this.value;
        const seatsInput = document.getElementById('seatsNumber');

        <% for (SeatAvailabilityDTO availability : seatAvailability) { %>
        if (seatTypeId == <%= availability.getSeatType().getSeat_type_id() %>) {
            seatsInput.max = <%= availability.getAvailableSeats() %>;
            seatsInput.title = "Maximum <%= availability.getAvailableSeats() %> places disponibles";
        }
        <% } %>
    });
</script>

</body>
</html>