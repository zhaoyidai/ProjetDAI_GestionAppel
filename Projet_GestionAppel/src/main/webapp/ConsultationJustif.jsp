
<%@ page import="metier.Justificatif"%>
<%@ page import="java.util.ArrayList"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border ="1">
<h1>Listes des abscences </h1>
<%
ArrayList <Justificatif> just = (ArrayList<Justificatif>) request.getAttribute("Justificatif");
for (Justificatif j: just){
 out.println("<tr><td>" + j.getUsersJustificatif().getNom()+ " " +j.getUsersJustificatif().getPrenom()+ "</td><td>" + j.getDateDeb() + "</td><td><a href='"+j.getUrl() +"'>Consulter le justificatif</td><td>" + j.getDateDeb()  + "</td><td>" + j.getDateFin()+ "</td><td><input type='checkbox' value='"+j.isValidation()+"'/>Valider</td></tr>" );
}
System.out.print(just.size());
%> 

</table>
</body>
</html>