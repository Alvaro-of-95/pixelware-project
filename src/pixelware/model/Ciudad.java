package pixelware.model;

public class Ciudad {
	
	private String nombre;

	
	// Constructor por defecto:
	public Ciudad() {}
	
	// Constructor general:
	public Ciudad(String nombre) {
		this.nombre = nombre;
	}

	// toString:
	@Override
	public String toString() {
		return "Ciudad [nombre=" + nombre + "]";
	}

	// Getter y Setter:
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}