import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AuthorFactory {
	
	static ProjectProperties p = new ProjectProperties();
	
	public static Author getAuthor(Integer id){
		ResultSet rs = null;
		Statement stmt = null; 
		Connection conn = null;
		String gender = null;
		ArrayList<Integer> lectures = new ArrayList<Integer>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(p.getUrlAddress(), p.getUser(), p.getPassword());
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM authors a " +
					"LEFT JOIN authors_lectures l " +
					"ON a.id = l.author_id " +
					"WHERE id = " + id);

			rs.next();
			gender = rs.getString("gender");
			lectures.add(rs.getInt("lecture_id"));
			
			while (rs.next())
				lectures.add(rs.getInt("lecture_id"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Author(id, gender, lectures);
	}
}
