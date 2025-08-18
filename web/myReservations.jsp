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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/messages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/myReservation.css">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js" defer></script>

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
                    <div class="info-label">Nombre de places adultes:</div>
                    <div><%= reservation.getSeats_number() %></div>
                </div>

                <div class="info-group">
                    <div class="info-label">Nombre de places enfants:</div>
                    <div><%= reservation.getSeats_number_children() %></div>
                </div>

                <div class="info-group">
                    <div class="info-label">Promotion:</div>
                    <div><%= reservation.isHas_promotion() ? "Oui" : "Non" %></div>
                </div>
                
                <div class="info-group">
                    <div class="info-label">Date de paiement:</div>
                    <div><%= reservation.getPayment_date() != null ? dateFormat.format(reservation.getPayment_date()) : "Non payé" %></div>
                </div>
            </div>

            <% if (!status.getReservation_name().equals("Annulé")) { %>
            <div style="margin-top: 15px; display: flex; gap: 10px;">
                <% if (reservation.getPayment_date() == null) { %>
                <button onclick="cancelReservation(<%= reservation.getReservation_id() %>)" class="btn-cancel">
                    <i class="fas fa-times"></i> Annuler la réservation
                </button>
                <button onclick="payReservation(<%= reservation.getReservation_id() %>)" class="btn-primary">
                    <i class="fas fa-credit-card"></i> Payer
                </button>
                <% } else { %>
                <span style="color: #22c55e; font-weight: bold;">
                    <i class="fas fa-check-circle"></i> Réservation payée
                </span>
                <% } %>
                <a href="./downloadReservationPdf?reservationId=<%= reservation.getReservation_id() %>" class="btn-download" target="_blank">
                    <i class="fas fa-file-pdf"></i> Télécharger PDF
                </a>
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
    
    function payReservation(reservationId) {
        window.location.href = './showPaymentPage?reservationId=' + reservationId;
    }
</script>

</body>
</html>