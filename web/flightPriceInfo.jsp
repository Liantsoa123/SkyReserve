<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%
    Flight flight = (Flight) request.getAttribute("flight");
    List<PriceInfo> priceInfos = (List<PriceInfo>) request.getAttribute("priceInfos");
    List<SeatType> seatTypes = (List<SeatType>) request.getAttribute("seatTypes");
    int id = request.getAttribute("idCurrent") != null ? Integer.parseInt(request.getAttribute("idCurrent").toString()) : -1;
    HashMap<String, String> error = new HashMap<String, String>();
    if (request.getAttribute("error") != null) {
        error = (HashMap<String, String>) request.getAttribute("error");
    }
    PriceInfo currentPriceInfo = null;
    if (request.getAttribute("currentPriceInfo") != null) {
        currentPriceInfo = (PriceInfo) request.getAttribute("currentPriceInfo");
    }
%>

<!DOCTYPE html>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Prix du Vol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/insertFlight.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/messages.css">

</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Prix du Vol <%= flight.getFlight_id() %>
    </h2>

    <div class="price-forms">
        <% for (SeatType seatType : seatTypes) {
            PriceInfo priceInfo = null;
            for (PriceInfo pi : priceInfos) {
                if (pi.getSeat_type_id() == seatType.getSeat_type_id()) {
                    priceInfo = pi;
                    break;
                }
            }
        %>
        <% if (seatType.getSeat_type_id() == id) {
            priceInfo = currentPriceInfo;
        %>

        <jsp:include page="components/messages.jsp"/>
        <% } %>
        <form action="./updatePriceInfo" method="post" class="price-form">
            <h3><%= seatType.getType_name() %>
            </h3>
            <input type="hidden" name="PriceInfo.flight_id" value="<%= flight.getFlight_id() %>">
            <input type="hidden" name="PriceInfo.seat_type_id" value="<%= seatType.getSeat_type_id() %>">

            <% if (seatType.getSeat_type_id() == id) { %>
            <% if (error.get("unit_price") != null) { %>
            <div class="error-message"><%= error.get("unit_price") %>
            </div>
            <% }
            } %>
            <div class="form-group">
                <label for="unit_price_<%= seatType.getSeat_type_id() %>">Prix unitaire:</label>
                <input type="number"
                       step="0.01"
                       min="0"
                       id="unit_price_<%= seatType.getSeat_type_id() %>"
                       name="PriceInfo.unit_price"
                       value="<%= priceInfo != null ? priceInfo.getUnit_price() : 0 %>"
                       required>
            </div>

            <% if (seatType.getSeat_type_id() == id) { %>
            <% if (error.get("discount_percentage") != null) { %>
            <div class="error-message"><%= error.get("discount_percentage") %>
            </div>
            <% }
            } %>
            <div class="form-group">
                <label for="discount_<%= seatType.getSeat_type_id() %>">Réduction (%):</label>
                <input type="number"
                       step="0.01"
                       min="0"
                       max="100"
                       id="discount_<%= seatType.getSeat_type_id() %>"
                       name="PriceInfo.discount_percentage"
                       value="<%= priceInfo != null ? priceInfo.getDiscount_percentage() : 0 %>">
            </div>


            <% if (seatType.getSeat_type_id() == id) { %>
            <% if (error.get("number") != null) { %>
            <div class="error-message"><%= error.get("number") %>
            </div>
            <% }
            } %>
            <div class="form-group">
                <label for="number_<%= seatType.getSeat_type_id() %>">Nombre de sièges en promotion:</label>
                <input type="number"
                       min="0"
                       id="number_<%= seatType.getSeat_type_id() %>"
                       name="PriceInfo.number"
                       value="<%= priceInfo != null ? priceInfo.getNumber() : 0 %>">
            </div>

            <div class="form-actions">
                <button type="submit" class="btn-primary">
                    <i class="fas fa-save"></i> Enregistrer
                </button>
            </div>
        </form>
        <% } %>
    </div>

    <div class="form-actions" style="margin-top: 2rem;">
        <button type="button" class="btn-secondary" onclick="window.location.href='./showAllFlights'">
            <i class="fas fa-arrow-left"></i> Retour
        </button>
    </div>
</div>

</body>
</html>