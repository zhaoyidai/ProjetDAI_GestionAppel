<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="metier.Users"%>
<%@page import="metier.Seance" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emploi du Temps</title>
<meta name="viewport" charset="UTF-8"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">

</head>
<body>
<%
	Users users = (Users) session.getAttribute("auth");
	if(users == null){
		response.sendRedirect("Login");
	}
	%>
	
<p>Vous êtes connecté en tant que : ${ sessionScope.prenom } ${ sessionScope.nom }
				(${ sessionScope.statut }) !</p>
<p>Mon planning</p>
<%
List<Seance> seances=(List<Seance>)request.getAttribute("seances");
for(Seance s:seances){
	out.println("<p>Cours : "+s.getCoursSeance().getNomC()+" Debut : "+s.getHeureDebut()+" Duree : "+s.getDuréeS()+"</p>");
 
	/* out.println("<p>CoursDebut : "+s.getHeureDebut()+"</p>"); */
 }
%>



<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
</body>
</html>