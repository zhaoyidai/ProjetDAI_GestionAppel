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
 <h5>Merci de justifier vos absenses</h5>
<%    
HttpSession sessionliste = request.getSession();
ArrayList<Seance>listeseances = (ArrayList<Seance>)sessionliste.getAttribute("listesabsences");
 						for(Seance m : listeseances){						
%> 

	<table border=1>
	<tr>
	<td><h3>Nom : <%= m.getCoursSeance().getNomC() %></h3></td>
	</tr>
	<tr>
	<td><h3>Date : <%= m.getDateSeance()%></h3></td>
	</tr>
	<tr>
	<td><h3>Heure : <%= m.getHeureDebut()%></h3></td>
	</tr>
	<tr>
	<td><h3>Durée : <%= m.getDureeS() %></h3></td>
	</tr>
	<tr>
	<tr>
    <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
    <form method="post" action="DepotFichierController?" enctype="multipart/form-data">
        <p>
            <label for="description">Description du fichier : </label>
            <input type="text" name="description" id="description" />
        </p>
          <p>
            <label for="description">Dates de debut : </label>
            <input type="date" name="debut" id="dated" />
        </p>
         <p>
            <label for="description">Dates de fin : </label>
            <input type="date" name="fin" id="datef" />
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
 <%     
 						}
	
%> 
</body>
</html>