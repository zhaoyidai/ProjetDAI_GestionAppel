<%@ page import="metier.Justificatif"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom fonts for this template-->
<link href="formAccueil/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="formAccueil/css/sb-admin-2.min.css" rel="stylesheet">
<title>Gesti Appel</title>
</head>
<body>
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="#">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">Gestion d'appel</div>
			</a>
			<!-- Divider -->
			<hr class="sidebar-divider my-0">
			<c:choose>
				<c:when test="${sessionScope.statut == Statut.SCOLARITE}">
				<li class="nav-item "><a class="nav-link" href="ProfilController?id=${sessionScope.id}">
							<i class="fas fa-fw fa-tachometer-alt"></i> <span>Mon profil</span>
					</a></li>
					<!-- Nav Item - Accueil Scolarite -->
					<li class="nav-item active"><a class="nav-link"
						href="AccueilController"> <i
							class="fas fa-fw fa-tachometer-alt"></i> <span>Accueil</span></a></li>
					<!-- Nav Item - Charts -->
					<li class="nav-item"><a class="nav-link"
						href="ScolJustificatifController"> <i
							class="fas fa-fw fa-chart-area"></i> <span>Justificatifs
								absences</span></a></li>
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
										src="${ sessionScope.photo }">
								</a> <!-- Dropdown - User Information -->
									<div
										class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
										aria-labelledby="userDropdown">

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
				<div>
					<h1 class="h3 mb-0 text-gray-800">Voulez vous confirmer les absences ?</h1>
				</div>
				<!-- Milieu -->
				<div class="row">
				<table border="1" class="table table-bordered" id="dataTable"
							width="100%" cellspacing="0">
					<form action="CtrlConfirmationValidation" method="get">					
							<%
							ArrayList<Integer> just = (ArrayList<Integer>) session.getAttribute("liste");
							for (Integer j : just) {%>
								<tr>
								<td class='style1'><%= j %></td>
								</tr>
								<% 
							}
							%>
					</form>
					</table>
					<input type="submit" value="Oui" class="btn btn-success btn-icon-split center btn-lg" />
 					</form>
					<a href="ScolJustificatifController" class="btn btn-primary btn-lg">Non</a>
 					<a href="ScolJustificatifController" class="btn btn-primary btn-lg">Annuler</a>
 					<p class="lead mb-5">${requestScope.msg_error}</p>
				</div>
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
			</div><!-- End of Content Wrapper -->
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
</body>
</html>