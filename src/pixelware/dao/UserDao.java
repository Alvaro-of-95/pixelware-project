package pixelware.dao;
import java.sql.SQLException;
import java.util.List;
import pixelware.model.History;
import pixelware.model.User;

// Data Access Object (DAO) para User:
public interface UserDao {
	
	boolean searchUser(User user) throws SQLException;
	
	void register(User user) throws SQLException;
	
	List<History> getHistory(User user) throws SQLException;
	
	void insertHistory(String usuario, String ciudad) throws SQLException;
}