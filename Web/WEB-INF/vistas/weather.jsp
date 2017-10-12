<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="appTiempo">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Consulta del tiempo</title>
	<link rel="shortcut icon" href="resources/styles/img/favicon.ico">
	<link rel="stylesheet" type="text/css" href="resources/styles/css/bootswatch-paper.css">
	<link rel="stylesheet" type="text/css" href="resources/styles/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="resources/styles/css/weather.css"/>
</head>
<body ng-controller="appController">

	<div class="container">
		<section class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2">
			<div class="col-xs-12 header">
				<h3 class="pull-left">${sessionScope.usuario}</h3>
				<a href="logout" class="pull-right">Cerrar sesión</a>
			</div>
			
			<form name="formData" ng-submit="showCity()">
				<div>
					<label for="nombre">Introduce una ciudad</label>
					<input type="text" name="ciudad" id="ciudad"
					required="required" ng-model="ciudad"/>
				</div>
				
				<div class="historial">
					<span>Historial</span>
					<select ng-model="ciudad" ng-change="showCity()">
						<c:forEach items="${history}" var="item">
							<option value="${item.ciudad}"><c:out value="${item.ciudad}"/></option>
						</c:forEach>
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
	
	<script type="text/javascript" src="resources/scripts/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="resources/scripts/js/angular.min.js"></script>
	<script type="text/javascript" src="resources/scripts/js/weather.js"></script>
</body>
</html>