package pixelware.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FrontController extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Configuraci�n para componentes no web:
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ComponentesNoWeb.class};
	}

	// Configuraci�n para componentes web:
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {ComponentesWeb.class};
	}

	// Configuraci�n de todos los patrones de llamada
	// asociados a DispatcherServlet:
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}