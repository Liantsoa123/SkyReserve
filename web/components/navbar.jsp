<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar">
    <a href="./dashboard" class="logo">
        <i class="fas fa-plane-departure"></i>
        SkyReserve
    </a>
    <div class="nav-links">
        <a href="./dashboard" ${pageContext.request.servletPath == '/WEB-INF/dashboard.jsp' ? 'class="active"' : ''}>Dashboard</a>
        <a href="./flights" ${pageContext.request.servletPath == '/WEB-INF/flights.jsp' ? 'class="active"' : ''}>Vols</a>
        <a href="./cities" ${pageContext.request.servletPath == '/WEB-INF/cities.jsp' ? 'class="active"' : ''}>Villes</a>
        <a href="./promotions" ${pageContext.request.servletPath == '/WEB-INF/promotions.jsp' ? 'class="active"' : ''}>Promotions</a>
        <a href="./logout">Déconnexion</a>
    </div>
</nav>