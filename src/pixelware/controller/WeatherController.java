package pixelware.controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pixelware.model.User;
import pixelware.services.UserService;
import pixelware.config.ApplicationConfig;
import pixelware.model.History;

@Controller
public class WeatherController {
	/* Método para peticines GET con URL "/tiempo", sólo se
	 * permitirá entrar si está activa la sesión de usuario,
	 * si no redirigimos al login:*/
	@RequestMapping(value="/tiempo", method=RequestMethod.GET)
	public ModelAndView index(Model model, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		String usuario = (String) request.getSession().getAttribute("usuario");
		
		if (usuario == null) {
			// No hay sesión de usuario activa:
			view.setViewName("login");
			model.addAttribute("error",
			"<strong>Error: </strong> Debes inicar sesión para"
			+ " acceder a la aplicación.");
			view.addObject("user", new User());
			return view;
			
		} else {
			// Recuperamos el nombre de usuario logeado:
			request.getSession().getAttribute("usuario");
			view.setViewName("weather");
			
			// Recuperamos el historial y lo añadimos:
			try {
				AbstractApplicationContext context =
						new AnnotationConfigApplicationContext(ApplicationConfig.class);
				UserService service = (UserService) context.getBean("userService");
				
				List<History> historial = 
						new ArrayList<History>(service.getHistory(new User(usuario)));

				context.close();

				view.addObject("history", historial);
				
			} catch (Exception e) {
				// Si se produce un error metemos una lista vacía,
				// si no daría error en la vista:
				List<History> historial = new ArrayList<History>();
				view.addObject("history", historial);
			}
			
			return view;
		}
	}
	
	/* Método para el historal: Cada vez que se realiza una búsqueda
	 * de tiempo sobre una ciudad, desde AngularJS se manda una
	 * petición con el nombre de la ciudad introducida que recibimos
	 * aquí para guardarla en la BBDD: */
	@RequestMapping(value = "/historial", method=RequestMethod.POST,
								consumes="text/html")
	public void historial (@RequestBody String ciudad,
					HttpServletRequest request) throws SQLException {
		
		String usuario = (String) request.getSession().getAttribute("usuario");
		
		// Ejecutar el insert:
		AbstractApplicationContext context =
				new AnnotationConfigApplicationContext(ApplicationConfig.class);
		UserService service = (UserService) context.getBean("userService");
		
		service.insertHistory(usuario, ciudad);

		context.close();
	}
}











