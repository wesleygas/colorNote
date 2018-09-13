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
		<div class="row">
			<%
				List<Note> notas = (List<Note>) request.getAttribute("notas");
				User user = (User) request.getAttribute("user");
			%>
			<%
				if (notas != null) {
					for (Note nota : notas) {
			%>
			<div class="col s12 m6">
				<div class="card blue-grey darken-1" onclick="selectNote(<%=nota.getNote_id()%>)">
					<div class="card-content white-text">
						<span class="card-title"><%=nota.getTitle()%></span>
						<p><%=nota.getBody()%></p>
					</div>
					<div class="card-action">
						<button onclick="deleteNote(<%=nota.getNote_id()%>)" class="waves-effect waves-light btn-small" ><i class="material-icons right" >delete</i> Delete</button>
						<a href="#">Minha altura: <%=nota.getNote_id()%></a>
					</div>
				</div>
			</div>
			<%
				}
				} else {
			%>
				<div class="valign-wrapper center-align">
					<h5>Nenhuma nota foi encontrada :/ </h5>
				</div>
			<%
				}
			%>
		</div>
	</div>

	<!-- Modal Structure -->
	<div id="addNote" class="modal">
		<form action="Home" method="post">
			<div class="modal-content">
				<div class="input-field">
					<input type="text" id="title" name="title"> <label
						for="title">Titulo</label>
				</div>
				<br>
				<div class="input-field">
					<textarea id="body" name="body" class="materialize-textarea"></textarea>
					<label for="body">Corpo</label>
					<input type="hidden" name="user_id" value="<%=user.getUser_id()%>">
				</div>
			</div>
			<div class="modal-footer">
      			<a href="#!" class="modal-action modal-close waves-effect waves-red btn red lighten-1">Done</a>
    		</div>
		</form>
	</div>


	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".dropdown-trigger").dropdown();
			$('.sidenav').sidenav();
			$('.modal').modal();
			$('.atualiza').click(function() {
				location.assign("Home?username=<%=user.getUsername()%>");
			});
		});
		function selectNote(noteId) {
			$.ajax({
		    url: 'Home', // your api url
		    method: 'PUT', // method is any HTTP method
		    data: {"note_id": noteId}, // data as js object
		    success: function() {}
		});
		}
		function deleteNote(noteId) {
			$.ajax({
		    url: 'Home?user_id=<%=user.getUser_id()%>&note_id='+ noteId, // your api url
		    method: 'DELETE', // method is any HTTP method
		    data: {"note_id": noteId}, // data as js object
		    success: function() {}
		});
		}
	</script>
</body>
</html>