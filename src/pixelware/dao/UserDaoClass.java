package pixelware.dao;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pixelware.model.History;
import pixelware.model.User;

/* Configurar como componente de tipo Repository (repositorio en la
 * capa de datos de la aplicaci�n). Por si hubiese m�s de una
 * implementaci�n del DAO, asignamos como id el valor categoryDao
 * @Qualifier: */
@Repository
@Qualifier("userDao")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserDaoClass implements UserDao {

	/* Inyectar el componente de JdbcTemplate definido
	 * en ApplicationConfig, para trabajo con BBDD: */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	// M�todo para comprobar si el usuario existe en la BBDD:
	@Override
	public boolean searchUser(User user) throws SQLException {
		
		boolean existe = true;
		String query = "SELECT * FROM users WHERE nombre = ? AND clave = ?";
		
		try {
			jdbcTemplate.queryForObject(
				query, new Object[] { user.getNombre(), user.getClave() },
				new BeanPropertyRowMapper(User.class));
		
		/* queryForObject s�lo puede recibir un resultado, la consulta dada
		 * puede dar 1 (si existe el usuario) o 0 (si no existe), si no existe
		 * saltar� esta excepci�n por no devolver resultados: */
		} catch (EmptyResultDataAccessException e) {
			existe = false;
		}
		
		return existe;
	}

	
	// M�todo para registrar un usuario:
	@Override
	public void register(User user) throws SQLException {
		jdbcTemplate.update("INSERT INTO users (nombre, clave,"
				+ " email, fecha, pais) VALUES (?, ?, ?, ?, ?)",
				user.getNombre(), user.getClave(), user.getEmail(),
				user.getFecha(), user.getPais());
	}

	
	// M�todo para cargar el historal al cargar la p�gina de tiempo:
	@Override
	public List<History> getHistory(User user) throws SQLException {
		
		String query = "SELECT ciudad FROM history WHERE usuario = ?"
							+ " ORDER BY id DESC LIMIT 10";
		
		List<History> historial = jdbcTemplate.query(
				query,  new Object[] { user.getNombre() },
				new BeanPropertyRowMapper(History.class));

		return historial;
	}

	
	// M�todo para a�adir un nuevo campo a la BBDD en la tabla historial:
	@Override
	public void insertHistory(String usuario, String ciudad) throws SQLException {
		
		String query = "SELECT ciudad FROM history WHERE usuario = ? AND ciudad = ?";
		
		try {
			jdbcTemplate.queryForObject(
				query, new Object[] { usuario, ciudad },
				new BeanPropertyRowMapper(History.class));
		
		/* Si la consulta no devuelve resultados, entonces la ciudad
		 * no existe a�n en el historial y procedemos ainsertarla: */
		} catch (EmptyResultDataAccessException e) {
			jdbcTemplate.update("INSERT INTO history (usuario, ciudad)"
						+ " VALUES (?, ?)", usuario, ciudad);
		}
	}
}