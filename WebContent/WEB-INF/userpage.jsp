<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon"
	href=".\WebContent\Resources\img\favicon.ico.png" type="image/x-icon">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Color Note</title>
</head>
<body>
	<%@ page import="java.util.*,colorNote.*"%>
	
	<!-- Dropdown Structure -->
	<ul id="dropdown1" class="dropdown-content">
		<li><a href="#!">one</a></li>
		<li><a href="#!">two</a></li>
		<li class="divider"></li>
		<li><a href="#!">three</a></li>
	</ul>

	<nav class="nav-extended">
		<div class="nav-wrapper">
			<a href="#!" class="brand-logo center"><i class="material-icons">cloud</i>Color
				Note</a>
			<ul class="left hide-small-only">
				<li><a href="#!" class="dropdown-trigger"
					data-target="dropdown1"><i class="material-icons">more_vert</i></a></li>
				<li><a href="#!"><i class="material-icons">view_module</i></a></li>
				<li><a href="#!" id="atualiza"><i class="material-icons">refresh</i></a></li>

			</ul>
		</div>
		<div class="nav-content">
			<a
				class="btn-floating btn-large halfway-fab waves-effect waves-light teal modal-trigger"
				href="#addNote"> <i class="material-icons">add</i>

			</a>
		</div>
	</nav>
	
	<div class="container">
		<div class="section">
			<div class="row">
			<%
				List<Note> notas = (List<Note>) request.getAttribute("notas");
				User user = (User) request.getAttribute("user");
			%>
				<div class= "col 18 s12">
					<h2>Your Profile</h2>
						<div class="card-panel alcaramel" style="min-height: 640px;">
                          <h6>Information</h6>
                          <hr>
                          <span class="detail-title">Username</span>
                          <span class==$0> "<%=user.getUsername()%>"</span>
                        </div>
			
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>