package appointment;
import java.util.Date;
import java.util.Vector;

public class AppointmentService {
	//set up vector to hold all of the requirements
	private Vector <Appointment> appList = new Vector <Appointment>();
	public Vector <Appointment> GetAppointmentList(){
		return appList;
	}
	//Set up ability to add to the vector
	public void AddAppointment(Date date, String description) {
		Date today = new Date();
		if(date == null || date.before(today)) {
			throw new IllegalArgumentException("Invalid appointment Date");
		}
		if (description == null || description.length()>50) {
			throw new IllegalArgumentException("Invalid Description");
		}
	}
	//set up ability to remove from the vector
	public void RemoveAppointment (String id) {
		if(id==null || id.length()>10) {
			throw new IllegalArgumentException("Invalid Id");
		}
	}

}
