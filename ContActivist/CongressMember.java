/**
 * This class represents a single representative or senator
 * in Congress, and contains information about them including:
 * name, phone number, and optionally an email address.
 */
public class CongressMember {
	
	// Instance Variables
	String name;
	String stateCode;
	String phone;
	String email;

	//Constructor method
	public CongressMember(String name, String stateCode, String phone, String email) {
		this.name = name;
		this.stateCode = stateCode;
		this.phone = phone;
		if (!email.equals("")) {
			this.email = email;
		} else {
			this.email = "NO EMAIL";
		}
		
	}
	
	//Class methods
	public String getName() {
		return name;
	}
	
	public String getState() {
		return stateCode;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
}
