package com.capgemini.pocwebapp.service.api;

import java.util.List;

import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.dao.entity.UserInfo;


public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);
	
	List<User> getallProfile();
	
	void updateList(List<UserInfo> lstUser) throws Exception; 
	
	void uploadUser(List<User> lstUser) throws Exception; 
	

}