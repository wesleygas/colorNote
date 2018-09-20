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
	<%
		User user = (User) request.getAttribute("user");
	%>
	<!-- Dropdown Structure -->
	<ul id="dropdown1" class="dropdown-content">
		<li><a href="javascript:history.back()">Home</a></li>
		<li><a href="./Login">Sair</a></li>
	</ul>

	<nav class="nav-extended">
		<div class="nav-wrapper">
			<a href="#!" class="brand-logo center"><i class="material-icons">cloud</i>Color
				Note</a>
			<ul class="left hide-small-only">
				<li><a href="#!" class="dropdown-trigger"
					data-target="dropdown1"><i class="material-icons">more_vert</i></a></li>

			</ul>
		</div>
	</nav>


	<div class="section">
		<div class="row">
			<div class="col 18 s12">
				<h2 class="teal-text text-lighten-2">
					Your Profile<i class="material-icons">person</i>
				</h2>
				<div class="card-panel alcaramel" style="min-height: 640px;">
					<h5>Information</h5>
					<hr>
					<span class="teal-text text-lighten-2">Username:</span>
					<p><%=user.getUsername()%></p>
					<hr>
					<span class="teal-text text-lighten-2">Última sessão ativa</span>
					<p><%=user.getLast_session()%></p>
					<hr>
					<span class="teal-text text-lighten-2">Mudar Senha</span>
					<form
						action="UserOptions?action=change_password&user_id=<%=user.getUser_id()%>"
						method="post">
						<input type="password" id="new_password" name="new_password">
						<button type="submit" class="waves-effect waves-light btn-small">Mudar
							Senha</button>
					</form>
					<hr>
					<span class="teal-text text-lighten-2">Desativar minha conta</span>
					<form
						action="UserOptions?action=delete&user_id=<%=user.getUser_id()%>"
						method="post">
						<button type="submit"
							class="waves-effect waves-light btn-small btn red">Desativar</button>
					</form>
					<hr>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>

	<script type="text/javascript">
	$(document).ready(function() {
		$(".dropdown-trigger").dropdown();
	});
	</script>

</body>

</html>