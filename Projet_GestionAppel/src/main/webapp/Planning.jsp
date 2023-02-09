<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="metier.Users"%>
<%@page import="metier.Seance" import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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

<!-- Custom fonts for this template-->
<link href="formAccueil/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="formAccueil/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>
	<%
	Users users = (Users) session.getAttribute("auth");
	if(users == null){
		response.sendRedirect("Login");
	}
	%>

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="/compte/accueil">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">Gestion d'appel</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">
			<c:choose>
				<c:when test="${sessionScope.statut == Statut.ENSEIGNANT}">
					<!-- Nav Item - Utilities Collapse Menu -->
					<li class="nav-item active"><a class="nav-link collapsed"
						href="CtrlRedirect?type_action=planning"
						data-target="#collapseUtilities"> <i
							class="fas fa-fw fa-wrench"></i> <span>Planning</span>
					</a></li>
					<!-- Nav Item - Pages Collapse Menu -->
					<li class="nav-item"><a class="nav-link collapsed"
						href="CoursController?id=${sessionScope.id}"
						data-target="#collapsePages"> <i class="fas fa-fw fa-folder"></i>
							<span>Cours</span>
					</a></li>
					<!-- Nav Item - Tables -->
					<li class="nav-item"><a class="nav-link" href="#"> <i
							class="fas fa-fw fa-table"></i> <span>Absences Etudiants</span></a></li>
					<!-- Nav Item - Tables -->
					<li class="nav-item"><a class="nav-link" href="#"> <i
							class="fas fa-fw fa-table"></i> <span>Récapitulatif
								alternance</span></a></li>
				</c:when>
			</c:choose>
			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>
		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">
							<div class="input-group-append"></div>
						</div>
					</form>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>

						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> </a> <!-- Dropdown - Alerts -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="alertsDropdown">


								<!-- Nav Item - Messages -->
								<li class="nav-item dropdown no-arrow mx-1"><a
									class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> <!-- Counter - Messages -->
								</a> <!-- Dropdown - Messages -->
									<div
										class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
										aria-labelledby="messagesDropdown"></div></li>

								<div class="topbar-divider d-none d-sm-block"></div>

								<!-- Nav Item - User Information -->
								<li class="nav-item dropdown no-arrow"><a
									class="nav-link dropdown-toggle" href="#" id="userDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> <span
										class="mr-2 d-none d-lg-inline text-gray-800 ">${ sessionScope.prenom }
											${ sessionScope.nom }</span> <img class="img-profile rounded-circle"
										src="formAccueil/img/undraw_profile.svg">
								</a> <!-- Dropdown - User Information -->
									<div
										class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
										aria-labelledby="userDropdown">
										<a class="dropdown-item" href="#"> <i
											class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
											Profil
										</a> <a class="dropdown-item" href="#"> <i
											class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
											Modifier mon profil
										</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="DeconnexionController"
											data-target="#logoutModal"> <i
											class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
											Deconnexion
										</a>
									</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Mon planning</h1>
					</div>
					<!-- Milieu -->
					<div class="row">
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
	timed.add((Calendar.HOUR), s.getDureeS());
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
	timed.add((Calendar.HOUR), s.getDureeS());
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
	timed.add((Calendar.HOUR), s.getDureeS());
	Date date = timed.getTime(); 
	
	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
	/* 
	String formatted = format1.format(date); */
	
	out.println("<p>__________________</p><p>Cours : "+s.getCoursSeance().getNomC()+" </p><p>Debut : "+s.getHeureDebut()+" </p><p>Fin :"+format1.format(date)+" </p><p><a href=\"CtrlAccederFicheAppel?idSeance="+s.getIdS()+"\">Fiche d'appel</a></p><p>__________________</p>"+"</li>"); 
  }
out.println("</li><li>Jeudi");
for(int i=0;i<jeudi.size();i++){
	Seance s=jeudi.get(i);
	Calendar timed=Calendar.getInstance();
	timed.setTime(s.getHeureDebut());
	timed.add((Calendar.HOUR), s.getDureeS());
	Date date = timed.getTime(); 
	
	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
	/* 
	String formatted = format1.format(date); */
	
	out.println("<p>__________________</p><p>Cours : "+s.getCoursSeance().getNomC()+" </p><p>Debut : "+s.getHeureDebut()+" </p><p>Fin :"+format1.format(date)+" </p><p><a href=\"CtrlAccederFicheAppel?idSeance="+s.getIdS()+"\">Fiche d'appel</a></p><p>__________________</p>"+"</li>"); 
  }
out.println("</li><li>Vendredi");
for(int i=0;i<vendredi.size();i++){
	Seance s=vendredi.get(i);
	Calendar timed=Calendar.getInstance();
	timed.setTime(s.getHeureDebut());
	timed.add((Calendar.HOUR), s.getDureeS());
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
					</div>

					<!-- Content Row -->

					<div class="row">


						<!-- Pie Chart -->
						<div class="col-xl-4 col-lg-5"></div>
					</div>

					<!-- Content Row -->
					<div class="row"></div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Home Web &copy; La créativité autour du web</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>



	<!-- Bootstrap core JavaScript-->
	<script src="formAccueil/vendor/jquery/jquery.min.js"></script>
	<script src="formAccueil/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="formAccueil/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="formAccueil/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="formAccueil/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="formAccueil/js/demo/chart-area-demo.js"></script>
	<script src="formAccueil/js/demo/chart-pie-demo.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
</body>
</html>