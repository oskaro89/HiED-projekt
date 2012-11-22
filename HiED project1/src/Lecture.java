import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class Lecture {
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
		
		if (rec_date.equals(lecture.pub_date)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (rec_date.equals(lecture.name)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (rec_date.equals(lecture.description)){
			agregation++;
			disjunction++;
		} else {
			disjunction += 2;
		}
		
		if (rec_date.equals(lecture.slide_titles)){
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
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/videolectures","damian",",[psql].");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT id FROM lectures");

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

	
	public HashMap<Integer, Double> getJacards(){
		HashMap<Integer, Double> jacardsList = new HashMap<Integer, Double>();
		for (Integer otherLectureId : getLecturesIds()) {
			jacardsList.put(otherLectureId, getJaccard(LectureFactory.getLecture(otherLectureId)));
		}
		return jacardsList;
	}
	

}
