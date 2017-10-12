<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<link rel="shortcut icon" href="resources/styles/img/favicon.ico">
	<link rel="stylesheet" type="text/css" href="resources/styles/css/bootswatch-paper.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="resources/styles/css/login.css"/>
</head>
<body>
	
	<div class="container">
		<section class="col-md-6 col-md-offset-3">
			<div class="col-xs-12 header">
				<h3>Login</h3>
			</div>
		
			<form method="post" modelAttribute="user" action="login">
				<table>
					<tr>
						<td>Usuario</td>
						<td>
							<input type="text" name="nombre" id="nombre"
							required="required"/>
						</td>
					</tr>
					<tr>
						<td>Contraseña</td>
						<td>
							<input type="password" name="clave" id="clave"
							required="required"/>
						</td>
					</tr>
				</table>
				
				<input type="submit" value="Iniciar sesión"/>
			</form>
			
			<p>¿No tienes cuenta? <a href="registro">Crea una aquí</a></p>
			
			<div class="alert alert-dismissible alert-info">
				Procesando...
			</div>
			
			<div class="alert alert-dismissible alert-danger fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<span>${error}</span>
			</div>
		</section>
	</div>
	
	<script type="text/javascript" src="resources/scripts/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="resources/scripts/js/angular.min.js"></script>
	<script type="text/javascript" src="resources/scripts/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/scripts/js/login-registro.js"></script>
</body>
</html>