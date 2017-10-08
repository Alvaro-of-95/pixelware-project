package pixelware.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	/* Método para peticines GET con URL "/" o "/login",
	 * redirigir a index.jsp: */
	@RequestMapping(value={"/", "/login"}, method=RequestMethod.GET)
	public String login() {
		return "/login";
	}
}