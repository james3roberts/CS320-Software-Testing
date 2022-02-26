package test;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import appointment.AppointmentService;
public class AppointmentServiceTest {
	//make a date
	Date createADate() {
		Calendar c = Calendar.getInstance();
		c.set(2022,03,25);
		Date date = c.getTime();
		return date;
		}
	//set up for past dates
	Date createBadDate() {
		Date date = new Date();
		date.setTime(1000000);
		return date;
	}
	//test add Appointments
	@Test
	void testConstructors () {
		var appService = new AppointmentService();
		assertTrue(appService.GetAppointmentList().isEmpty());
		appService.AddAppointment(createADate(), "Description");
	}
	@Test
	void TestAddAppointmentMethodNullDate() {
		var appService = new AppointmentService();
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			appService.AddAppointment(null,"Description");
		});
	}
	//Test description to long
	@Test
	void testAddAppointmentToLongDescription() {
		var appService = new AppointmentService();
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			appService.AddAppointment(createADate(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		});
	}
	//test description is good
	@Test
	void testAddAppointmentValidParameters() {
		var appService = new AppointmentService();
		appService.AddAppointment(createADate(), "This is the new description");
	}
	
	//test remove appointment
	@Test
	void testRemoveMethodListEmpty() {
		var appService = new AppointmentService();
		appService.RemoveAppointment("12345678");
	}
	//test id not null
	@Test
	void TestRemoveMethodIdNull() {
		var appService = new AppointmentService();
		appService.AddAppointment(createADate(), "To Remove");
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			appService.RemoveAppointment(null);
		});
	}
	//Test id to long
	@Test
	void testRemoveMethodIdTooLong() {
		var appService = new AppointmentService();
		appService.AddAppointment(createADate(),"To Remove");
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			appService.RemoveAppointment("1234567890123");
		});
	}
	//test remove appointment
	@Test
	void testRemoveMethodAppointmentNotInList() {
		var appService = new AppointmentService();
		appService.AddAppointment(createADate(),"To Remove");
		appService.RemoveAppointment("12345678");
	}
	
}
