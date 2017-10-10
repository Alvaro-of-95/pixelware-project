package pixelware.test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.bsd.beans.Category;
import com.bsd.config.ApplicationConfig;
import com.bsd.services.CategoryService;

/* AnnotationConfigApplicationContext registra todos los beans de la aplicaci�n
 * mediante la clase de configuraci�n ApplicationConfig en tiempo de ejecuci�n: */
public class Test {
	public static void main(String[] args) {
		
		AbstractApplicationContext context =
				new AnnotationConfigApplicationContext(
						ApplicationConfig.class);
		
		/* Recuperar dentro de la aplicaci�n Spring el
		 * componente categoryService: */
		CategoryService service = (CategoryService)
				context.getBean("categoryService");
		
		Category category = new Category("Ropa", "Todo tipo de prendas");		
		Category category2 = new Category("Borrar", "Sin descripci�n");

		service.addCategory(category);
		service.addCategory(category2);
		
		// Probar m�todos de negocio:
		System.out.println("\nListado de categor�as iniciales:");
		service.findAll().stream().forEach(System.out::println);
		service.deleteCategory(4);
		category.setCategory_name("Clothes");
		service.editCategory(category, category.getCategory_id());
		
		System.out.println("\nListado de categor�as finales:");
		service.findAll().stream().forEach(System.out::println);

		System.out.println("\nC�digo de la categor�a Accesorios: "
						+ service.getCode("Accesorios"));
		
		System.out.println("\nC�digo de la �ltima categor�a: "
				+ service.getLastCategoryCode() + "\n"
				+ service.findCategory(service.getLastCategoryCode()));
		
		context.close();
	}
}





