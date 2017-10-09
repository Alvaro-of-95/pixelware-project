package pixelware.controller;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pixelware.model.User;

@Controller
public class IndexController {
	/* Método para peticines GET con URL "/",
	 * redirigir a index.jsp: */
	@RequestMapping(value={"/", "/inicio"}, method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		view.addObject("user", new User());
		
		return view;
	}
}