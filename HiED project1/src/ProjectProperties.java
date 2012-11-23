import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ProjectProperties {
	private String urlAddress;
	private String user;
	private String password;
	
	public ProjectProperties(){
		PropertyResourceBundle rb = null;
		try {
			rb = new PropertyResourceBundle(new FileInputStream("resources/project.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		urlAddress = rb.getString("urlAddress");
		user = rb.getString("user");
		password = rb.getString("password");
	}
	
	public String getUrlAddress() {
		return urlAddress;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
