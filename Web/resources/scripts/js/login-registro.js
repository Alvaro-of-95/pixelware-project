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