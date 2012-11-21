import java.util.ArrayList;

public class Author {
	private Integer id;
	private String gender;
	ArrayList<Integer> lectures;

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
}
