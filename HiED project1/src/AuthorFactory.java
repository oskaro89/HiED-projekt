import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AuthorFactory {
	
	static ProjectProperties p = new ProjectProperties();
	
	/**
	 * Metoda zawraca liste autorów (iloœæ zale¿na od parametrów)
	 * @param count - liczba rekordów
	 * @param from - pocz¹tkowy rekord
	 * @return lista autorów
	 */
	public static ArrayList<Author> getAuthors(int count, int from){
		ArrayList<Author> result = new ArrayList<Author>();
		ResultSet rs = null;
		Statement stmt = null; 
		Connection conn = null;
		Integer id = null;
		String gender = null;
		ArrayList<Integer> lectures = new ArrayList<Integer>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(p.getUrlAddress(), p.getUser(), p.getPassword());
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM authors a " +
					"LEFT JOIN authors_lectures l " +
					"ON a.id = l.author_id " +
					"LIMIT " + count +
					"OFFSET " + from);

			rs.next();
			while (!rs.isAfterLast()) {
				id = rs.getInt("id");
				gender = rs.getString("gender");

				lectures.add(rs.getInt("lecture_id"));
				while (rs.next() && id.equals(rs.getInt("id"))) {
					lectures.add(rs.getInt("lecture_id"));	
				}
				
				result.add(new Author(id, gender, lectures));
				lectures = new ArrayList<Integer>();
			}

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
		return result;
	}
	
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
