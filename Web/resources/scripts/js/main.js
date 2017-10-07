// Módulo principal de la aplicacion:
var tiempo = angular.module("appTiempo", []);

// Controlador principal de la aplicación:
var controlador = tiempo.controller("appController",
	["$scope", "$http", function($scope, $http) {
			// Definir variables de ámbito de Angular:
			$scope.data = [];
			$scope.ciudad;
			$scope.hora;
			
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
						 + $scope.ciudad
				
				// Si la petición devuelve resultado, guardarlo en la variable:
				}).success(function(datos, status, headers, config) {
					$scope.data = datos;
					$scope.ready = 2;
					
					// Sacar la hora de la cadena devuelta por el json
					// (que devuelte la fecha junto a la hora:
					var cadenaHora = datos.location.localtime.split(" ");
					$scope.hora = cadenaHora[1];
				
				// Si la petición devuelve un error, mostrar el bloque de error
				// con el nombre de la ciudad introducida::
				}).error(function(datos, status, headers, config) {
					if (status == 400) {
						// Si el código de estado es 400, se debe
						// a que el nombre introducido no existe:
						$scope.ready = 3;
					} else {
						// Si es otro código de error:
						$scope.ready = 4;
					}
				});
				
				$scope.ciudad = "";
			}
		}]);