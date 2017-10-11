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
public class LoginController {
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
		
		try {
			// Si el método devuelve true es porque el usuario
			// y clave del formulario existen en la BBDD:
			if (BDUtilities.searchUser(formUser.getNombre(), formUser.getClave())) {
				// Añadir el atributo de sesión:
				request.getSession().setAttribute("usuario", formUser.getNombre());
					
				// Redirigir a weather.jsp:
				view.setViewName("redirect:/tiempo");
				return view;
			}
			
		} catch (SQLException e) {
			// Si ha habido un problema con la conexión
			// a la BBDD, mostramos la descripción:
			view.setViewName("login");
			model.addAttribute("error",
			"Error en la conexión con la Base de datos: " + e.getMessage());
			view.addObject("user", new User());
			return view;
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
}