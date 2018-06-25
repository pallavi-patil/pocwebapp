package com.capgemini.pocwebapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pocwebapp.beans.LdapUser;
import com.capgemini.pocwebapp.dao.api.UserDao;
import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.dao.entity.UserInfo;
import com.capgemini.pocwebapp.service.api.UserService;
import com.capgemini.pocwebapp.spring.ldap.repository.UserRepository;
import com.capgemini.pocwebapp.utils.AuthenticationConstants;

@Service("userService")
@Transactional
@PropertySource("classpath:/config/ldap/ldap_details.properties")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private Environment env;
	
	@Autowired
	private UserRepository userRepository;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

	@Transactional
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String authentication = env.getRequiredProperty(AuthenticationConstants.AUTHENTICATION);
		if (authentication.equalsIgnoreCase(AuthenticationConstants.LDAP_AUTH)) {
			LdapUser p = new LdapUser();
			p.setFullName(user.getFirstName());
			p.setLastName(user.getLastName());
			p.setUid(user.getSsoId());
			userRepository.create(p);
		}
		else {			
			dao.save(user);
		}
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with proper
	 * values within transaction. It will be updated in db once transaction ends.
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setSsoId(user.getSsoId());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		String authentication = env.getRequiredProperty(AuthenticationConstants.AUTHENTICATION);
		if (authentication.equalsIgnoreCase(AuthenticationConstants.LDAP_AUTH)) {
			return dao.findAllLdapUsers();
		} else {

			return dao.findAllUsers();
		}
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public List<User> getallProfile() {
		String authentication = env.getRequiredProperty(AuthenticationConstants.AUTHENTICATION);
		if (authentication.equalsIgnoreCase(AuthenticationConstants.LDAP_AUTH)) {
			return dao.findLdapUserGroup();
		} else {
			return dao.UserProfileType();
		}
	}

	@Override
	@Transactional
	public void updateList(List<UserInfo> p) throws Exception {
		dao.updateUserInfos(p);
		
	}

	@Override
	public void uploadUser(List<User> lstUser) throws Exception {
		dao.uploadUsers(lstUser);		
	} 
}
