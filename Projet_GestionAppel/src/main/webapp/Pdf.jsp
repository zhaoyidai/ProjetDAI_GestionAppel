<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="dao.EtudiantPresence"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.Element"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
response.setContentType("application/pdf");
Document document = new Document();
PdfWriter writer=PdfWriter.getInstance(document, response.getOutputStream());
document.open();
Paragraph p = new Paragraph();
p.add("UT1 Capitole Toulouse");
p.setAlignment(Element.ALIGN_CENTER);
document.add(p);

Paragraph p2 = new Paragraph();

List<EtudiantPresence> etudiants=(List<EtudiantPresence>)request.getAttribute("listeEtudiant");


p2.add("Récapitulatif d'absences du" + etudiants.get(0).getDateSeance()); //no alignment


document.add(p2);
Font f = new Font();
f.setStyle(Font.BOLD);
f.setSize(8);


/* Paragraph p3=null; */
PdfPTable table=new PdfPTable(3);
table.setWidths(new int[]{1, 1, 1});
table.addCell("Nom");
table.addCell("Prenom");
table.addCell("Status");
for(EtudiantPresence e:etudiants){
	table.addCell(e.getU().getNom());
	table.addCell(e.getU().getPrenom());
	table.addCell(e.getStatus().toString());
	/* p3 = new Paragraph();
	p3.add(e.getU().getNom()+" "+e.getU().getPrenom()+" "+e.getStatus());
	document.add(p3); */
	
}
document.add(table);
document.close();
%>
</body>
</html>