import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LectureFactory {
	static ProjectProperties p = new ProjectProperties();

	public static ArrayList<Lecture> getLectures(int count, int from){
		ArrayList<Lecture> result = new ArrayList<Lecture>();
		ResultSet rs = null;
		Statement stmt = null; 
		Connection conn = null;
		Integer id = null;
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
			conn = DriverManager.getConnection(p.getUrlAddress(), p.getUser(), p.getPassword());
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM lectures l " + 
					"LEFT JOIN authors_lectures a " + 
					"ON l.id = a.lecture_id " + 
					"LEFT JOIN categories_lectures c " + 
					"ON l.id = c.lecture_id " + 
					"LIMIT " + count +
					"OFFSET " + from);
			
			rs.next();
			type = rs.getString("type");
			language = rs.getString("language");
			parent_id = rs.getInt("parent_id");
			views = rs.getInt("views");
			rec_date = rs.getString("rec_date");
			pub_date = rs.getString("pub_date");
			name = rs.getString("name");
//			description = rs.getString("description");
//			slide_titles = rs.getString("slide_titles");
			
			rs.next();
			while (!rs.isAfterLast()) {
				id = rs.getInt("id");
				type = rs.getString("type");
				language = rs.getString("language");
				parent_id = rs.getInt("parent_id");
				views = rs.getInt("views");
				rec_date = rs.getString("rec_date");
				pub_date = rs.getString("pub_date");
				name = rs.getString("name");
				
				categories.add(rs.getInt("category_id"));
				authors.add(rs.getInt("author_id"));
				while (rs.next() && id.equals(rs.getInt("id"))) {
					Integer temp = rs.getInt("category_id");
					if (!categories.contains(temp)){
						categories.add(temp);
					}
					temp = rs.getInt("author_id");
					if (!authors.contains(temp)){
						authors.add(temp);
					}	
				}
				
				result.add(new Lecture(id, type, language, parent_id, views, rec_date, pub_date, name, "", "", authors, categories));
				categories = new ArrayList<Integer>();
				authors = new ArrayList<Integer>();
			}

		} catch (Exception e) {
			System.out.println("lf2");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("lf2");
				e.printStackTrace();
			}
		}
		return result;
	}
	
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
			conn = DriverManager.getConnection(p.getUrlAddress(), p.getUser(), p.getPassword());
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM lectures l " + 
					"LEFT JOIN authors_lectures a " + 
					"ON l.id = a.lecture_id " + 
					"LEFT JOIN categories_lectures c " + 
					"ON l.id = c.lecture_id " +
					"WHERE id = " + id);

			rs.next();
			type = rs.getString("type");
			language = rs.getString("language");
			parent_id = rs.getInt("parent_id");
			views = rs.getInt("views");
			rec_date = rs.getString("rec_date");
			pub_date = rs.getString("pub_date");
			name = rs.getString("name");
//			description = rs.getString("description");
//			slide_titles = rs.getString("slide_titles");

			categories.add(rs.getInt("category_id"));
			authors.add(rs.getInt("author_id"));
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
			System.out.println("lf3");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("lf4");
				e.printStackTrace();
			}
		}
		return new Lecture(id, type, language, parent_id, 
				views, rec_date, pub_date, name, description, 
				slide_titles, authors, categories);
	}
}
