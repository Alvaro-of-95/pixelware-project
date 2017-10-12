package pixelware.services;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pixelware.dao.UserDao;
import pixelware.model.History;
import pixelware.model.User;

@Service("userService")
public class UserServiceClass implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public boolean searchUser(User user) throws SQLException {
		return dao.searchUser(user);
	}

	@Override
	public void register(User user) throws SQLException {
		dao.register(user);
	}

	@Override
	public List<History> getHistory(User user) throws SQLException {
		return dao.getHistory(user);
	}

	@Override
	public void insertHistory(String usuario, String ciudad) throws SQLException {
		dao.insertHistory(usuario, ciudad);
	}
}