<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<ul>
					<!-- Nav Item - Accueil enseigants -->
					<li class="nav-item active"><a class="nav-link" href="CtrlRedirectIndicateur?type_action=indicateur"> <span>Indicateur des statuts</span>
					</a></li>
					<!-- Nav Item - Utilities Collapse Menu -->
					<li class="nav-item"><a class="nav-link collapsed"
						href="CtrlRedirectGraph?type_action=graphique"
						data-target="#collapseUtilities"> <i
							class="fas fa-fw fa-wrench"></i> <span>Graphique des absences par séance</span>
					</a></li>
					<!-- Nav Item - Pages Collapse Menu -->
					<li class="nav-item"><a class="nav-link collapsed"
						href="CoursController?id=${sessionScope.id}"
						data-target="#collapsePages"> <i class="fas fa-fw fa-folder"></i>
							<span>Liste absents avec plus de 3 absences injustifiées</span>
					</a></li>
					</ul>
</body>
</html>