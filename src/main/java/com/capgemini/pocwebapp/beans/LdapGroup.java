package com.capgemini.pocwebapp.beans;

import java.util.HashSet;
import java.util.Set;

import javax.naming.Name;

import org.springframework.stereotype.Component;

@Component
public class LdapGroup {

	private String name;
    private Set<Name> members;
    
    
    public LdapGroup() {
    }
    
    public LdapGroup(String name, Set<Name> members) {
        this.name = name;
        this.members = members;
    }

    public LdapGroup(Name dn, String name, Set<Name> members) {
        this.name = name;
        this.members = members;
    }
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Name> getMembers() {
		return members;
	}
	public void setMembers(Set<Name> members) {
		this.members = members;
	}

	public void addMember(Name member) {
        if (this.members == null){
            this.members = new HashSet<>();
        }
        members.add(member);
    }

    public void removeMember(Name member) {
        members.remove(member);
    }
    
	 @Override
	    public String toString() {
	        return "LdapGroup{" +
	                "name='" + name + '\'' +
	                ", members=" + members +
	                '}';
	    }
    
}
