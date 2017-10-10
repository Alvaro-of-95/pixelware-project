package pixelware.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {
	/* Método para peticines GET con URL "/",
	 * redirigir a index.jsp: */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		
		// Borramos el atributo de sesión y redirigimos a index:
		request.getSession().removeAttribute("usuario");
		view.setViewName("redirect:/inicio");
		
		return view;
	}
}