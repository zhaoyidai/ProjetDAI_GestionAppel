<%@ page import="javax.swing.text.Style" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="formLogin/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="formLogin/fonts/ionicons.min.css">
    <link rel="stylesheet" href="formLogin/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="formLogin/css/styles.css">
</head>
<body>
    <%
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        String emailErreur = request.getAttribute("email_erreur") == null ? "" : (String) request.getAttribute("email_erreur");
        String passwordErreur = request.getAttribute("password_erreur") == null ? "" : (String) request.getAttribute("password_erreur");
        String generaleErreur = request.getAttribute("generale_erreur") == null ? "" : (String) request.getAttribute("generale_erreur");
    %>
    <section class="login-dark">
        <form method="post" autocomplete="off" action="LoginController">
            <h2 class="sr-only">Université Toulouse 1 Capitole</h2>
            <div class="illustration">
                <i class="icon ion-ios-locked-outline"></i>
            </div>
            <div class="mb-3">
                <input class="form-control" type="email" name="email" value="<%= email %>" placeholder="Email">
                <small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= emailErreur %></small>
            </div>
            <div class="mb-3">
                <input class="form-control" type="password" name="password" placeholder="Mot de passe">
                <small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= passwordErreur %></small>
            </div>
            <div class="mb-3">
                <small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= generaleErreur %></small>
                <button class="btn btn-primary btn-block bg-dark" type="submit">Se connecter</button>
            </div>
        </form>
        <div class="connexion_overlay"></div>
    </section>
    <script src="formLogin/js/jquery.min.js"></script>
    <script src="formLogin/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
