package pixelware.controller;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pixelware.model.User;
import pixelware.services.UserService;
import pixelware.config.ApplicationConfig;

@Controller
public class LoginController {
	/* M�todo para peticines GET con URL "/" y "/inicio", comprobamos
	 * si ya hay una sesi�n activa y redirigimos a la aplicaci�n
	 * de tiempo si es as�, para evitar logear con 2 usuarios a la vez: */
	@RequestMapping(value={"/", "/inicio"}, method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		
		if (request.getSession().getAttribute("usuario") == null) {
			view.setViewName("login");
			view.addObject("user", new User());
		} else {
			// Si ya hay un usuario logeado, no le dejamos ir a login:
			view.setViewName("redirect:/tiempo");
		}
		
		return view;
	}
	
	
	/* M�todo activado por el formulario de index, comprobamos
	 * si el usuario y clave introducidos coincide con alguno
	 * de la BBDD: */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User formUser,
							Model model, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		
		try {
			// Contexto jdbc para ejecutar la consulta:
			AbstractApplicationContext context =
					new AnnotationConfigApplicationContext(ApplicationConfig.class);
			UserService service = (UserService) context.getBean("userService");
			
			// Guardar en la variable el resultado de la comprobaci�n:
			boolean existe = service.searchUser(
					new User(formUser.getNombre(), formUser.getClave()));

			context.close();
			
			// Si el m�todo devuelve true es porque el usuario
			// y clave del formulario existen en la BBDD:
			if (existe) {
				request.getSession().setAttribute("usuario", formUser.getNombre());
				view.setViewName("redirect:/tiempo");
				return view;
			}
			
		} catch (SQLException e) {
			// Si ha habido un problema con la conexi�n
			// a la BBDD, mostramos la descripci�n:
			view.setViewName("login");
			model.addAttribute("error",
			"Error en la conexi�n con la Base de datos: " + e.getMessage());
			view.addObject("user", new User());
			return view;
		}
		
		/* Si se llega a este punto, es porque se ha procesado
		 * correctamente la BBDD pero no se han encontrado
		 * coincidencias: */
		view.setViewName("login");
		model.addAttribute("error",
		"<strong>Error: </strong> Usuario y/o contrase�a no v�lido.");
		view.addObject("user", new User());
		return view;
	}
}