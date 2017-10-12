package pixelware.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.dao.DuplicateKeyException;
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
public class RegisterController {
	/* Método para peticines GET con URL "/registro",
	 * redirigir a index.jsp: */
	@RequestMapping(value="/registro", method=RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		
		if (request.getSession().getAttribute("usuario") == null) {
			view.setViewName("register");
			view.addObject("user", new User());
		} else {
			// Si ya hay un usuario logeado, no le dejamos ir a registro:
			view.setViewName("redirect:/tiempo");
		}
		
		return view;
	}
	
	/* Método activado por el formulario de registro: */
	@RequestMapping(value="/crear", method=RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") User formUser,
							Model model, HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		
		try {
			// Ejecutar el insert:
			AbstractApplicationContext context =
					new AnnotationConfigApplicationContext(ApplicationConfig.class);
			UserService service = (UserService) context.getBean("userService");
			
			// Crear el objeto user y insertarlo en la BBDD con el método del DAO:
			User user = new User(formUser.getNombre(), formUser.getClave(),
					formUser.getEmail(), formUser.getFecha(), formUser.getPais());
			
			service.register(user);

			context.close();
			
		} catch (DuplicateKeyException ex) {
			// Si se intenta registrar un nombre de usuario que ya ha sido
			// utilizado, saltará esta excepción:
			view.setViewName("register");
			model.addAttribute("error", "<strong>Error: "
					+ "</strong>El usuario introducido ya existe.");
			view.addObject("user", new User());
			return view;
			
		} catch (Exception e) {
			// Si es cualquier otro error:
			view.setViewName("register");
		    model.addAttribute("error", "<strong>Error</strong> en la"
						+ "transacción: " + e.getMessage());
			view.addObject("user", new User());
			return view;
		}
		
		// Si ha ido bien, ir a la página de la app ya logeado:
		request.getSession().setAttribute("usuario", formUser.getNombre());
		view.setViewName("redirect:/tiempo");
		return view;
	}
}