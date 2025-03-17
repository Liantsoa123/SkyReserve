<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
    List<Flight> flights = (List<Flight>) request.getAttribute("flights");
    List<City> departureCities = (List<City>) request.getAttribute("departureCities");
    List<City> arrivalCities = (List<City>) request.getAttribute("arrivalCities");
    List<SeatType> seatTypes = (List<SeatType>) request.getAttribute("seatTypes");
    List<ReservationStatus> statuses = (List<ReservationStatus>) request.getAttribute("statuses");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
%>

<!DOCTYPE html>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Mes Réservations</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/messages.css">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js" defer></script>

    <style>
        .reservations-container {
            margin: 20px;
            padding: 20px;
        }

        .reservation-card {
            background: var(--surface-color);
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .reservation-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .reservation-status {
            padding: 5px 10px;
            border-radius: 4px;
            font-weight: bold;
        }

        .status-pending { background-color: #ffd700; }
        .status-confirmed { background-color: #90EE90; }
        .status-cancelled { background-color: #ffcccb; }

        .flight-info {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
        }

        .info-group {
            margin-bottom: 10px;
        }

        .info-label {
            font-weight: bold;
            color: var(--text-muted);
        }

        .btn-cancel {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Mes Réservations</h2>

    <jsp:include page="components/messages.jsp"/>

    <div class="reservations-container">
        <% if (reservations != null && !reservations.isEmpty()) {
            for (int i = 0; i < reservations.size(); i++) {
                Reservation reservation = reservations.get(i);
                Flight flight = flights.get(i);
                City departureCity = departureCities.get(i);
                City arrivalCity = arrivalCities.get(i);
                SeatType seatType = seatTypes.get(i);
                ReservationStatus status = statuses.get(i);
        %>
        <div class="reservation-card">
            <div class="reservation-header">
                <h3>Réservation #<%= reservation.getReservation_id() %></h3>
                <span class="reservation-status status-<%= status.getReservation_name().toLowerCase() %>">
                    <%= status.getReservation_name() %>
                </span>
            </div>

            <div class="flight-info">
                <div class="info-group">
                    <div class="info-label">Date de réservation:</div>
                    <div><%= dateFormat.format(reservation.getReservation_date()) %></div>
                </div>

                <div class="info-group">
                    <div class="info-label">Vol:</div>
                    <div><%= departureCity.getCity_name() %> → <%= arrivalCity.getCity_name() %></div>
                </div>

                <div class="info-group">
                    <div class="info-label">Départ:</div>
                    <div><%= dateFormat.format(flight.getDeparture_date()) %></div>
                </div>

                <div class="info-group">
                    <div class="info-label">Arrivée:</div>
                    <div><%= dateFormat.format(flight.getArrival_date()) %></div>
                </div>

                <div class="info-group">
                    <div class="info-label">Type de siège:</div>
                    <div><%= seatType.getType_name() %></div>
                </div>

                <div class="info-group">
                    <div class="info-label">Nombre de places:</div>
                    <div><%= reservation.getSeats_number() %></div>
                </div>

                <div class="info-group">
                    <div class="info-label">Promotion:</div>
                    <div><%= reservation.isHas_promotion() ? "Oui" : "Non" %></div>
                </div>
            </div>

            <% if (!status.getReservation_name().equals("Annulé")) { %>
            <div style="margin-top: 15px;">
                <button onclick="cancelReservation(<%= reservation.getReservation_id() %>)" class="btn-cancel">
                    <i class="fas fa-times"></i> Annuler la réservation
                </button>
            </div>
            <% } %>
        </div>
        <% }
        } else { %>
        <div style="text-align: center; margin: 40px 0;">
            <p>Vous n'avez aucune réservation pour le moment.</p>
            <a href="./showAllFlights" class="btn-primary" style="display: inline-block; margin-top: 20px;">
                <i class="fas fa-plane"></i> Réserver un vol
            </a>
        </div>
        <% } %>
    </div>
</div>

<script>
    function cancelReservation(reservationId) {
        if (confirm('Êtes-vous sûr de vouloir annuler cette réservation ?')) {
            window.location.href = './cancelReservation?reservationId=' + reservationId;
        }
    }
</script>

</body>
</html>