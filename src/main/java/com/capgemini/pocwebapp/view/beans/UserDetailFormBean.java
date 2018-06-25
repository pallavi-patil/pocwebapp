package com.capgemini.pocwebapp.view.beans;

import java.util.List;

public class UserDetailFormBean extends BaseFormBean {

	private static final long serialVersionUID = 1L;
	private int isNew;
	private String searchValue = null;
	private boolean showInactive;
	private List<UserFormBean> userList = null;
	/**
	 * @return the isNew
	 */
	public int getIsNew() {
		return isNew;
	}
	/**
	 * @param isNew the isNew to set
	 */
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}
	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	/**
	 * @return the showInactive
	 */
	public boolean isShowInactive() {
		return showInactive;
	}
	/**
	 * @param showInactive the showInactive to set
	 */
	public void setShowInactive(boolean showInactive) {
		this.showInactive = showInactive;
	}
	/**
	 * @return the userList
	 */
	public List<UserFormBean> getUserList() {
		return userList;
	}
	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<UserFormBean> userList) {
		this.userList = userList;
	}
}
