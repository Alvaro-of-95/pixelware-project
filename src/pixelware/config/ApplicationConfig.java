package pixelware.config;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = {"pixelware"},
excludeFilters={@Filter(type=FilterType.ANNOTATION, value=Configuration.class)})
public class ApplicationConfig {
	
	/* Método que devuelve un bean DataSource
	 * con la conexión a la BBDD: */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
		managerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		managerDataSource.setUrl("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11198254");
		managerDataSource.setUsername("sql11198254");
		managerDataSource.setPassword("fM4bE8BVdf");
		
		return managerDataSource;
	}
	
	/* Método que devuelve un bean JdbcTemplate asociado al
	 * DataSource para trabajar directamente con la BBDD: */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
}