package com.capgemini.pocwebapp.beans;

import java.io.Serializable;

import com.capgemini.pocwebapp.utils.StringUtils;

public class MessageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4097583840748440771L;
	public static final int CONFIRMATION = 3;
	public static final String CONFIRMATION_HEADER = "Confirmation";
	public static final String CONFIRMATION_STYLE = "message confirmation";

	public static final int ERROR = 1;
	public static final String ERROR_HEADER = "Error(s) Occured";
	public static final String ERROR_STYLE = "message error";

	public static final int INFO = 0;
	public static final String INFO_HEADER = "Information";
	public static final String INFO_STYLE = "message info";

	public static final int WARNING = 2;
	public static final String WARNING_HEADER = "Warning";
	public static final String WARNING_STYLE = "message warning";

	private String message = null;
	private int severity = 0;

	/**
	 * @param severity
	 * @param description
	 */
	public MessageBean(int severity, String description) {
		this(severity, description, null);
	}

	/**
	 * @param severity
	 * @param description
	 * @param arguments
	 */
	public MessageBean(int severity, String description, String[] arguments) {
		this.severity = severity;
		this.message = StringUtils.Trim(description);
		if (arguments != null && arguments.length > 0) {
			this.message = StringUtils.FormatMessage(this.message, arguments);
		}
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return this.severity;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(int severity) {
		this.severity = severity;
	}

}
