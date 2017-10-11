package pixelware.model;

// Entidad que encapsula la tabla Users de la BBDD MySQL:
public class User {

	private String nombre;
	private String clave;
	private String email;
	private String fecha;
	private String pais;
	
	// Constructor por defecto,
	// para cuando entramos a Index:
	public User() {}
	
	
	// Constructor con nombre y clave, para el Login:
	public User(String nombre, String clave) {
		this.nombre = nombre;
		this.clave = clave;
	}

	
	// Constructor general, para el Registro:
	public User(String nombre, String clave, String email, String fecha, String pais) {
		this.nombre = nombre;
		this.clave = clave;
		this.email = email;
		this.fecha = fecha;
		this.pais = pais;
	}

	
	// toString, para test:
	@Override
	public String toString() {
		return "User [nombre=" + nombre + ", clave=" + clave + ", email="
				+ email + ", fecha=" + fecha + ", pais=" + pais + "]";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}