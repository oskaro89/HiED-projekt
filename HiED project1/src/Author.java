import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Author {
	private Integer id;
	private String gender;
	ArrayList<Integer> lectures;
	
	public static ArrayList<Integer> getAuthorsIds(){
		ResultSet rs = null;
		Statement stmt = null; 
		Connection conn = null;
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:odbc:Database");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id FROM authors");

			while (rs.next())
				idList.add(rs.getInt("id"));

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
		return idList;
	}

	public Author(Integer id, String gender, ArrayList<Integer> lectures) {
		this.id = id;
		this.gender = gender;
		this.lectures = lectures;
	}
	
	public double getJaccard(Author author){
		double agregation = 0;
		double disjunction = 0;
		
		if (gender.equals(author.gender)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		disjunction += author.lectures.size();
		for (Integer lecture : lectures) {
			if (author.lectures.contains(lecture)){
				agregation++;
			} else {
				disjunction++;
			}
		}
		return agregation / disjunction;
	}
	
	public HashMap<Integer, Double> getJacards(){
		HashMap<Integer, Double> jacardsList = new HashMap<Integer, Double>();
		for (Integer otherAuthorId : getAuthorsIds()) {
			jacardsList.put(otherAuthorId, getJaccard(AuthorFactory.getAuthor(otherAuthorId)));
		}
		return jacardsList;
	}
}
