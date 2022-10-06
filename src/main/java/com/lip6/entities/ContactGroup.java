package com.lip6.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ContactGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupId;
	
	private String groupName;
	
	@ManyToMany(mappedBy="contactGroups")
	private Set <Contact> contacts=new HashSet<Contact>();
	
	public ContactGroup(long groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}
	public ContactGroup( String groupName) {
		super();
		
		this.groupName = groupName;
	}
	public ContactGroup() {
		
	}
	public Set<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	

}
