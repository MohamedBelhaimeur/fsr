package com.lip6.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.entities.Messages;
import com.lip6.entities.PhoneNumber;
import com.lip6.utils.JpaUtil;

public class DAOContact implements IDAOContact {

	/**
	 * Rajoute un contact dans la base de donnees.
	 * 
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return renvoit le nouveau contact
	 */
	@Override
	public  boolean addContact(long idContact, String firstname, String lastname, String email) {

		//Avant l'utilisation de classe JpaUtil	
		//EntityManagerFactory emf=Persistence.createEntityManagerFactory("projetJPA");
		
		//1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
		
	    boolean success=false;
	    System.out.println(firstname);
		try {
			 
	    EntityManager em=JpaUtil.getEmf().createEntityManager();
	    System.out.println(firstname+"3");
		// 2 : Ouverture transaction 
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		Contact contact = new Contact("Blanc", "Xavier", "Xavier.Blanc@gmail.com");
		Address address=new Address("15 rue de la Paix", "Paris", "75009");
		PhoneNumber phone1=new PhoneNumber("0625086509");
		PhoneNumber phone2=new PhoneNumber("0625086510");
		ContactGroup group1= new ContactGroup("les bg");
		phone1.setContact(contact);
		phone2.setContact(contact);
		contact.getPhones().add(phone1);
		contact.getPhones().add(phone2);
		contact.setAddress(address);
		address.setContact(contact);
		
		group1.getContacts().add(contact);
		
		contact.getContactGroups().add(group1);
		
		em.persist(contact);
		
		
		
		// 5 : Fermeture transaction 
		tx.commit();
		
		
		 
		// 6 : Fermeture de l'EntityManager et de unit� de travail JPA 
		em.close();
		  System.out.println(firstname);
		// 7: Attention important, cette action ne doit s'executer qu'une seule fois et non pas à chaque instantiation du ContactDAO
		//Donc, pense bien à ce qu'elle soit la dernière action de votre application
		//JpaUtil.close();	
		
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
	}

	
	public  boolean addContact(Contact c) {

		//Avant l'utilisation de classe JpaUtil	
		//EntityManagerFactory emf=Persistence.createEntityManagerFactory("projetJPA");
		
		//1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
		
	    boolean success=false;
	  
		try {
			 
	    EntityManager em=JpaUtil.getEmf().createEntityManager();
	 
		// 2 : Ouverture transaction 
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		
		
		em.persist(c);
		
		
		
		// 5 : Fermeture transaction 
		tx.commit();
		
		
		 
		// 6 : Fermeture de l'EntityManager et de unit� de travail JPA 
		em.close();
	
		// 7: Attention important, cette action ne doit s'executer qu'une seule fois et non pas à chaque instantiation du ContactDAO
		//Donc, pense bien à ce qu'elle soit la dernière action de votre application
		//JpaUtil.close();	
		
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
	}
	/**
	 * Suppresion d'un contact a partir de son identifiant
	 * 
	 * @param id
	 * @return vrai si la suppression a bien ete effectuee
	 */
	@Override
	public int deleteContact(long id) {
		int success = 0;
		System.out.println(id);
		//Avant l'utilisation de classe JpaUtil	
		//EntityManagerFactory emf=Persistence.createEntityManagerFactory("projetJPA");
		
		//1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
		
		try {
			 
	    EntityManager em=JpaUtil.getEmf().createEntityManager();
	    
		// 2 : Ouverture transaction 
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		Contact c=em.find(Contact.class, id);
		
		em.remove(c);
		
		// 5 : Fermeture transaction 
		tx.commit();
		
		
		 
		// 6 : Fermeture de l'EntityManager et de unit� de travail JPA 
		em.close();
		
	
		success=1;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	

		return success;
	}

	/**
	 * Recuperation d'un contact a partir de son identifiant
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Contact getContact(long id) {
	System.out.println(id);
		//Avant l'utilisation de classe JpaUtil	
		//EntityManagerFactory emf=Persistence.createEntityManagerFactory("projetJPA");
		
		//1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
		
		try {
		EntityManager em=JpaUtil.getEmf().createEntityManager();
		EntityTransaction tx =  em.getTransaction();
		Contact c = null;
		tx.begin();
		
		/*
		 // NativeQuery SQL
		final String nativeQuery="SELECT * FROM contact WHERE idContact=?";
		
		List<Contact> listcontacts= em.createNativeQuery(nativeQuery,Contact.class).setParameter(1, id).getResultList();
		
		c= listcontacts.get(0);
		*/
		
		//JPQL + FETCH 
		String requete="Select c FROM Contact c JOIN FETCH c.phones WHERE c.idContact=:id" ;
		TypedQuery<Contact> query=em.createQuery(requete,Contact.class);
		query.setParameter("id", id);
		List<Contact> listcontacts= query.getResultList();
			c=listcontacts.get(0);
			System.out.println(c);
			c.getPhones().forEach(x->System.out.println(x.getPhoneNumber()));
			
		
	   /*   
	    //find 
		Contact c=em.find(Contact.class, id);	
		
		*/
		tx.commit();
		em.close();
		
	
		return c;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
	return null;
	}

	/**
	 * Methode qui modifie les coordonees d'un contact
	 * 
	 * @param id
	 * @param firstname
	 * @param alstname
	 * @param email
	 * @return
	 */
	@Override
	public boolean modifyContact(long id, String firstname, String lastname, String email) {
		boolean success = false;
	
		try {
			
			 EntityManager em=JpaUtil.getEmf().createEntityManager();
			    
				// 2 : Ouverture transaction 
				EntityTransaction tx =  em.getTransaction();
				tx.begin();
				
				Contact c=em.find(Contact.class, id);
				
				c.setFirstName(firstname);
				c.setLastName(lastname);
				c.setEmail(email);
				c.setAddress(null);
					
				
				
				
				
				// 5 : Fermeture transaction 
				tx.commit();
				
				
				 
				// 6 : Fermeture de l'EntityManager et de unit� de travail JPA 
				em.close();
			success = true;
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * Renvoit la liste des contacts correspondant au prenom firrstname
	 * 
	 * @param firstname
	 * @return
	 */
	@Override
	public ArrayList<Contact> getContactByFirstName(String firstname) {

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE firstname = " + "'" + firstname + "'");

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setIdContact(Long.parseLong(rec.getString("id")));
				contact.setFirstName(rec.getString("firstname"));
				contact.setLastName(rec.getString("lastname"));
				contact.setEmail(rec.getString("email"));

				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant au nom lastname
	 * 
	 * @param lastname
	 * @return
	 */
	@Override
	public ArrayList<Contact> getContactByLastName(String lastname) {

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE lastname = " + "'" + lastname + "'");

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setIdContact(Long.parseLong(rec.getString("id")));
				contact.setFirstName(rec.getString("firstname"));
				contact.setLastName(rec.getString("lastname"));
				contact.setEmail(rec.getString("email"));
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant a l'email email
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public ArrayList<Contact> getContactByEmail(String email) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try {
			Class.forName(Messages.getString("driver"));
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"),
					Messages.getString("password"));
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE email = " + "'" + email + "'");

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setIdContact(Long.parseLong(rec.getString("id")));
				contact.setFirstName(rec.getString("firstname"));
				contact.setLastName(rec.getString("lastname"));
				contact.setEmail(rec.getString("email"));
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

}
