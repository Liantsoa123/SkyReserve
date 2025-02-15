<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.SeatType" %>
<%@ page import="model.Flight" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<SeatType> seatTypes = (List<SeatType>) request.getAttribute("seatTypes");
    List<Flight> flights = (List<Flight>) request.getAttribute("flights");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    HashMap<String, String> error = new HashMap<String, String>();
    if (request.getAttribute("error") != null) {
        error = (HashMap<String, String>) request.getAttribute("error");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Ajouter un Prix</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/insertFlight.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/messages.css">
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Ajouter un Nouveau Prix</h2>

    <jsp:include page="components/messages.jsp"/>

    <form action="./insertPriceInfo" method="post">
        <% if (error.get("seat_type_id") != null) {%>
        <div class="error-message"><%=error.get("seat_type_id")%>
        </div>
        <% } %>
        <div class="form-group">
            <label for="seatType">Type de siège:</label>
            <select name="PriceInfo.seat_type_id" id="seatType" required>
                <option value="">Sélectionner un type de siège</option>
                <% if (seatTypes != null) {
                    for (SeatType seatType : seatTypes) {
                        String selected = "";
                        if (request.getParameter("PriceInfo.seat_type_id") != null) {
                            selected = Integer.parseInt(request.getParameter("PriceInfo.seat_type_id")) == seatType.getSeat_type_id() ? "selected" : "";
                        }
                %>
                <option value="<%= seatType.getSeat_type_id() %>" <%=selected%> >
                    <%= seatType.getType_name() %>
                </option>
                <% }
                } %>
            </select>
        </div>

        <% if (error.get("flight_id") != null) {%>
        <div class="error-message"><%=error.get("flight_id")%>
        </div>
        <% } %>
        <div class="form-group">
            <label for="flight">Vol:</label>
            <select name="PriceInfo.flight_id" id="flight" required>
                <option value="-1">Sélectionner un vol</option>
                <% if (flights != null) {
                    for (Flight flight : flights) {
                        String selected = "";
                        if (request.getParameter("PriceInfo.flight_id") != null) {
                            selected = Integer.parseInt(request.getParameter("PriceInfo.flight_id")) == flight.getFlight_id() ? "selected" : "";
                        }
                %>
                <option value="<%= flight.getFlight_id() %>" <%=selected%> >
                    Vol <%= flight.getFlight_id() %> - Départ: <%= dateFormat.format(flight.getDeparture_date()) %>
                </option>
                <% }
                } %>
            </select>
        </div>

        <% if (error.get("unit_price") != null) {%>
        <div class="error-message"><%=error.get("unit_price")%>
        </div>
        <% } %>
        <div class="form-group">
            <label for="unitPrice">Prix unitaire:</label>
            <input type="number" step="0.01" id="unitPrice" name="PriceInfo.unit_price" value="<%=request.getParameter("PriceInfo.unit_price")!=null?request.getParameter("PriceInfo.unit_price"):""%>">
        </div>

        <% if (error.get("discount_percentage") != null) {%>
        <div class="error-message"><%=error.get("discount_percentage")%>
        </div>
        <% } %>
        <div class="form-group">
            <label for="discountPercentage">Pourcentage de réduction:</label>
            <input type="number" step="0.01" min="0" max="100" id="discountPercentage"
                   name="PriceInfo.discount_percentage" value="<%=request.getParameter("PriceInfo.discount_percentage")!=null?request.getParameter("PriceInfo.discount_percentage"):""%>">
        </div>

        <% if (error.get("number") != null) {%>
        <div class="error-message"><%=error.get("number")%>
        </div>
        <% } %>
        <div class="form-group">
            <label for="number">Nombre de sièges:</label>
            <input type="number" min="1" id="number" name="PriceInfo.number" value="<%=request.getParameter("PriceInfo.number")!=null?request.getParameter("PriceInfo.number"):""%>">
        </div>

        <div class="form-actions">
            <button type="button" class="btn-secondary" onclick="window.location.href='#'">
                <i class="fas fa-times"></i> Annuler
            </button>
            <button type="submit" class="btn-primary">
                <i class="fas fa-plus"></i> Ajouter le prix
            </button>
        </div>
    </form>
</div>
</body>
</html>