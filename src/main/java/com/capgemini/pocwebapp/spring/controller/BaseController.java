package com.capgemini.pocwebapp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.pocwebapp.spring.config.security.UserPrincipal;
import com.capgemini.pocwebapp.view.beans.BaseFormBean;
public abstract class BaseController {

	@Autowired
	public MessageSource messageSource;

	@Autowired
	public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	public AuthenticationTrustResolver authenticationTrustResolver;
	
     /**
	 * @return ModelAndView
	 */
	protected ModelAndView responseDispatcherBlank() {
		return new ModelAndView("blank");
	}

	/**
	 * @return ModelAndView
	 */
	protected ModelAndView responseDispatcherMessage() {
		return new ModelAndView("message");
	}
	
	protected ModelAndView responseDispatcher(BaseFormBean baseFormBean) {
		String FORM_BEAN_RESPONSE = "responseFormBean";
		return new ModelAndView(baseFormBean.getNavAction(),
				FORM_BEAN_RESPONSE, baseFormBean);
	}
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	public String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	public boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   /* JaasGrantedAuthority jaasGrantedAuthority = (JaasGrantedAuthority)(authentication.getAuthorities().toArray()[0]);
		
		UserPrincipal userPrincipal = (UserPrincipal)jaasGrantedAuthority.getPrincipal();
		userPrincipal.setRole(jaasGrantedAuthority.getAuthority());*/
		
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
 }
