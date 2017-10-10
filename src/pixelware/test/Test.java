package pixelware.test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.bsd.beans.Category;
import com.bsd.config.ApplicationConfig;
import com.bsd.services.CategoryService;

/* AnnotationConfigApplicationContext registra todos los beans de la aplicación
 * mediante la clase de configuración ApplicationConfig en tiempo de ejecución: */
public class Test {
	public static void main(String[] args) {
		
		AbstractApplicationContext context =
				new AnnotationConfigApplicationContext(
						ApplicationConfig.class);
		
		/* Recuperar dentro de la aplicación Spring el
		 * componente categoryService: */
		CategoryService service = (CategoryService)
				context.getBean("categoryService");
		
		Category category = new Category("Ropa", "Todo tipo de prendas");		
		Category category2 = new Category("Borrar", "Sin descripción");

		service.addCategory(category);
		service.addCategory(category2);
		
		// Probar métodos de negocio:
		System.out.println("\nListado de categorías iniciales:");
		service.findAll().stream().forEach(System.out::println);
		service.deleteCategory(4);
		category.setCategory_name("Clothes");
		service.editCategory(category, category.getCategory_id());
		
		System.out.println("\nListado de categorías finales:");
		service.findAll().stream().forEach(System.out::println);

		System.out.println("\nCódigo de la categoría Accesorios: "
						+ service.getCode("Accesorios"));
		
		System.out.println("\nCódigo de la última categoría: "
				+ service.getLastCategoryCode() + "\n"
				+ service.findCategory(service.getLastCategoryCode()));
		
		context.close();
	}
}





