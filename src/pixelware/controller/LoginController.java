package pixelware.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pixelware.model.User;

@Controller
public class LoginController {
	// Variables para el trabajo con BBDD:
	private static Connection conn = null;
	private static Statement stmt;
	private static ResultSet resultSet;
	
	// Método para crear la conexión a la BBDD:
	@ModelAttribute
	public void connectDB() {
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
	
	
	/* Método para peticines GET con URL "/" y "/inicio", comprobamos
	 * si ya hay una sesión activa y redirigimos a la aplicación
	 * de tiempo si es así, para evitar logear con 2 usuarios a la vez: */
	@RequestMapping(value={"/", "/inicio"}, method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		
		if (request.getSession().getAttribute("usuario") == null) {
			view.setViewName("login");
			view.addObject("user", new User());
		} else {
			// Si ya hay un usuario logeado:
			view.setViewName("redirect:/tiempo");
		}
		
		return view;
	}
	
	
	/* Método activado por el formulario de index, comprobamos
	 * si el usuario y clave introducidos coincide con alguno
	 * de la BBDD: */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User formUser,
							Model model, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		
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
					
					// Añadir el atributo de sesión:
					request.getSession().setAttribute("usuario", listUser.getNombre());
					
					// Redirigir a weather.jsp:
					view.setViewName("redirect:/tiempo");
					return view;
				}
			}
			
		} catch (SQLException e) {
			// Si ha habido un problema con la conexión
			// a la BBDD, mostramos la descripción:
			view.setViewName("login");
			model.addAttribute("error",
			"Error en la conexión con la Base de datos: " + e.getMessage());
			view.addObject("user", new User());
			return view;
		
		// Cerrar la conexión con la BBDD:
		} finally {
			closeConnection();
		}
		
		/* Si se llega a este punto, es porque se ha procesado
		 * correctamente la BBDD pero no se han encontrado
		 * coincidencias: */
		view.setViewName("login");
		model.addAttribute("error",
		"<strong>Error: </strong> Usuario y/o contraseña no válido.");
		view.addObject("user", new User());
		return view;
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
			// Acceso a las columnas del registro actual del Resultset:
			nombre = resultSet2.getString(1);
			clave = resultSet2.getString(2);
			
			// Crear Usuario:
			User usuario = new User(nombre, clave);
			usuarios.add(usuario);
		}
		
		return usuarios;
	}
}