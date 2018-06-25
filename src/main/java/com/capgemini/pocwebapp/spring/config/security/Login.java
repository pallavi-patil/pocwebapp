package com.capgemini.pocwebapp.spring.config.security;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.service.api.UserService;

@Component
public class Login implements LoginModule {

	private String password;
	private String username;
	private Subject subject;

	@Autowired
	private UserService userService;
	
	public boolean login() throws LoginException {
	
		/*if ((username.equals("admin") && password.equals("adminpass")) || (username.equals("enduser")&&password.equals("enduserpass"))) 
  			{			
				subject.getPrincipals().add(new UserPrincipal(username));
				
           }*/
		User user = userService.findBySSO(username);
		if(user != null && user.getSsoId().equals(username)) {			
			subject.getPrincipals().add(new UserPrincipal(username));
		}
		return true;
  }


        

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> state, Map<String, ?> options) {
		this.subject = subject;

		try {
			NameCallback nameCallback = new NameCallback("prompt");
			PasswordCallback passwordCallback = new PasswordCallback("prompt",
					false);

			callbackHandler.handle(new Callback[] { nameCallback,
					passwordCallback });

			password = new String(passwordCallback.getPassword());
			username = nameCallback.getName();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
