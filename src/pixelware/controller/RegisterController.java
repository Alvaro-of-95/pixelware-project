package pixelware.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pixelware.model.User;

@Controller
public class RegisterController {
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
	
	/* Método para peticines GET con URL "/registro",
	 * redirigir a index.jsp: */
	@RequestMapping(value="/registro", method=RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView view = new ModelAndView();
		view.setViewName("register");
		view.addObject("user", new User());
		
		return view;
	}
	
	/* Método activado por el formulario de registro: */
	@RequestMapping(value="/crear", method=RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") User formUser,
							Model model, HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		
		// Ejecutar el insert:
		try {
			stmt.execute("INSERT INTO users (nombre, clave,"
				+ " email, fecha, pais) VALUES ('" + formUser.getNombre() + "', "
				+ "'" + formUser.getClave() + "', '" + formUser.getEmail() + "',"
				+ " '" + formUser.getFecha() + "', '" + formUser.getPais() + "')");
			
		} catch (SQLException e) {
			view.setViewName("register");
			
			/* 1062 es el código de error de MySQL al insertar clave primaria
			 * duplicada (Nombre es el campo clave), si se intenta introducir
			 * un usuario que ya existe mostramos ese error: */
			if (e.getErrorCode() == 1062) {
				model.addAttribute("error", "<strong>Error: "
						+ "</strong>El usuario introducido ya existe.");
				System.out.println(e.getMessage());
		        
		    } else {
		    	// Si es otro tipo de error, mostramos la descripción:
				model.addAttribute("error", "<strong>Error</strong> en la"
									+ "transacción: " + e.getMessage());
		    }
			
			view.addObject("user", new User());
			return view;
			
		} finally {
			closeConnection();
		}
		
		// Si ha ido bien, ir a la página de la app ya logeado:
		request.getSession().setAttribute("usuario", formUser.getNombre());
		view.setViewName("redirect:/tiempo");
		return view;
	}
}


