package com.capgemini.pocwebapp.spring.config.security.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class WebAppAuthenticationDetails extends WebAuthenticationDetails {

   private static final long serialVersionUID = 1L;
   
   private String remoteHost = "";
   private String timeZone;
   private boolean isServiceRequest;
   
	public WebAppAuthenticationDetails(HttpServletRequest request) {
		 super(request);
	        timeZone = request.getParameter("timeZone_id");
	        remoteHost = request.getServerName();
	}

	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * @param remoteHost the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the isServiceRequest
	 */
	public boolean isServiceRequest() {
		return isServiceRequest;
	}

	/**
	 * @param isServiceRequest the isServiceRequest to set
	 */
	public void setServiceRequest(boolean isServiceRequest) {
		this.isServiceRequest = isServiceRequest;
	}

}
