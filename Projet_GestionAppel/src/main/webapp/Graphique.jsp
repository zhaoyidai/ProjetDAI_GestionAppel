<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>
</style>
</head>
<% 
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3307/db_21708799", "21708799", "01053S");
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("SELECT CodeSeance, COUNT(*) FROM Assister WHERE Status LIKE 'ABSENCE%' GROUP BY CodeSeance");

List<String> labels = new ArrayList<>();
List<String> data = new ArrayList<>();

while (resultSet.next()) {
    labels.add(resultSet.getString("CodeSeance"));
    data.add(resultSet.getString("COUNT(*)"));
}
%>
<body>
<canvas id="graphiqueAbsence"></canvas>
</body>    
</html>
<script>
var ctx = document.getElementById('graphiqueAbsence').getContext('2d');
var chart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: [<%= String.join(", ", labels) %>],
        datasets: [{
            label: 'Nombre d\'absence par séances',
            data: [<%= String.join(", ", data) %>],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',

                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

</script>