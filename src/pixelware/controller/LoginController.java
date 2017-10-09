package pixelware.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pixelware.model.User;

@Controller
public class LoginController {
	// Variables para el trabajo con BBDD:
	private static Connection conn = null;
	private static Statement stmt;
	private static ResultSet resultSet;
	
	// Método para crear la conexión a la BBDD:
	@ModelAttribute
	public void connectDB(Model model) {
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
	
	
	/* Método activado por el formulario de index, comprobamos
	 * si el usuario y clave introducidos coincide con alguno
	 * de la BBDD: */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User formUser) {
		// Ejecutar instrucción SELECT:
		try {
			resultSet = stmt.executeQuery("SELECT "
			+ "nombre, clave FROM users");
			
			// Procesar y el ResultSet para obtener la lista de
			// usuarios (solo el nombre y la clave) de la BBDD:
			List<User> usuarios = new ArrayList<>(procesarRegistros(resultSet));
			
			// Para cada objeto de la lista, comprobar si su nombre
			// y clave coinciden con el introducido en el formulario:
			for (User listUser : usuarios) {
				if (formUser.getNombre().equalsIgnoreCase(listUser.getNombre())
						&& formUser.getClave().equalsIgnoreCase(listUser.getClave())) {
					
					// Redirigir a weather.jsp:
					return "weather";
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "index";
		
		// Cerrar la conexión con la BBDD:
		} finally {
			closeConnection();
		}
		
		/* Si se llega a este punto, es porque se ha procesado
		 * correctamente la BBDD pero no se han encontrado
		 * coincidencias: */
		return "index";
	}
	
	
	// Método para cerrar la conexión con la BBDD:
	private static void  closeConnection() {
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
	
	// Método para procesar la consulta Select, transformando cada
	// fila devuelta en un objeto y guardándolos en una lista:
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
	
	
	
	
	// Método para registrar un nuevo usuario:
	//private static void crearUsuario() throws SQLException {
		
	//}
}