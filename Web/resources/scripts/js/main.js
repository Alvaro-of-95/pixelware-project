// Módulo principal de la aplicacion:
var tiempo = angular.module("appTiempo", []);

// Controlador principal de la aplicación:
var controlador = tiempo.controller("appController",
		["$scope", "$http", function($scope, $http) {
			// Definir variables de ámbito de Angular:
			$scope.data = [];
			
			/* Variable ready: Indica el estado de la petición, para
			 * usarlo en la directiva ng-show y mostrar los bloques
			 * html que correspondan:
			 *		- Si ready = 0: No se muestra ningún bloque.
			 *		- Si ready = 1: Mostramos el bloque de cargando.
			 *		- Si ready = 2: Mostramos el bloque con el resultado.
			 *		- Si ready = 3: Mostramos el bloque de error. */
			$scope.ready = 0;
			
			// Método activado por el formulario:
			$scope.showCity = function(formData) {
				$scope.ready = 1;
				
				// Realizar la petición, añadiendo la cadena introducida
				// en el input del formulario al final de la url:
				$http({
					method: 'GET',
					url: 'http://api.apixu.com/v1/current.json?key=4dc1395b72974483bff105320170610&q='
						 + formData.nombre
				
				// Si la petición devuelve resultado, guardarlo en la variable:
				}).success(function(datos, status, headers, config) {
					$scope.data = datos;
					$scope.ready = 2;
				
				// Si la petición devuelve un error, mostrarlo:
				}).error(function(datos, status, headers, config) {
					$scope.ready = 3;
				});
			}
			
		}]);