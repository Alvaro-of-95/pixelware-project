package pixelware.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pixelware.model.User;

public class Test {
	private static Connection conn = null;
	private static Statement stmt;
	private static ResultSet resultSet;
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			// Conexión a la BBDD mysql (hosting online gratuito):
			String conn_db = "sql11198254";
			String conn_url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/" + conn_db;
			String conn_user = "sql11198254";
			String conn_pass = "fM4bE8BVdf";
			conn = DriverManager.getConnection(conn_url, conn_user, conn_pass);
			stmt = conn.createStatement();
			
			
			// Crear usuario:
			//stmt.execute("INSERT INTO users (nombre, clave) VALUES ('juanjo', 'rr')");
			//System.out.println("Usuario creado.\n");
			
			
			// Ejecutar instrucción SELECT:
			resultSet = stmt.executeQuery("SELECT "
					+ "nombre, clave FROM users");
			
			// Procesar y mostrar el ResultSet:
			List<User> usuarios = procesarRegistros(resultSet);
			System.out.println("Usuarios:");
			usuarios.stream().forEach(System.out::println);
			
		} catch (Exception e) {
			System.out.println("Error de BBDD.");
			e.printStackTrace();
			
		} finally {
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
				System.out.println("Error en el cierre del Servidor.");
				e.printStackTrace();
			}
		}

	}
	
	private static List<User> procesarRegistros(ResultSet resultSet2) throws SQLException {
		List<User> usuarios = new ArrayList<>();
		
		String nombre = null;
		String clave = null;
		
		while (resultSet2.next()) {
			// Acceso a las columnas del registro actual del Resultset
			// (donde se encuentra el cursor) por índice en base 1:
			nombre = resultSet2.getString(1);
			clave = resultSet2.getString(2);
			
			// Crear Usuario:
			User usuario = new User(nombre, clave);
			usuarios.add(usuario);
		}
		
		return usuarios;
	}
	
	//private static void crearUsuario() throws SQLException {
		
	//}
}