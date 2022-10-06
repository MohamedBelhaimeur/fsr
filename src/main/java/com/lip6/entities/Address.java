package com.lip6.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_address;
	
	
	private String Street;
	private String City;
	private String zip;
	private String Country;
	
	@OneToOne(mappedBy="address")
	private Contact contact;

	
	public Contact getContact() {
		return contact;
	}




	public void setContact(Contact contact) {
		this.contact = contact;
	}




	public Address(){
	}
	


	
	public Address(String street, String city, String zip, String country) {
	
		this.City=city;
		this.zip = zip;
		this.Country = country;
		this.Street=street;
	}
	
	public Address(String street, String city, String zip) {
	
		this.City=city;
		this.zip = zip;

		this.Street=street;
	}




	public long getId() {
		return id_address;
	}




	public void setId(long id) {
		this.id_address = id;
	}




	public String getStreet() {
		return Street;
	}




	public void setStreet(String street) {
		Street = street;
	}




	public String getCity() {
		return City;
	}




	public void setCity(String city) {
		City = city;
	}




	public String getZip() {
		return zip;
	}




	public void setZip(String zip) {
		this.zip = zip;
	}




	public String getCountry() {
		return Country;
	}




	public void setCountry(String country) {
		Country = country;
	}




}
