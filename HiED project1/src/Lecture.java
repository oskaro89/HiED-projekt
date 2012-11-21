import java.util.ArrayList;


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
	

}
