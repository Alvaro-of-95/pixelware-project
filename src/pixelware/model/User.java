package pixelware.model;

// Entidad que encapsula la tabla CATEGORIES de la BBDD MySQL:
public class User {

	private String nombre;
	private String clave;
	
	// Constructor por defecto:
	public User() {}
	
	
	// Constructor general:
	public User(String nombre, String clave) {
		this.nombre = nombre;
		this.clave = clave;
	}
	
	@Override
	public String toString() {
		return "User [nombre=" + nombre +
				", clave=" + clave + "]";
	}

	// Getter y Setter:
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
}