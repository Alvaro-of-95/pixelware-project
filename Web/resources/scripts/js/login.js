$(document).ready(function() {
	// Evento activado al enviar el formulario:
	$("form").bind("submit", function() {
		$(".alert-info").css("display", "block");
	});
});