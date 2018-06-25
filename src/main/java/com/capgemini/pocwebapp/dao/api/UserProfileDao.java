package com.capgemini.pocwebapp.dao.api;

import java.util.List;

import com.capgemini.pocwebapp.dao.entity.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
