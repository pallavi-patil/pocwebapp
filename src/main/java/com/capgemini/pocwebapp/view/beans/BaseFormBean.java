package com.capgemini.pocwebapp.view.beans;

import java.io.Serializable;


public abstract class BaseFormBean implements Serializable{

	private static final long serialVersionUID = -4121643298291677399L;
	private String navAction = null;
	private String navEvent = null;
	private boolean newRecord;
	/**
	 * @return the navAction
	 */
	public String getNavAction() {
		return navAction;
	}
	/**
	 * @param navAction the navAction to set
	 */
	public void setNavAction(String navAction) {
		this.navAction = navAction;
	}
	/**
	 * @return the navEvent
	 */
	public String getNavEvent() {
		return navEvent;
	}
	/**
	 * @param navEvent the navEvent to set
	 */
	public void setNavEvent(String navEvent) {
		this.navEvent = navEvent;
	}
	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}
	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
}
