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
				<div class="card blue-grey darken-1 hoverable" onclick="selectNote(<%=nota.getNote_id()%>)">
					<div class="card-content white-text">
						<span class="card-title" id="tl<%=nota.getNote_id()%>"><%=nota.getTitle()%></span>
						<p id="bd<%=nota.getNote_id()%>"><%=nota.getBody()%></p>
					</div>
					<div class="card-action">
						<form action="Home?action=delete&user_id=<%=user.getUser_id()%>&note_id=<%=nota.getNote_id()%>" method="post">
							<button type="submit" class="waves-effect waves-light btn-small" ><i class="material-icons right" >delete</i> Delete</button>
						</form>
						<a class="btn-flat disabled">Last Edit:  <%=nota.getLast_edit()%></a>
						<button onclick="deleteNote(<%=nota.getNote_id()%>)" class="waves-effect waves-light btn" ><i class="material-icons right" >delete</i> Delete</button>
						<a class="btn-flat disabled">Last Edit:<%=nota.getLast_edit()%></a>
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
				<button type="submit" class="modal-close waves-effect waves-red btn red lighten-1">Done</button>
    		</div>
		</form>
	</div>
	
	<div id="editNote" class="modal">
		<form action="Home" method="post">
			<div class="modal-content">
				<div class="input-field">
					<input type="text" id="mtitle" name="mtitle"> <label
						for="title">Titulo</label>
				</div>
				<br>
				<div class="input-field">
					<textarea id="mbody" name="mbody" class="materialize-textarea"></textarea>
					<label for="body">Corpo</label>
					<input type="hidden" name="action" value="edit">
					<input type="hidden" id="mnote_id" name="note_id" value="">
					<input type="hidden" name="user_id" value="<%=user.getUser_id()%>">
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="modal-close waves-effect waves-green btn-flat">fechar</button>
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
			$('#addNote').modal();
			$('#editNote').modal();
			$('.atualiza').click(function() {
				location.assign("Home?username=<%=user.getUsername()%>");
			});
		});
		function selectNote(noteId) {
			
			var Modalelem = document.querySelector('#editNote');
            var instance = M.Modal.init(Modalelem);
            instance.open();
			console.log("You clicked note: ", noteId);
			title = $('#tl' + noteId).text();
			body = $('#bd' + noteId).text();
			console.log("Titulo:",title,"Corpo:",body);
			$('#mtitle').val(title);
			$('#mnote_id').val(noteId);
			$('#mbody').text(body);
		}
	</script>
</body>
</html>