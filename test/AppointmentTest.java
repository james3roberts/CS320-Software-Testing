package test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import appointment.Appointment;

public class AppointmentTest {
	//Test the date maker
	@Test
	void testAppConstructors() {
		Calendar c = Calendar.getInstance();
		c.set(2022, 02, 13);
		Date date = c.getTime();
	
		//check that everything works with correct info
		Date newDate = c.getTime();
		Appointment app = new Appointment("1234567890", newDate, "Appointment Description");
		assertTrue(app.GetAppId().contentEquals("1234567890"));
		assertTrue(app.GetAppData().equals(newDate));
		assertTrue(app.GetAppDescription().contentEquals("Appointment Description"));
		
		//Check id length
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			new Appointment("12345678901",date,"Appointment Description");
		});
		//check if for null
		Assertions.assertThrows(IllegalArgumentException.class,()-> {
			new Appointment(null, date, "Appointment Description");
		});
		//check description for null
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Appointment("123456789",date, null);
		});
		//check description length
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Appointment("123456789",date,"abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYX");
		});
		//Check description for null
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Appointment("123456789",date,null);
		});
		//check date for null
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Appointment("123456789",null,"Appointment Description");
		});
		//check for future dates
		date.setTime(0);
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			new Appointment("123456789",date,"Appointment Description");
		});	
	}
	//test the setters
	@Test
	void testSetters() {
		Calendar c = Calendar.getInstance();
		c.set(2022,03,25);
		Date date = c.getTime();
		Date newDate = c.getTime();
		Date badDate = new Date();
		badDate.setTime(10000);
		//make object to see if it updates
		Appointment app = new Appointment("1234567890", date, "Appointment Description");
		//update the description
		app.SetAppDescription("This is the new description");
		//check for description null
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			app.SetAppDescription(null);
		});
		//make sure description is not to long
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			app.SetAppDescription("abcdefghyijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		});
		//Make sure the description changed
		//This may not work
		assertTrue(app.GetAppDescription().contentEquals("Appointment Description"));
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			app.SetAppDescription("Appointment Description");
		});
		//made sure data is not null
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			app.SetAppDate(null);
		});
		//change the date
		app.SetAppDate(newDate);
		//check the changed date
		assertTrue(app.GetAppData().equals(newDate));
	}
	
}