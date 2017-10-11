package pixelware.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BDUtilities {
	// Variables para el trabajo con BBDD:
	private static Connection conn = null;
	private static Statement stmt;
	private static ResultSet resultSet;
	
	
	// M�todo para crear la conexi�n a la BBDD:
	private static void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String conn_db = "sql11198254";
			String conn_url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/" + conn_db;
			String conn_user = "sql11198254";
			String conn_pass = "fM4bE8BVdf";
			
			conn = DriverManager.getConnection(conn_url, conn_user, conn_pass);
			stmt = conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
	}
	
	
	// M�todo para cerrar la conexi�n con la BBDD:
	private static void closeConnection() {
		// Parar y librerar objetos:
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
								
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// M�todo para comprobar si el usuario existe en la BBDD:
	public static boolean searchUser(String nombre, String clave) throws SQLException {
		boolean existe = false;
		
		// Conectar a la BBDD:
		connectDB();
		
		resultSet = stmt.executeQuery("SELECT * FROM users WHERE nombre = '"
							+ nombre + "' AND clave='" + clave + "'");
		
		// Si la consulta devuelve resultados,
		// es porque ha encontrado el usuario:
		if (resultSet.next()) {
			existe = true;
		}
		
		// Cerrar la conexi�n:
		closeConnection();
		
		return existe;
	}

	
	// M�todo para registrar un usuario:
	public static void register(String nombre, String clave, String email,
					String fecha, String pais) throws SQLException {
		
		// Conectar a la BBDD:
		connectDB();
		
		// Insertar el registro:
		stmt.execute("INSERT INTO users (nombre, clave,"
				+ " email, fecha, pais) VALUES ('" + nombre + "', "
				+ "'" + clave + "', '" + email + "',"
				+ " '" + fecha + "', '" + pais + "')");
		
		// Cerrar la conexi�n:
		closeConnection();
	}
	
	// M�todo para cargar el historal al cargar la p�gina de tiempo:
	public static List<Ciudad> getHistory(String usuario) throws SQLException {
		// Conectar a la BBDD:
		connectDB();
		
		// Sacamos los primeros 10 resultados, ordenados por id
		// descendiente pues el registro m�s nuevo ser� el de mayor id:
		resultSet = stmt.executeQuery("SELECT ciudad FROM history WHERE usuario = '" 
								+ usuario + "' ORDER BY id DESC LIMIT 10");
		
		List<Ciudad> historial = new ArrayList<Ciudad>();
		
		// A�adimos la ciudad a la lista por cada registro:
		while (resultSet.next()) {
			Ciudad ciudad = new Ciudad();
			ciudad.setNombre(resultSet.getString(1));
			historial.add(ciudad);
		}
		
		// Cerrar la conexi�n:
		closeConnection();
		
		return historial;
	}
	
	// M�todo para a�adir un nuevo campo a la BBDD en la
	// tabla historial:
	public static void insertHistory(String usuario, String ciudad) throws SQLException {
		// Conectar a la BBDD:
		connectDB();
		
		// Comprobar si ya existe la ciudad en el historial
		// del usuario de sesi�n:
		resultSet = stmt.executeQuery("SELECT ciudad FROM history WHERE usuario = '"
								+ usuario + "' AND ciudad = '" + ciudad + "'");

		// Si la consulta no devuelve resultados,
		// la ciudad no est� su historial:
		if (!resultSet.next()) {
			stmt.execute("INSERT INTO history (usuario, ciudad) VALUES ('"
					+ usuario + "', '" + ciudad + "')");
		}
				
		// Cerrar la conexi�n:
		closeConnection();
	}
}