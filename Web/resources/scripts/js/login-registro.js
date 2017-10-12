// Creacion del modulo principal de la aplicacion:
var servicios = angular.module("appUsuarios", []);

var controlador = servicios.controller("appController",
		["$scope", "$http", function($scope, $http) {
			$scope.paises = [];
			
			// Petición para cargar los países del fichero JSON:
			$http({
				method: 'GET',
				url: 'resources/scripts/js/countries.json'
					
			}).success(function(datos, status, headers, config) {
				$scope.paises = datos;
				
			}).error(function(){
				
			});
			
		}]);


$(document).ready(function() {
	// Evento activado al enviar el formulario:
	$("form").bind("submit", function() {
		$(".alert-info").css("display", "block");
		$(".alert-danger").css("display", "none");
	});
	
	// Mostrar el mensaje de alerta si contiene caracteres:
	if (!$(".alert-danger > span").text().trim().length == 0) {
		$(".alert-danger").css("display", "block");
	}
});