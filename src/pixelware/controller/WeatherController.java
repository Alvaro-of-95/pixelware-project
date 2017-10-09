package pixelware.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WeatherController {
	/* Este método lo dejamos para pruebas, lo acabaremos borrando: */
	@RequestMapping(value={"/tiempo"}, method=RequestMethod.GET)
	public String weather() {
		return "weather";
	}
}