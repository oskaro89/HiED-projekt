import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Author {
	static ProjectProperties p = new ProjectProperties();
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
			conn = DriverManager.getConnection(p.getUrlAddress(), p.getUser(), p.getPassword());
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
	
	private double getMinimumValue(HashMap<Integer, Double> map){
		double min = Double.MAX_VALUE;
		for (Integer i : map.keySet()) {
			if (min > map.get(i))
				min = map.get(i);
		}
		return min;
	}
	
	private Integer getMinimumKey(HashMap<Integer, Double> map){
		double min = Double.MAX_VALUE;
		Integer key = -1;
		for (Integer i : map.keySet()) {
			if (min > map.get(i)){
				min = map.get(i);
				key = i;
			}
		}
		return key;
	}
	
	public HashMap<Integer, Double> getJacards(ArrayList<Author> authors){
		HashMap<Integer, Double> jacardsList = new HashMap<Integer, Double>();
		for (Author author : authors) {
			double jaccardValue = getJaccard(author);
			if (jacardsList.size() < 50) {				
				jacardsList.put(author.id, jaccardValue);
			} else if (jaccardValue > getMinimumValue(jacardsList)) {
				jacardsList.remove(getMinimumKey(jacardsList));
				jacardsList.put(author.id, jaccardValue);
			}
		}
		
		return jacardsList;
	}
	
	public HashMap<Integer, Double> getJacards(){
		int i = 100;
		HashMap<Integer, Double> jacardsList = new HashMap<Integer, Double>();
		for (Integer otherAuthorId : getAuthorsIds()) {
			i--;
			double jaccardValue = getJaccard(AuthorFactory.getAuthor(otherAuthorId));
			if (jacardsList.size() < 50) {				
				jacardsList.put(otherAuthorId, jaccardValue);
			} else if (jaccardValue > getMinimumValue(jacardsList)) {
				jacardsList.remove(getMinimumKey(jacardsList));
				jacardsList.put(otherAuthorId, jaccardValue);
			}
			
			if (i < 1)
				return jacardsList;
		}
		return jacardsList;
	}
	
	@Override
	public String toString() {
		return id + "\t" + gender;
	}
}
