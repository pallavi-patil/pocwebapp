package com.capgemini.pocwebapp.service.api;

import java.util.List;

import com.capgemini.pocwebapp.dao.entity.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
