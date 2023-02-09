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
<canvas id="graphMoyenne" width = "400" height = "400"></canvas>

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