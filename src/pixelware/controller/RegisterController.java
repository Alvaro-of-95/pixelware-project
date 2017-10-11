package pixelware.controller;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pixelware.model.User;
import pixelware.model.BDUtilities;

@Controller
public class RegisterController {
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
		
		try {
			// Ejecutar el insert:
			BDUtilities.register(formUser.getNombre(), formUser.getClave(),
					formUser.getEmail(), formUser.getFecha(), formUser.getPais());
			
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
			
		}
		
		// Si ha ido bien, ir a la página de la app ya logeado:
		request.getSession().setAttribute("usuario", formUser.getNombre());
		view.setViewName("redirect:/tiempo");
		return view;
	}
}