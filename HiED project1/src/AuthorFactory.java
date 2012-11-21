import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AuthorFactory {
	
	public static Author getAuthor(Integer id){
		ResultSet rs = null;
		Statement stmt = null; 
		Connection conn = null;
		String gender = null;
		ArrayList<Integer> lectures = new ArrayList<Integer>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:odbc:Database");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM authors " +
					"NATURAL JOIN authors_lectures" +
					"WHERE id = " + id);

			rs.next();
			gender = rs.getString("gender");
			
			while (rs.next())
				lectures.add(rs.getInt("lecture_id"));

		} catch (Exception e) {
			System.out.println(e);
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
