<%@page import="enumtype.Statut"%>
<%@page import="org.hibernate.Session"%>
<%@page import="metier.Users"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Accueil</title>
<meta name="viewport" charset="UTF-8"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
</head>
<body id="page-top">
	<%
	Users users = (Users) session.getAttribute("auth");
	if(users == null){
		response.sendRedirect("Login");
	}
	%>
	<section class="clean-block clean-hero">
		<div class="text">
			<p>Vous êtes connecté en tant que : ${ sessionScope.id } ${ sessionScope.prenom } ${ sessionScope.nom }
				(${ sessionScope.statut }) !</p>
		</div>
	</section>
	<div id="wrapper">
		<nav
			class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0 toggled">
			<div class="container-fluid d-flex flex-column p-0">
				<a
					class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0"
					href="#">
					<div class="sidebar-brand-icon">
						<i class="fas fa-university"></i>
					</div>
					<div class="sidebar-brand-text mx-3">
						<span>Gestion appel</span>
					</div>
				</a>
				<hr class="sidebar-divider my-0">
				<ul class="navbar-nav text-light" id="accordionSidebar">
					<c:choose>
						<c:when test="${sessionScope.statut== Statut.ETUDIANT}">
							<li class="nav-item"><a href="/compte/accueil"><i
									class="fas fa-home"></i><span>Accueil</span></a></li>
							<li class="nav-item"><a href="/compte/consultation-etudiant"><i
									class="fas fa-clock"></i><span>Absences</span></a></li>
						</c:when>
						<c:when test="${sessionScope.statut == Statut.ENSEIGNANT}">
							<li class="nav-item"><a href="/compte/accueil"><i
									class="fas fa-home"></i><span>Accueil</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="CtrlRedirect?type_action=planning"><i class="fas fa-calendar-alt"></i><span>Planning</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="CoursController?id=${sessionScope.id}"><i class="fas fa-chalkboard-teacher"></i><span>Cours</span></a></li>
							<li class="nav-item"><a class="nav-link"
								href="/compte/liste-etudiant"><i
									class="fas fa-user-graduate"></i><span>Etudiants</span></a></li>
						</c:when>
						<c:when test="${sessionScope.statut == Statut.SCOLARITE}">
							<li class="nav-item"><a class="nav-link "
								href="/compte/accueil"><i class="fas fa-home"></i><span>Accueil</span></a></li>
							<li class="nav-item"><a class="nav-link "
								href="/compte/formation"><i class="fas fa-home"></i><span>Récapitulaif
										alternance</span></a></li>
							<li class="nav-item"><a class="nav-link "
								href="/compte/scolarite-justificatif"><i
									class="fas fa-clock"></i><span>Justificatifs</span></a></li>
						</c:when>
					</c:choose>
				</ul>
				<div class="text-center d-none d-md-inline">
					<form>
						<button class="btn rounded-circle border-0" id="sidebarToggle"
							type="button">
							<a href="DeconnexionController">Deconnexiosn</a>
						</button>
					</form>
				</div>
			</div>
		</nav>
		<footer class="bg-white sticky-footer">
		
		
		
		
		</footer>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
</body>
</html>
