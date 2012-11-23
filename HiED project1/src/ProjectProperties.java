import java.util.ResourceBundle;

public class ProjectProperties {
	private String urlAddress;
	private String user;
	private String password;
	
	public ProjectProperties(){
		ResourceBundle rb = ResourceBundle.getBundle("project.properties");

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
