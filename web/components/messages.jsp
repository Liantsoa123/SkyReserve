<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String message = request.getAttribute("message")!=null?request.getAttribute("message").toString():null;
    String errorMessage = request.getAttribute("errorMessage")!=null?request.getAttribute("errorMessage").toString():null;
%>

<% if (message != null) { %>
<div class="message-container success-message">
    <i class="fas fa-check-circle message-icon"></i>
    <div class="message-content">
        <h4>Succès</h4>
        <p><%= message %></p>
    </div>
    <button class="message-close" onclick="this.parentElement.style.display='none'">
        <i class="fas fa-times"></i>
    </button>
</div>
<% } %>

<% if (errorMessage != null) { %>
<div class="message-container error-message">
    <i class="fas fa-exclamation-circle message-icon"></i>
    <div class="message-content">
        <h4>Erreur</h4>
        <p><%= errorMessage %></p>
    </div>
    <button class="message-close" onclick="this.parentElement.style.display='none'">
        <i class="fas fa-times"></i>
    </button>
</div>
<% } %>