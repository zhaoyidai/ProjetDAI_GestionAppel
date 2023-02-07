<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="metier.Users"%>
<%@page import="metier.Seance" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>Liste des absences</h1>
 <h5>Merci de justifier vois absenses</h5>
<%-- <%     
	ArrayList<Seance>listeseances  = (ArrayList<Seance>) session.getAttribute("listeAbscences");
 						for(Seance m : listeseances){						
%> --%>

	<table>
	<tr>
	<%-- <td><%= m %></td> --%>
	<td>
    <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
    <form method="post" action="Test" enctype="multipart/form-data">
        <p>
            <label for="description">Description du fichier : </label>
            <input type="text" name="description" id="description" />
        </p>
        <p>
            <label for="fichier">Fichier à envoyer : </label>
            <input type="file" name="fichier" id="fichier" />
        </p>
        
        <input type="submit" />
    </form>
    </td>
    </tr>
    </table>
<%-- <%     
 						}
	
%> --%>
</body>
</html>