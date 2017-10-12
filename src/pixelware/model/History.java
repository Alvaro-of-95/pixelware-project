package pixelware.model;

// Clase utilizada para la lista
// de ciudades en el historial:
public class History {
	
	private String ciudad;

	// Constructor por defecto:
	public History() {}
	
	// Constructor general:
	public History(String ciudad) {
		this.ciudad = ciudad;
	}

	// toString:
	@Override
	public String toString() {
		return "Ciudad [Ciudad=" + ciudad + "]";
	}

	// Getter y Setter:
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}