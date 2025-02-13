<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>SkyReserve - Connexion</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="login-container"><h2>Connexion à SkyReserve</h2>
    <form action="./login" method="post">
        <div class="form-group"><label for="name">Nom d'utilisateur:</label> <input type="text" id="name" name="name"
                                                                                    required></div>
        <div class="form-group"><label for="password">Mot de passe:</label> <input type="password" id="password"
                                                                                   name="password" required></div>
        <button type="submit">Se connecter</button>
    </form>
</div>
</body>
</html>