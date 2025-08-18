<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%
    Reservation reservation = (Reservation) request.getAttribute("reservation");
    Flight flight = (Flight) request.getAttribute("flight");
    City departureCity = (City) request.getAttribute("departureCity");
    City arrivalCity = (City) request.getAttribute("arrivalCity");
    SeatType seatType = (SeatType) request.getAttribute("seatType");
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DecimalFormat priceFormat = new DecimalFormat("#,##0.00");
%>

<!DOCTYPE html>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Paiement</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/messages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/insertFlight.css">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js" defer></script>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Paiement de la Réservation #<%= reservation.getReservation_id() %></h2>

    <jsp:include page="components/messages.jsp"/>

    <div class="flight-details" style="margin-bottom: 2rem;">
        <h3>Détails de la Réservation</h3>
        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
            <div>
                <p><strong>Vol:</strong> <%= departureCity.getCity_name() %> → <%= arrivalCity.getCity_name() %></p>
                <p><strong>Date de départ:</strong> <%= dateFormat.format(flight.getDeparture_date()) %></p>
                <p><strong>Date d'arrivée:</strong> <%= dateFormat.format(flight.getArrival_date()) %></p>
                <p><strong>Type de siège:</strong> <%= seatType.getType_name() %></p>
            </div>
            <div>
                <p><strong>Nombre de places adultes:</strong> <%= reservation.getSeats_number() %></p>
                <p><strong>Nombre de places enfants:</strong> <%= reservation.getSeats_number_children() %></p>
                <p><strong>Promotion:</strong> <%= reservation.isHas_promotion() ? "Oui" : "Non" %></p>
            </div>
        </div>
    </div>

    <form action="./processPayment" method="post" style="max-width: 500px;">
        <input type="hidden" name="reservationId" value="<%= reservation.getReservation_id() %>">
        
        <h3>Informations de Paiement</h3>
        
        <div class="form-group">
            <label for="paymentDate">Date de paiement:</label>
            <input type="date" id="paymentDate" name="paymentDate" required>
        </div>
        
        <div class="form-actions">
            <button type="button" class="btn-secondary" onclick="window.location.href='./showMyReservations'">
                <i class="fas fa-arrow-left"></i> Retour
            </button>
            <button type="submit" class="btn-primary">
                <i class="fas fa-credit-card"></i> Payer
            </button>
        </div>
    </form>
</div>

<script>
    // Set today's date as default
    document.getElementById('paymentDate').value = new Date().toISOString().split('T')[0];
</script>

</body>
</html>
            <button type="button" class="btn-secondary" onclick="window.location.href='./showMyReservations'">
                <i class="fas fa-arrow-left"></i> Retour
            </button>
            <button type="submit" class="btn-primary">
                <i class="fas fa-credit-card"></i> Payer
            </button>
        </div>
    </form>
</div>

<script>
    // Format card number
    document.getElementById('cardNumber').addEventListener('input', function(e) {
        let value = e.target.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        let formattedValue = value.match(/.{1,4}/g)?.join(' ') || value;
        e.target.value = formattedValue;
    });
    
    // Format expiry date
    document.getElementById('expiryDate').addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length >= 2) {
            value = value.substring(0,2) + '/' + value.substring(2,4);
        }
        e.target.value = value;
    });
    
    // CVV numbers only
    document.getElementById('cvv').addEventListener('input', function(e) {
        e.target.value = e.target.value.replace(/[^0-9]/g, '');
    });
</script>

</body>
</html>
