<%@page import="enumtype.Statut"%>
<%@page import="org.hibernate.Session"%>
<%@page import="metier.Users"%>
<%@ page import="java.util.ArrayList" %>
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

	%>
	<section class="clean-block clean-hero">
		<div class="text">
			<p>Vous êtes connecté en tant que : ${ sessionScope.prenom } ${ sessionScope.nom }
				(${ sessionScope.statut }) !</p>
		</div>
	</section>
<h1>Liste des étudiants</h1>

<table border ="1">
	<tr>
		<td>Photo</td><td>Numéro étudiant</td><td>Prénom</td><td>Nom</td><td>Formation</td><td>Modifier</td>
	</tr>
<% 
ArrayList<Users> liste = (ArrayList<Users>)request.getAttribute("users");
for(Users us: liste)
	out.println("<tr><td>" + us.getPhoto() + "</td><td>" + us.getId() + "</td><td>"  + us.getNom() + "</td><td>"  + us.getPrenom() +"</td><td>"  + us.getFormation() +"</td><td></td></tr>");
%>	
		
</table>



<a href="Accueil.jsp">Retour</a>

</body>
</html>