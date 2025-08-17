<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    String passeportPhoto = (String) session.getAttribute("passportPhotoPath");
%>

<!DOCTYPE html>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Photo de Passeport</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/passportPhoto.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dark-mode.css">
    <script src="${pageContext.request.contextPath}/assets/js/theme.js" defer></script>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container">
    <h2>Photo de Passeport</h2>
    
    <div class="photo-section">
        <div class="current-photo">
            <% if (passeportPhoto != null) { %>
                <img src="<%= passeportPhoto %>" alt="Photo de passeport" class="passport-image">
            <% } else { %>
                <div class="no-photo">
                    <i class="fas fa-camera" style="font-size: 3rem; color: #ddd; margin-bottom: 1rem;"></i>
                    <p>Aucune photo de passeport uploadée</p>
                </div>
            <% } %>
        </div>
    </div>
    
    <div class="upload-form">
        <h3>
            <i class="fas fa-upload"></i>
            <%= passeportPhoto != null ? "Changer" : "Ajouter" %> votre photo de passeport
        </h3>
        
        <form action="uploadPassportPhoto" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="passportPhoto">Sélectionner une image:</label>
                <input type="file" 
                       id="passportPhoto" 
                       name="passportPhoto" 
                       accept="image/*" 
                       class="file-input" 
                       required>
            </div>
            
            <button type="submit" class="btn-upload">
                <i class="fas fa-cloud-upload-alt"></i>
                <%= passeportPhoto != null ? "Modifier" : "Importer" %> la photo
            </button>
        </form>
    </div>
</div>

</body>
</html>
