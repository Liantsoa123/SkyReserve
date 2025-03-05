<%@ page import="java.util.HashMap" %>
<%@ page import="model.SettingReservation" %>
<%@ page import="model.SettingReservationFlight" %>
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

    SettingReservationFlight settingReservationFlight = request.getAttribute("settingReservationFlight") != null ? (SettingReservationFlight) request.getAttribute("settingReservationFlight") : null;

%>

<!DOCTYPE html>
<html data-theme="light">
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
    <h2>Paramètres de Réservation  <%=request.getAttribute("flightId")!=null? "pour le vol id ="+request.getAttribute("flightId"):""%></h2>

    <jsp:include page="components/messages.jsp"/>

    <form action="<%=settingReservationFlight!=null? "./insertSettingReservationFlight" :"./insertSettingReservation" %>" method="post">
        <input type="hidden" name="url" value="<%=settingReservationFlight!=null?"/showSettingReservationFlight?flightId="+settingReservationFlight.getFlight_id():"/showSettingReservation"%>">

        <input type="hidden" name="<%=settingReservationFlight!=null?"SettingReservationFlight.setting_reservation_id":"SettingReservation.setting_reservation_id"%>"
            <%
                     if (settingReservation != null) { %>
               value="<%=settingReservation.getSetting_reservation_id()%>"
            <%
                    } else { %>
               value="<%=request.getParameter("SettingReservation.setting_reservation_id")!=null?request.getParameter("SettingReservation.setting_reservation_id"):"-1"%>"
            <% } %> >

        <input type="hidden" name="<%=settingReservationFlight!=null?"SettingReservationFlight.setting_reservation_flight_id":"SettingReservation.setting_reservation_id"%>"
            <%
                     if (settingReservationFlight != null) { %>
               value="<%=settingReservationFlight.getFlight_id()%>"
            <%
                    } else { %>
               value="<%=request.getParameter("SettingReservationFlight.setting_reservation_flight_id")!=null?request.getParameter("SettingReservationFlight.setting_reservation_id"):"-1"%>"
            <% } %> >

        <input type="hidden" name="SettingReservationFlight.flight_id"
            <%
                if (settingReservationFlight != null) { %>
               value="<%=settingReservationFlight.getFlight_id()%>"
            <%
                } else { %>
               value="<%=request.getParameter("SettingReservationFlight.flight_id")!=null?request.getParameter("SettingReservationFlight.flight_id"):"-1"%>"
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
                   name="<%=settingReservationFlight!=null?"SettingReservationFlight.reservation":"SettingReservation.reservation"%>"
                <%
                        if (request.getParameter("SettingReservation.reservation")!= null) { %>
                   value="<%=request.getParameter("SettingReservation.reservation")%>"
                <%
                        } else if ( settingReservation!=null ){ %>
                   value="<%=settingReservation.getReservation()%>"
                <%
                        } else if (request.getParameter("SettingReservationFlight.reservation")!=null){  %>
                   value="<%=request.getParameter("SettingReservationFlight.reservation")%>"
                <%
                        } else if (settingReservationFlight !=null){ %>
                   value="<%=settingReservationFlight.getReservation()%>"
                <% } %>>
        </div>

        <% if (error.get("cancelation") != null) {%>
        <div class="error-message"><%=error.get("cancelation")%>
        </div>
        <% } %>
        <div class="form-group">
            <label for="cancelation">Critère d'annulation (Nombre d'heures avant le vol):</label>
            <input type="text"
                   id="cancelation"
                   name="<%=settingReservationFlight!=null?"SettingReservationFlight.cancelation":"SettingReservation.cancelation"%>"
                   step="0.1"
                <%
                        if (request.getParameter("SettingReservation.cancelation") != null) { %>
                   value="<%=request.getParameter("SettingReservation.cancelation")%>"
                <%
                        } else if ( settingReservation!=null ){ %>
                   value="<%=settingReservation.getCancelation()%>"
                <%
                        } else if ( request.getParameter("SettingReservationFlight.cancelation") != null ){%>
                   value="<%=request.getParameter("SettingReservationFlight.cancelation")%>"
                <%
                        }else if (settingReservationFlight != null) { %>
                   value="<%=settingReservationFlight.getCancelation()%>"
                <%
                        }
                %>>
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