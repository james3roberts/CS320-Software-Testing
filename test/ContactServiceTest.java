package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Contact.contactClass;
import Contact.contactService;

class ContactServiceTest {

	
	@Test
	void testAddContactMethod() {
		// create a contact
		contactService contactService = new contactService();
		String testID = contactService.GenerateUniqueID();
		contactClass contact = new contactClass(testID, "Larry", "Joe", "7025554900", "6029 Elm Street");
		// add contact to the list
		contactService.AddContact(contact);
		// confirm contact is in the list, count is not zero
		assertTrue(!contactService.GetContactList().isEmpty());
		assertTrue(contactService
				.GetContactList()
				.elementAt(0)
				.getId()
				.equals(testID));
		assertTrue(contactService.GetContactCount() > 0);
	}
	
	@Test
	void testDeleteContactMethod() {
		contactService contactService = new contactService();
		// create new contact
		contactClass contact = new contactClass("123456", "Larry", "Joe", "7025554900", "6029 Elm Street");
		// try to remove with null id
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.DeleteContact(null);
		});
		// try to remove with id that's too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.DeleteContact("12345678901");
		});
		// try to remove from empty list
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.DeleteContact("1234567890");
		});
		// add the contact
		contactService.AddContact(contact);
		// remove a contact that isn't there
		contactService.DeleteContact("123457");
		// contact list is not empty, count is not zero
		// contact not removed because contact doesn't exist
		assertTrue(!contactService.GetContactList().isEmpty());
		assertTrue(contactService.GetContactCount() != 0);
		// remove correct contact
		contactService.DeleteContact("123456");
		// list is empty, count is zero, contact successfully removed
		assertTrue(contactService.GetContactCount() == 0);
		assertTrue(contactService.GetContactList().isEmpty());
		
	}
	
	@Test
	void testUpdateContactMethodErrors() {
		contactService contactService = new contactService();
		// contact list is empty
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.UpdateContact("123456", "Lawrence", 1);
		});
		
		// create new contact, add to list
		contactClass contact = new contactClass("123456", "Larry", "Joe", "7025554900", "6029 Elm Street");
		contactService.AddContact(contact);
		// check that contact was added
		assertTrue(!contactService.GetContactList().isEmpty());
		
		// id is too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.UpdateContact("12345678901", "Lawrence", 1);
		});
		// id is null
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.UpdateContact(null, "Lawrence", 1);
		});
		// update value is null
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.UpdateContact("123456", null, 1);
		});
		// selection value is less than zero
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.UpdateContact("123456", "Lawrence", -1);
		});
		
		// print "contact not found" to console
		contactService.UpdateContact("123457", "Lawrence", 1);
		
		// print "contact not updated" to console
		contactService.UpdateContact("123456", "Lawrence", 5);
		
	}
	
	@Test
	void testUpdateContactMethod() {
		contactService contactService = new contactService();
		contactClass contact = new contactClass("123456", "Larry", "Joe", "7025554900", "6029 Elm Street");
		contactService.AddContact(contact);
		assertTrue(!contactService.GetContactList().isEmpty());
		
		// update first name
		contactService.UpdateContact("123456", "Lawrence", 1);
		assertTrue(contactService
				.GetContactList()
				.elementAt(0)
				.getFullName()
				.equals("Lawrence Joe"));
		// update last name
		contactService.UpdateContact("123456", "Johnson", 2);
		assertTrue(contactService
				.GetContactList()
				.elementAt(0)
				.getFullName()
				.equals("Lawrence Johnson"));
		// update phone number
		contactService.UpdateContact("123456", "4402255499", 3);
		assertTrue(contactService
				.GetContactList()
				.elementAt(0)
				.getPhoneNumber()
				.equals("4402255499"));
		// update address
		contactService.UpdateContact("123456", "4910 Carson Ave", 4);
		assertTrue(contactService
				.GetContactList()
				.elementAt(0)
				.getAddress()
				.equals("4910 Carson Ave"));
		
		// update first name too long
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			contactService.UpdateContact("123456", "LawrenceJoseph", 1);
		});
				
		// check that list has been updated
		// only one contact in list, check that it's updated by checking name
		assertTrue(contactService.GetContactCount() == 1);
		assertTrue(contactService.GetContactList().elementAt(0)
				.getFullName().equals("Lawrence Johnson"));
				
	}

}
