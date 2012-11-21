import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
