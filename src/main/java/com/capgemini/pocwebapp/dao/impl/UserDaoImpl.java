package com.capgemini.pocwebapp.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Name;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.pocwebapp.beans.LdapGroup;
import com.capgemini.pocwebapp.beans.LdapUser;
import com.capgemini.pocwebapp.dao.AbstractDao;
import com.capgemini.pocwebapp.dao.api.UserDao;
import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.dao.entity.UserInfo;
import com.capgemini.pocwebapp.dao.entity.UserProfile;
import com.capgemini.pocwebapp.spring.ldap.repository.GroupRepository;
import com.capgemini.pocwebapp.spring.ldap.repository.UserRepository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRepository groupRepository;

	public User findById(int id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<User> users = (List<User>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let
		// them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles()); }
		 */
		return users;
	}

	public void save(User user) {
		saveUser(user);
	}

	public long saveUser(User user) {
		// User existinguser = findUserBySSOID(user.getSsoId());
		getSession().save(user);
		return 1;
	}

	public User findUserBySSOID(String ssoId) {
		// Query query =
		// getSession().getNamedNativeQuery(User.QUERY_FIND_USER_BY_SSOID);
		EntityManager em = Persistence.createEntityManagerFactory("JPA").createEntityManager();
		TypedQuery<User> namedQuery = em.createNamedQuery(User.QUERY_FIND_USER_BY_SSOID, User.class);
		namedQuery.setParameter("ssoId", ssoId.toLowerCase());
		User user = (User) namedQuery.getSingleResult();
		System.out.println(user);
		return user;
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> UserProfileType() {
		org.hibernate.Session session = getSession();
		SQLQuery query = session.createSQLQuery(
				"SELECT s.sso_id , hp.user_profile_id, h.type FROM app_user s INNER JOIN app_user_user_profile hp on s.id = hp.user_id INNER JOIN user_profile h on hp.user_profile_id = h.id");
		List<Object[]> rows = query.list();
		List<User> userList = getListOfUsersWithRoles(rows);
		return userList;
	}

	/**
	 * 
	 * @param rows
	 * @return
	 */
	private List<User> getListOfUsersWithRoles(List<Object[]> rows) {

		Map<String, User> userRoleMap = new HashMap();
		List<User> userList = new ArrayList();
		for (Object[] row : rows) {
			String name = String.valueOf(row[0]);
			String roleId = String.valueOf(row[1]);
			String roleName = String.valueOf(row[2]);
			if (userRoleMap.containsKey(name)) {
				User user = userRoleMap.get(name);
				UserProfile up = new UserProfile();
				up.setId(Integer.parseInt(roleId));
				up.setType(roleName);
				user.getUserProfiles().add(up);
			} else {
				User user = new User();
				user.setFirstName(name);
				UserProfile up = new UserProfile();
				up.setId(Integer.parseInt(roleId));
				up.setType(roleName);
				user.getUserProfiles().add(up);
				userRoleMap.put(name, user);
			}
		}
		return getListOfUsersFromMap(userRoleMap, userList);
	}

	/**
	 * 
	 * @param userRoleMap
	 * @param userList
	 * @return
	 */
	private List<User> getListOfUsersFromMap(Map<String, User> userRoleMap, List<User> userList) {
		for (Map.Entry<String, User> entry : userRoleMap.entrySet()) {
			// System.out.println(entry.getKey() + "/" + entry.getValue());
			userList.add(entry.getValue());
		}
		return userList;
	}

	@Override
	public List<User> findAllLdapUsers() {
		List<LdapUser> ldapUserList = userRepository.findAll();
		List<User> userList = new ArrayList();
		for(LdapUser ldapUser: ldapUserList) {
			User user = new User();
			user.setFirstName(ldapUser.getFullName());
			user.setLastName(ldapUser.getLastName());
			user.setSsoId(ldapUser.getUid());
			user.setEmail(ldapUser.getEmail());
			userList.add(user);
		}
		return userList;
	}

	@Override
	public List<User> findLdapUserGroup() {
		/*List<LdapGroup> ldapGroups = ldapTemplate.search(query().where("objectClass").is("groupOfUniqueNames"),
				new GroupContextMapper());*/
		List<LdapGroup> ldapGroups = groupRepository.findAll();
		Map<String, User> userRoleMap = new HashMap();
		List<User> userList = new ArrayList();
		for (LdapGroup group : ldapGroups) {
			String roleName = group.getName();
			Set<Name> memberSet = group.getMembers();
			for (Name userName : memberSet) {
				String uid = userName.toString();
				String[] splittedString = uid.split(",");
                System.out.println(" Splitted String "+splittedString[0]);
                String uidKey = splittedString[0];
                String[] uidSplit = uidKey.split("=");
                System.out.println(" uidSplit[1] "+uidSplit[1]);
				if (userRoleMap.containsKey(uidSplit[1])) {
					User user = userRoleMap.get(uidSplit[1]);
					UserProfile up = new UserProfile();
					up.setType(roleName);
					user.getUserProfiles().add(up);
				} else {
					User user = new User();
					user.setFirstName(uidSplit[1]);
					UserProfile up = new UserProfile();
					up.setType(roleName);
					user.getUserProfiles().add(up);
					userRoleMap.put(uidSplit[1], user);
				}
			}
		}
		return getListOfUsersFromMap(userRoleMap, userList);
	}

	@Override
	public void updateUserInfos(List<UserInfo>  plstUserInfo) {
		Session session = this.sessionFactory.getCurrentSession();
        if (plstUserInfo != null && plstUserInfo.size() > 0) {
            for (UserInfo entity: plstUserInfo) {                
                session.persist(entity);
            }
        }
	}

	@Override
	public void uploadUsers(List<User> p) {
		Session session = this.sessionFactory.getCurrentSession();
        if (p != null && p.size() > 0) {
            for (User entity: p) {                
                session.persist(entity);
            }
        }
	} 
}
