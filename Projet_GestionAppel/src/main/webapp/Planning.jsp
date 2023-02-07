<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="metier.Users"%>
<%@page import="metier.Seance" import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emploi du Temps</title>
<meta name="viewport" charset="UTF-8"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="formLogin/css/planning.css">
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
<!-- <table border=1px>
  <tr>
    <th>Lundi</th>
    <th>Mardi</th>
    <th>Mercredi</th>
  </tr> -->
<ul>

  

<%
List<Seance> seances=(List<Seance>)request.getAttribute("seances");
Calendar calendar = Calendar.getInstance(); 
List<Seance> lundi=new ArrayList<>();
List<Seance> mardi=new ArrayList<>();
List<Seance> mercredi=new ArrayList<>();
List<Seance> jeudi=new ArrayList<>();
List<Seance> vendredi=new ArrayList<>();
for(Seance s:seances){
	
	
    
	calendar.setTime(s.getDateSeance());
	
	int day = calendar.get(Calendar.DAY_OF_WEEK); 
	String daytext="";
	switch (day) {
    case 1:
    	daytext = "Lundi";
    	lundi.add(s);
        break;
    case 2:
    	daytext = "Mardi";
    	mardi.add(s);
        break;
    case 3:
    	daytext = "Mercredi";
    	mercredi.add(s);
        break;
    case 4:
    	daytext = "Jeudi";
    	jeudi.add(s);
        break;
    case 5:
    	daytext = "Vendredi";
    	vendredi.add(s);
        break;
    case 6:
    	daytext = "Samedi";
        break;

    }
	/* out.println("<p>Cours : "+s.getCoursSeance().getNomC()+" Jour : "+daytext+" Debut : "+s.getHeureDebut()+" Duree : "+s.getDuréeS()+" Fin :"+formatted+" <a href=\"CtrlAccederFicheAppel?idSeance="+s.getIdS()+"\">Fiche d'appel</a></p>");
  */
  
	
 }
out.println("<li>Lundi");
for(int i=0;i<lundi.size();i++){
	Seance s=mercredi.get(i);
	Calendar timed=Calendar.getInstance();
	timed.setTime(s.getHeureDebut());
	timed.add((Calendar.HOUR), s.getDuréeS());
	Date date = timed.getTime(); 
	
	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
	/* 
	String formatted = format1.format(date); */
	
	out.println("<p>__________________</p><p>Cours : "+s.getCoursSeance().getNomC()+" </p><p>Debut : "+s.getHeureDebut()+" </p><p>Fin :"+format1.format(date)+" </p><p><a href=\"CtrlAccederFicheAppel?idSeance="+s.getIdS()+"\">Fiche d'appel</a></p><p>__________________</p>"); 
  }
out.println("</li><li>Mardi");
for(int i=0;i<mardi.size();i++){
	Seance s=mercredi.get(i);
	Calendar timed=Calendar.getInstance();
	timed.setTime(s.getHeureDebut());
	timed.add((Calendar.HOUR), s.getDuréeS());
	Date date = timed.getTime(); 
	
	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
	/* 
	String formatted = format1.format(date); */
	
	out.println("<p>__________________</p><p>Cours : "+s.getCoursSeance().getNomC()+" </p><p>Debut : "+s.getHeureDebut()+" </p><p>Fin :"+format1.format(date)+" </p><p><a href=\"CtrlAccederFicheAppel?idSeance="+s.getIdS()+"\">Fiche d'appel</a></p><p>__________________</p>"); 
  }
out.println("</li><li>Mercredi");
for(int i=0;i<mercredi.size();i++){
	Seance s=mercredi.get(i);
	Calendar timed=Calendar.getInstance();
	timed.setTime(s.getHeureDebut());
	timed.add((Calendar.HOUR), s.getDuréeS());
	Date date = timed.getTime(); 
	
	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
	/* 
	String formatted = format1.format(date); */
	
	out.println("<p>__________________</p><p>Cours : "+s.getCoursSeance().getNomC()+" </p><p>Debut : "+s.getHeureDebut()+" </p><p>Fin :"+format1.format(date)+" </p><p><a href=\"CtrlAccederFicheAppel?idSeance="+s.getIdS()+"\">Fiche d'appel</a></p><p>__________________</p>"+"</li>"); 
  }
out.println("</li><li>Jeudi");
for(int i=0;i<jeudi.size();i++){
	Seance s=mercredi.get(i);
	Calendar timed=Calendar.getInstance();
	timed.setTime(s.getHeureDebut());
	timed.add((Calendar.HOUR), s.getDuréeS());
	Date date = timed.getTime(); 
	
	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
	/* 
	String formatted = format1.format(date); */
	
	out.println("<p>__________________</p><p>Cours : "+s.getCoursSeance().getNomC()+" </p><p>Debut : "+s.getHeureDebut()+" </p><p>Fin :"+format1.format(date)+" </p><p><a href=\"CtrlAccederFicheAppel?idSeance="+s.getIdS()+"\">Fiche d'appel</a></p><p>__________________</p>"+"</li>"); 
  }
out.println("</li><li>Vendredi");
for(int i=0;i<vendredi.size();i++){
	Seance s=mercredi.get(i);
	Calendar timed=Calendar.getInstance();
	timed.setTime(s.getHeureDebut());
	timed.add((Calendar.HOUR), s.getDuréeS());
	Date date = timed.getTime(); 
	
	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
	/* 
	String formatted = format1.format(date); */
	
	out.println("<p>__________________</p><p>Cours : "+s.getCoursSeance().getNomC()+" </p><p>Debut : "+s.getHeureDebut()+" </p><p>Fin :"+format1.format(date)+" </p><p><a href=\"CtrlAccederFicheAppel?idSeance="+s.getIdS()+"\">Fiche d'appel</a></p><p>__________________</p>"+"</li>"); 
  }
%>
</li>
</ul>
<!--  </table> -->


<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
</body>
</html>