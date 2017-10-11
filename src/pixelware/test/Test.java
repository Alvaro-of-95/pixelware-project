package pixelware.test;
import java.sql.SQLException;
import pixelware.model.BDUtilities;

public class Test {
	public static void main(String[] args) {
		
		try {
			System.out.println(BDUtilities.searchUser("", ""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}