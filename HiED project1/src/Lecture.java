import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class Lecture {
	static ProjectProperties p = new ProjectProperties();
	private Integer id;
	private String type;
	private String language;
	private Integer parent_id;
	private Integer views;
	private String rec_date;
	private String pub_date;
	private String name;
	private String description;
	private String slide_titles;
	private ArrayList<Integer> authors;
	private ArrayList<Integer> categories;
	
	public Lecture(Integer id, String type, String language, Integer parent_id, Integer views, 
			String rec_date, String pub_date, String name, String description, String slide_titles,
			ArrayList<Integer> authors, ArrayList<Integer> categories){
		this.id = id;
		this.type = type;
		this.language = language;
		this.parent_id = parent_id;
		this.views = views;
		this.rec_date = rec_date;
		this.pub_date = pub_date;
		this.name = name;
		this.description = description;
		this.slide_titles = slide_titles;
		this.authors = authors;
		this.categories = categories;
	}
	
	public double getJaccard(Lecture lecture){
		double agregation = 0;
		double disjunction = 0;
		
		if (type.equals(lecture.type)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (language.equals(lecture.language)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (parent_id.equals(lecture.parent_id)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (views.equals(lecture.views)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (rec_date.equals(lecture.rec_date)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (rec_date.equals(lecture.rec_date)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (pub_date.equals(lecture.pub_date)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (name.equals(lecture.name)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		disjunction += lecture.authors.size();
		for (Integer author: authors) {
			if (lecture.authors.contains(author)){
				agregation++;
			} else {
				disjunction++;
			}
		}
		
		disjunction += lecture.categories.size();
		for (Integer category: categories) {
			if (lecture.categories.contains(category)){
				agregation++;
			} else {
				disjunction++;
			}
		}
		return agregation / disjunction;
	}
	

	public static ArrayList<Integer> getLecturesIds(){
		ResultSet rs = null;
		Statement stmt = null; 
		Connection conn = null;
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(p.getUrlAddress(), p.getUser(), p.getPassword());
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id FROM lectures");

			while (rs.next())
				idList.add(rs.getInt("id"));

		} catch (Exception e) {
			System.out.println("l1");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("l2");
				e.printStackTrace();
			}
		}
		return idList;
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

	public HashMap<Integer, Double> getJacards(ArrayList<Lecture> lectures){
		HashMap<Integer, Double> jacardsList = new HashMap<Integer, Double>();
		for (Lecture lecture : lectures) {
			double jaccardValue = getJaccard(lecture);
			if (jacardsList.size() < 50) {				
				jacardsList.put(lecture.id, jaccardValue);
			} else if (jaccardValue > getMinimumValue(jacardsList)) {
				jacardsList.remove(getMinimumKey(jacardsList));
				jacardsList.put(lecture.id, jaccardValue);
			}
		}
		
		return jacardsList;
	}
	
	public HashMap<Integer, Double> getJacards(){
		HashMap<Integer, Double> jacardsList = new HashMap<Integer, Double>();
		for (Integer otherLectureId : getLecturesIds()) {
			jacardsList.put(otherLectureId, getJaccard(LectureFactory.getLecture(otherLectureId)));
		}
		return jacardsList;
	}
	

}
