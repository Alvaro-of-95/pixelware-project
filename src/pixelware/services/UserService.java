package pixelware.services;
import java.sql.SQLException;
import java.util.List;
import pixelware.model.History;
import pixelware.model.User;

public interface UserService {
	
	boolean searchUser(User user) throws SQLException;
	
	void register(User user) throws SQLException;
	
	List<History> getHistory(User user) throws SQLException;
	
	void insertHistory(String usuario, String ciudad) throws SQLException;
}