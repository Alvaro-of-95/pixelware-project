<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="appTiempo">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Consulta del tiempo</title>
	<link rel="stylesheet" type="text/css" href="resources/styles/css/bootswatch-paper.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/css/weather.css"/>
</head>
<body ng-controller="appController">

	<div class="container">
		<section class="col-md-6 col-md-offset-3">
			<div class="col-xs-12 header">
				<h3 class="pull-left">Usuario</h3>
				<a href="login" class="pull-right">Cerrar sesión</a>
			</div>
			
			<form name="formData" ng-submit="showCity()">
				<div>
					<label for="nombre">Introduce una ciudad</label>
					<input type="text" name="ciudad" id="ciudad"
					required="required" ng-model="ciudad"/>
				</div>
				
				<div class="historial">
					<span>Historial</span>
					<select>
						<option>Paris</option>
						<option>Madrid</option>
						<option>Londres</option>
						<option>Nueva York</option>
						<option>Dublin</option>
					</select>
				</div>
				
				<input type="submit" value="Mostrar tiempo"/>
			</form>
			
			<div ng-show="ready == 1" class="alert alert-dismissible alert-info">
				Cargando datos...
			</div>
			
			<div ng-show="ready == 2" class="content">
				<h3>Datos del tiempo</h3>
				<p><strong>Ciudad: </strong>{{data.location.name}} ({{data.location.country}})</p>
				<p>
					<strong>Tiempo: </strong>{{data.current.temp_c}} ºC 
					<img src="//{{data.current.condition.icon}}"/>
				</p>
				<p><strong>Hora: </strong>{{hora}}</p>
			</div>
			
			<div ng-show="ready == 3" class="alert alert-dismissible alert-danger">
				<strong>Error: </strong> No has introducido una ciudad válida.
			</div>
			
			<div ng-show="ready == 4" class="alert alert-dismissible alert-danger">
				Se ha producido un error en la conexión con el servidor.
			</div>
		</section>
	</div>
	
	<script type="text/javascript" src="resources/scripts/js/angular.min.js"></script>
	<script type="text/javascript" src="resources/scripts/js/weather.js"></script>
</body>
</html>