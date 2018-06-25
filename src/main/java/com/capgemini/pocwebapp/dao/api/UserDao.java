package com.capgemini.pocwebapp.dao.api;

import java.util.List;

import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.dao.entity.UserInfo;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();
	
	List<User> findAllLdapUsers();
	
	List<User> UserProfileType();
	
	List<User> findLdapUserGroup();
	
	void updateUserInfos(List<UserInfo> p);
	
	void uploadUsers(List<User> p);

}

