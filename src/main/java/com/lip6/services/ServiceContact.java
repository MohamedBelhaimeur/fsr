package com.lip6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lip6.daos.DAOContact;
import com.lip6.daos.IDAOContact;
import com.lip6.entities.Contact;

@Service
public class ServiceContact implements IServiceContact {
	
	
	private IDAOContact daoc;
	
	@Autowired
	public ServiceContact (IDAOContact daoc) {
		this.daoc=daoc;
	}
	
	
	public void createContact(long id, String fname, String lname, String email) {
		

		 
		 boolean ok=daoc.addContact(id, fname, lname, email);
		if (ok)
			System.out.println("Contact ajout�!");
		else
			System.out.println("Contact non ajout�!");
		
	}
	
	public void createContact(Contact c) {
		
	
		boolean ok=daoc.addContact(c);
		if (ok)
			System.out.println("Contact ajout�!");
		else
			System.out.println("Contact non ajout�!");
		
	}
	
	
	public void deleteContact(long id) {
		
		
		int ok=daoc.deleteContact(id);
		if (ok!=0)
			System.out.println("Contact suppr�!");
		else
			System.out.println("Contact non suppr�!");
		
	}
	
public void updateContact(long id,String firstname, String lastname,String email) {
		
		
		 boolean ok=daoc.modifyContact(id, firstname, lastname, email);
		if (ok)
			System.out.println("Contact edité!");
		else
			System.out.println("Contact non edité!");
		
	}

public void searchIDContact(long id) {
	

	Contact ok=daoc.getContact(id);
	System.out.println(ok.getIdContact() + ","+ ok.getFirstName()+"," + ok.getLastName()+"," + ok.getEmail());
}

}
