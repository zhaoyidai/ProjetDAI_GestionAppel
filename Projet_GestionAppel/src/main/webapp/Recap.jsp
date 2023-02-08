<%@page import="enumtype.Statut"%>
<%@page import="org.hibernate.Session"%>
<%@page import="metier.Users"%>
<%@page import="dao.EtudiantPresence"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recap</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">

</head>
<body>
<h1>Liste des étudiants</h1>

<table id="table" border ="1">
	<tr>
		<td>Photo</td><td>Numéro_étudiant</td><td>Nom</td><td>Prénom</td><td>Formation</td>
		<td>Présence</td>
	</tr>
<% 

		
		List<EtudiantPresence> listeEtudiant = (List<EtudiantPresence>)request.getAttribute("listeEtudiant");
		
	for(EtudiantPresence u : listeEtudiant){
		Users us=u.getU();
		out.println("<tr><td><img  src=\""+ us.getPhoto() +"\"style=\"width: 50px; height: 50px;\" />" + "</td><td>" + us.getId() + "</td><td>"  + us.getNom() + "</td><td>"  + us.getPrenom() +"</td><td>"  + us.getFormation() +"</td>");
		
		if(u.getStatus().toString()=="ABSENCE"){
			out.println("<td>Absente</td>");
		}
		else{
			out.println("<td>Presente</td>");
		}
	}
%>	
</table>		

<a href="CtrlValiderFiche?ids=${param.idSeance }"><button id="btn_valider">Valider</button></a>


<a href="Accueil.jsp">Retour</a>
</body>
</html>