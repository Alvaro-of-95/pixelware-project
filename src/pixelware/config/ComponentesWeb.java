package pixelware.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// Clase de configuraci�n para componentes web de Spring:
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"pixelware"})
public class ComponentesWeb extends WebMvcConfigurerAdapter {

	/* Informar al servidor de aplicaciones de que sirva,
	 * los recursos est�ticos en vez de el servlet: */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
	}
	
	/* M�todo para devolver un componente para asociar el
	 * nombre l�gico de las vistas con los ficheros f�sicos: */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/vistas/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		
		return resolver;
	}
}