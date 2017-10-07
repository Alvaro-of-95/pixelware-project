package pixelware.model;

// Entidad que encapsula la tabla CATEGORIES de la BBDD MySQL:
public class User {
	
	private int id;
	private String nombre;
	private String clave;
	
	// Constructor general:
	public User(int id, String nombre, String clave) {
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", nombre=" + nombre +
				", clave=" + clave + "]";
	}

	// Getter y Setter:
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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