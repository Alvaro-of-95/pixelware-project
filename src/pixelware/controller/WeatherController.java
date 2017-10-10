package pixelware.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pixelware.model.User;

@Controller
public class WeatherController {
	/* M�todo para peticines GET con URL "/tiempo", s�lo se
	 * permitir� entrar si est� activa la sesi�n de usuario,
	 * si no redirigimos al login:*/
	@RequestMapping(value="/tiempo", method=RequestMethod.GET)
	public ModelAndView index(Model model, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		
		if (request.getSession().getAttribute("usuario") == null) {
			// No hay sesi�n de usuario activa:
			view.setViewName("login");
			model.addAttribute("error",
			"<strong>Error: </strong> Debes inicar sesi�n para"
			+ "acceder a la aplicaci�n.");
			view.addObject("user", new User());
			return view;
			
		} else {
			// Recuperamos el nombre de usuario logeado:
			request.getSession().getAttribute("usuario");
			view.setViewName("weather");
			return view;
		}
	}
}