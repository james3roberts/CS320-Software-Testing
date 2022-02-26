package appointment;
import java.util.Date;

public class Appointment {
	private String appId;
	private Date appDate;
	private String appDescription;
	
	//make strings and set up the test
	public Appointment (String id, Date date, String description) {
		if(id==null || id.length()>10) {
			throw new IllegalArgumentException("Invalid Appointment ID"); 
		}
		if(date==null || date.before(new Date())) {
			throw new IllegalArgumentException("Invalid Appointment Date");
		}
		if(description ==null || description.length()>50) {
			throw new IllegalArgumentException("Invalid description");
		}
		appId = id;
		appDate = date;
		appDescription = description;
		
	}
	//set up the getter to return values of the variables
	public String GetAppId() {
		return appId;
	}
	public Date GetAppData() {
		return appDate;
	}
	public String GetAppDescription() {
		return appDescription;
	}
	//set up setter to update the variables.
	public void SetAppDate(Date date) {
		if (date == null || date.before(new Date())) {
			throw new IllegalArgumentException("Invalid Appointment Date");
		}
		appDate = date;
	}
	public void SetAppDescription(String description) {
		if (description == null || description.length()>50) {
			throw new IllegalArgumentException("Invalid Appointment Description");
		}
	}

}
