// Módulo principal de la aplicacion:
var tiempo = angular.module("appLogin", []);

// Controlador principal de la aplicación:
var controlador = tiempo.controller("appController",
	["$scope", function($scope) {
			// Definir variables de ámbito de Angular:
			$scope.data = [];
			
			/* Variable ready: Indica el estado de la petición,
			 * para usarlo en la directiva ng-show y mostrar
			 * el mensaje correspondiente:
			 *		- Si ready = 0: No se muestra ningún mensaje.
			 *		- Si ready = 1: Mensaje de procesando
			 *		- Si ready = 2: Mensaje de usuario correcto
			 *		- Si ready = 3: Mensaje de usuario incorrecto */
			$scope.ready = 0;
	}]);