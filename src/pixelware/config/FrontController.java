package pixelware.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FrontController extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Configuración para componentes no web:
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ComponentesNoWeb.class};
	}

	// Configuración para componentes web:
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {ComponentesWeb.class};
	}

	// Configuración de todos los patrones de llamada
	// asociados a DispatcherServlet:
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}