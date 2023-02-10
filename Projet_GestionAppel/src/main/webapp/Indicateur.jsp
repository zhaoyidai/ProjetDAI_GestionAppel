<%@page import="enumtype.AppelEtat"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.min.js"></script>
</head>
<%
	
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3307/db_21708799", "21708799", "01053S");

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT Status, ROUND((COUNT(Status) / (SELECT COUNT(*) FROM Assister)) * 100, 2) AS pourcentage FROM Assister GROUP BY Status");
	

    List<String> label = new ArrayList<>();
    List<String> data = new ArrayList<>();


    
    while (resultSet.next()) {
        label.add(resultSet.getString("Status"));
        data.add(resultSet.getString("pourcentage"));
    }

%>
<body>
<h1><center>Graphique répartition des status</center></h1><br>
<canvas id="graphMoyenne" width = "200" height = "200"></canvas><br><br>
<center>
<button style="background-color:green" disabled ><p style="color:white"><strong>PRESENCE</strong></p></button>
<button style="background-color:red" disabled ><p style="color:white"><strong>ABSENCE</strong></p></button>
<button style="background-color:orange" disabled ><p style="color:white"><strong>ABSENCE JUSTIFIEE</strong></p></button>
<button style="background-color:yellow" disabled ><p style="color:Black"><strong>RETARD</strong></p></button>
<button style="background-color:grey" disabled ><p style="color:white"><strong>NON NOTIFIER</strong></p></button><br>
<a href="CtrlAbsence?type_action=absence"><button style="background-color:grey"  ><p style="color:green"><strong>Retour</strong></p></button></a>

</center>
<script>
    var ctx = document.getElementById('graphMoyenne').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            label: ['ABSENCE','ABSENCE_JUSTIFIE','NON_NOTIFIE','PRESENCE','RETARD'],
            datasets: [{
                label: 'moyenne absences',
                data: [<%= String.join(", ", data) %>],
                backgroundColor: [
                    'red',
                    'orange',
                    'grey',
                    'green',
                    'yellow'
                ],
                borderWidth: 1
            }]
        },
        options: {
            cutoutPercentage: 50,
            responsive: true,
            maintainAspectRatio: false,
            legend: {
            	display: false,
            	labels:{
            		display: true
            	}
            },
            tooltips:{
            	enabled: true
            }
        }
    });
    
</script>
</body>
</html>