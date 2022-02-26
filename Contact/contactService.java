package Contact;

import java.util.Random;
import java.util.Vector;

public class contactService {

	private Vector<contactClass> _contactList = new Vector<contactClass>();
	private int _contactCount = 0;
	
	public int GetContactCount() {
		return _contactCount;
	}
	
	public Vector<contactClass> GetContactList(){
		return _contactList;
	}

	public void AddContact(String id, String fName, String lName, String phone, String addy) {
		contactClass newContact = new contactClass(
				id,
				fName,
				lName,
				phone,
				addy);
		
		_contactList.add(newContact);
		_contactCount++;
	}
	
	public void AddContact(contactClass contact) {
		_contactList.add(contact);
		_contactCount++;
	}
	
	public void DeleteContact(String id) {
		if(id == null || id.length() > 10) {
			throw new IllegalArgumentException("Invalid ID");
			
		}
		
		if(_contactList.isEmpty()) {
			throw new IllegalArgumentException("No contacts to delete");
		}
		
		int indx = -1;
		for (contactClass c : _contactList) {
			if (c.getId() == id) {
				indx = _contactList.indexOf(c);
			}
			
		}
		if (indx == -1) {
			System.out.println("Contact not found.");
			return;
		}
		else{
			_contactList.remove(indx);
			_contactCount--;
			System.out.println("Contact removed from list.");
		}
		
		
	}
	
	private void DeleteContact(contactClass contact) {
		_contactList.remove(contact);
		_contactCount--;
	}
	
	public void UpdateContact(String id, String update, int selection) {
		if(id == null || id.length() > 10 || update == null || selection < 0) {
			throw new IllegalArgumentException("Invalid ID");
		}
		
		if(_contactList.isEmpty()) {
			throw new IllegalArgumentException("No contacts exist in list");
		}
		
		int indx = -1;
		for (contactClass c : _contactList) {
			if (c.getId() == id) {
				indx = _contactList.indexOf(c);
			}
			
		}
		if (indx == -1) {
			System.out.println("Contact not found.");
			return;
		}
		
		contactClass updateContact = _contactList.get(indx);
		
		switch (selection){
		case 1:{
			updateContact.setFirstName(update);
			break;
		}
		case 2:{
			updateContact.setLastName(update);
			break;
		}
		case 3:{
			updateContact.setPhoneNumber(update);
			break;
		}
		case 4:{
			updateContact.setAddress(update);
			break;
		}
		default:{
			System.out.println("Contact not updated, invalid change requested.");
			break;
		}
		}
		
		DeleteContact(_contactList.elementAt(indx));
		AddContact(updateContact);
				
	}
	
	public void UpdateContact(String id, String fName, String lName, String phone, String addy) {
		if(id == null || id.length() > 10) {
			throw new IllegalArgumentException("Invalid ID");
		}
		
		if(_contactList.isEmpty()) {
			throw new IllegalArgumentException("No contacts exist in list");
		}
		
		int indx = -1;
		for (contactClass c : _contactList) {
			if (c.getId() == id) {
				indx = _contactList.indexOf(c);
			}
			
		}
		if (indx == -1) {
			System.out.println("Contact not found.");
			return;
		}
		
		contactClass tempContact = _contactList.get(indx);
		tempContact.setFirstName(fName);
		tempContact.setLastName(lName);
		tempContact.setAddress(addy);
		tempContact.setPhoneNumber(phone);
		
		_contactList.remove(indx);
		_contactList.add(tempContact);
				
	}
	
	public String GenerateUniqueID() {
		// auto generate a random ID for contact
		Random rand = new Random();
		int newID = rand.nextInt(1000000000);
		String uniqueID = Integer.toString(newID);
		
		for (contactClass c : _contactList) {
			while (c.getId() == uniqueID) {
				newID = rand.nextInt(1000000000);
				uniqueID = Integer.toString(newID);
			}
			
		}
		
		System.out.println("ID created: " + uniqueID);
				
		return uniqueID;
	}
	
	
	
}