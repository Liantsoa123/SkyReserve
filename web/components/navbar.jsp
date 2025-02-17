<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    User currentUser = (User) session.getAttribute("user");
%>

<nav class="navbar">
    <a href="./dashboard" class="logo">
        <i class="fas fa-plane-departure"></i>
        SkyReserve
    </a>
    <div class="nav-links">
        <a href="./dashboard" ${pageContext.request.servletPath == '/WEB-INF/dashboard.jsp' ? 'class="active"' : ''}>Dashboard</a>
        <a href="./showAllFlights" ${pageContext.request.servletPath == '/listsFlight.jsp' || pageContext.request.servletPath == '/insertFlight.jsp' ? 'class="active"' : ''}>Vols</a>
        <a href="./showInsertPriceInfo" ${pageContext.request.servletPath == '/insertPriceInfo.jsp' ? 'class="active"' : ''}>Prix Vols</a>
        <a href="./showSettingReservation" ${pageContext.request.servletPath == '/settingReservation.jsp' ? 'class="active"' : ''}>Paramètre de Reservation</a>

        <div class="profile-menu">
            <button class="profile-button">
                <i class="fas fa-user"></i>
                <%= currentUser != null ? currentUser.getName() : "Utilisateur" %>
                <i class="fas fa-chevron-down"></i>
            </button>
            <div class="profile-dropdown">
                <div class="profile-info">
                    <p class="user-name"><%= currentUser != null ? currentUser.getName() : "Utilisateur" %></p>
                    <p class="user-role"><%= currentUser != null ? currentUser.getRole() : "Rôle" %></p>
                </div>
                <div class="dropdown-divider"></div>
                <a href="./logout" class="logout-link">
                    <i class="fas fa-sign-out-alt"></i>
                    Déconnexion
                </a>
            </div>
        </div>
    </div>
</nav>