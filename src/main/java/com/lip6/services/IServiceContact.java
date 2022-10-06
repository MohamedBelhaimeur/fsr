package com.lip6.services;

import com.lip6.daos.DAOContact;
import com.lip6.daos.IDAOContact;
import com.lip6.entities.Contact;

public interface IServiceContact {
	

	public void createContact(long id, String fname, String lname, String email);
	
	public void createContact(Contact c) ;
	
	
	public void deleteContact(long id) ;
	
public void updateContact(long id,String firstname, String lastname,String email);
	

public void searchIDContact(long id);

}
