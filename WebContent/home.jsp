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
				<li><a href="#!"><i class="material-icons">refresh</i></a></li>

			</ul>
		</div>
		<div class="nav-content">
			<a
				class="btn-floating btn-large halfway-fab waves-effect waves-light teal "
				data-toggle="modal" data-target="#addNote"> <i class="material-icons">add</i>
			</a>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<%
				List<Note> notas = (List<Note>) request.getAttribute("notas");
			%>
			<%
				for (Note nota : notas) {
			%>
			<div class="col s12 m6">
				<div class="card blue-grey darken-1">
					<div class="card-content white-text">
						<span class="card-title"><%=nota.getTitle()%></span>
						<p><%=nota.getBody()%></p>
					</div>
					<div class="card-action">
						<a href="#"><%=nota.getLast_edit()%></a> <a href="#">Minha
							altura: <%=nota.getNote_id()%></a>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>

	</div>

	<div class="modal fade" id="addNote">
		<div class="row">
			<div class="col s12 m6">
				<div class="card blue-grey darken-1">
					<div class="card-content white-text">
						<span class="card-title">Card Title</span>
						<p>I am a very simple card. I am good at containing small bits
							of information. I am convenient because I require little markup
							to use effectively.</p>
					</div>
					<div class="card-action">
						<a href="#">This is a link</a> <a href="#">This is a link</a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".dropdown-trigger").dropdown();
			$('.sidenav').sidenav();
		})
	</script>
</body>
</html>