<%@page import="enumtype.Statut"%>
<%@page import="org.hibernate.Session"%>
<%@page import="metier.Users"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>Cours</title>
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

<table id="table" border ="1">
	<tr>
		<td>Photo</td><td>Numéro_étudiant</td><td>Nom</td><td>Prénom</td><td>Formation</td><td>Présent</td><td>Retard</td><td>Absent</td><td>Commentaire</td>
	</tr>
<% 

		
		List<Users> listeEtudiant = (List<Users>)request.getAttribute("listeEtudiant");
		
	for(Users us : listeEtudiant){
		out.println("<tr><td><img  src=\""+ us.getPhoto() +"\"style=\"width: 50px; height: 50px;\" />" + "</td><td>" + us.getId() + "</td><td>"  + us.getNom() + "</td><td>"  + us.getPrenom() +"</td><td>"  + us.getFormation() +"</td><td>" + "<input type='checkbox'>" + "</td><td>" + "<input type='time'>"+"</td><td>" + "<input type='checkbox'>"+"</td><td>" + "<input type='text'>"+" </td></tr>");
		
		/*  out.println(us.toString()); */
	}
%>	
</table>		

<a href="CtrlRecap?idSeance=${param.idSeance }"><button id="btn_valider">Valider</button></a>


<a href="Accueil.jsp">Retour</a>
<script type="text/JavaScript" src="js/fonctionjs.js"></script>
</body>
</html>