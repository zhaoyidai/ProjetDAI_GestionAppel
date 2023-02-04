<%@ page import="javax.swing.text.Style"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Login</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="formLogin/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="formLogin/fonts/ionicons.min.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="formLogin/css/style.css">
</head>
<body class="img js-fullheight"
	style="background-image: url(img/manuphoto.jpg);">
	<%
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        String emailErreur = request.getAttribute("email_error") == null ? "" : (String) request.getAttribute("email_error");
        String passwordErreur = request.getAttribute("password_error") == null ? "" : (String) request.getAttribute("password_error");
        String generaleErreur = request.getAttribute("generale_error") == null ? "" : (String) request.getAttribute("generale_error");
    %>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0">
						<h3 class="mb-4 text-center">UT1 - CAPITOLE</h3>
						<div class=" mb-5" style="text-align: center;">
					
					<img class="img-fluid img-profile" src="img/logo2.png" alt="..." style="width : 150px; height : 150px;" />
					
						</div>
						<form method="post" action="LoginController" autocomplete="off" class="signin-form">
							<div class="form-group">
								<input type="email" name="email" placeholder="Email"  class="form-control" required> 
								<small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= emailErreur %></small>
							</div>
							<div class="form-group">
								<input id="password-field" type="password" name="password" placeholder="Mot de passe"  class="form-control" required>
								<span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
							</div>
							<small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= passwordErreur %></small>
							<div class="form-group">
								<small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= generaleErreur %></small>
								<button type="submit" class="form-control btn btn-primary submit px-3">Se connecter</button>
							</div>
							<div class="form-group d-md-flex">
								<div class="w-50">
									<label class="checkbox-wrap checkbox-primary">Remember Me <input type="checkbox" checked> 
									<span class="checkmark"></span>
									</label>
								</div>
								<div class="w-50 text-md-right">
									<a href="#" style="color: #fff">Forgot Password</a>
								</div>
							</div>
						</form>
						<div class="connexion_overlay"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="formLogin/js/jquery.min.js"></script>
	<script src="formLogin/bootstrap/js/bootstrap.min.js"></script>
	<script src="formLogin/js/jquery.min.js"></script>
	<script src="formLogin/js/popper.js"></script>
	<script src="formLogin/js/bootstrap.min.js"></script>
	<script src="formLogin/js/main.js"></script>
</body>
</html>
