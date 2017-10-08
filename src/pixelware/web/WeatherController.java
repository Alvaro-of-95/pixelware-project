package pixelware.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WeatherController {
	/* M�todo para peticines GET con URL "/tiempo",
	 * comprobar si hay usuario logeado y redirigir
	 * a weather.jsp si es as�: */
	@RequestMapping(value={"/tiempo"}, method=RequestMethod.GET)
	public String weather() {
		return "/weather";
	}
}