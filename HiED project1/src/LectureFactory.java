import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LectureFactory {

	public static Lecture getLecture(Integer id){
		ResultSet rs = null;
		Statement stmt = null; 
		Connection conn = null;
		String type = null;
		String language = null;
		Integer parent_id = null;
		Integer views = null;
		String rec_date = null;
		String pub_date = null;
		String name = null;
		String description = null;
		String slide_titles = null;
		ArrayList<Integer> authors = new ArrayList<Integer>();
		ArrayList<Integer> categories = new ArrayList<Integer>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:odbc:Database");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM lectures " +
					"NATURAL JOIN authors_lectures " +
					"NATURAL JOIN categories_lectures " +
					"WHERE id = " + id);

			rs.next();
			type = rs.getString("type");
			language = rs.getString("language");
			parent_id = rs.getInt("parent_id");
			views = rs.getInt("views");
			rec_date = rs.getString("rec_date");
			pub_date = rs.getString("pub_date");
			name = rs.getString("name");
			description = rs.getString("description");
			slide_titles = rs.getString("slide_titles");
			
			while (rs.next()) {
				Integer temp = rs.getInt("category_id");
				if (!categories.contains(temp)){
					categories.add(temp);
				}
				temp = rs.getInt("author_id");
				if (!authors.contains(temp)){
					authors.add(temp);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Lecture(id, type, language, parent_id, 
				views, rec_date, pub_date, name, description, 
				slide_titles, authors, categories);
	}
}
