package com.capgemini.pocwebapp.beans;

import org.springframework.stereotype.Component;

@Component
public class LdapUser {
	
	private String uid;
    private String fullName;
    private String lastName;
    private String email;
    
    public LdapUser() {
    }

    public LdapUser(String fullName, String lastName) {
        this.fullName = fullName;
        this.lastName = lastName;
    }

    public LdapUser(String uid, String fullName, String lastName) {
        this.uid = uid;
        this.fullName = fullName;
        this.lastName = lastName;
    }

    
    
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	
	 public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	    public String toString() {
	        return "LdapUser{" +
	                "fullName='" + fullName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", Email ='" + email + '\'' +
	                '}';
	    }
}
