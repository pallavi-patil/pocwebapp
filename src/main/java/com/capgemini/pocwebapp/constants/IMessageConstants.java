package com.capgemini.pocwebapp.constants;

import com.capgemini.pocwebapp.beans.MessageBean;

public interface IMessageConstants {

	// Message[ message details]
	String MSG_CREATE = "{0} details created successfully";
	String MSG_DELETE = "{0} details deleted successfully";
	String MSG_DELINK = "{0} delinked successfully";
	String MSG_APPROVE = "{0} approved successfully";
	String MSG_RELEASE = "{0} released successfully";
	String MSG_AMEND = "{0} amended successfully";
	String MSG_REMOVE = "{0} details removed successfully";
	String MSG_REJECT = "{0} rejected successfully";
	String MSG_SAVE = "{0} details saved successfully";
	String MSG_SAVE_FAILED = "Error occured while saving {0} details";
	String MSG_MODIFIED = "Status of this {0} has already been modified by other user.";
	String MSG_ERROR_DELETE = "Error occured while deleting selected {0}.";
	String MSG_ERROR_PREFIX = " - Error(s) occured: ";
	String MSG_EXIST = "{0} name already exists. Please enter a different name";
	String MSG_PRICE_EXIST = "{0} already exists for selected Price Level and Price Date.";
	String MSG_HOST_NAME_RETRIEVE_ERROR = "Error occured while getting the hostName from SystemProperty";
	String MSG_HTTP_REQUEST_LOG = "HTTP Request : SessionID ={0},URL ={1}, URI ={2}, EventName ={3}, METHOD ={4}, SessionValid ={5}, Creation ={6}, LastAccessed ={7}, SessionAttributeNames =({8})";
	String MSG_LAST_MODIFIED_DATA_NOT_UPDATED = "Expected columns LAST_MODIFIED_BY and LAST_MODIFIED_DT are not set in the Query ";
	String MSG_SUBSCRIBE = "{0} subscribed successfully";
	String MSG_GENEVA_SAVE = "{0} Record Loaded Successfully";
	String MSG_GENEVA_FAIL = "Geneva Ticker loading failed. Geneva Ticker {0} already exists in Geneva. Please contact System Administrator for further assistance.";
	String MSG_GENEVA_EXISTS = "{0} already updated in Geneva for this Series";
	
	// LOG_MSG_FORMAT : User[<user id>] Transaction[<class name>:<method name>]
	String MSG_LOG_DEBUG = "User [{0}] Transaction [{1}.{2}] Message [{3}]";
	String MSG_LOG_ERROR = "User [{0}] Transaction [{1}.{2}] Message [{3}]";
	String MSG_LOG_FATAL = "User [{0}] Transaction [{1}.{2}] Message [{3}]";
	String MSG_LOG_INFO = "User [{0}] Transaction [{1}.{2}] Message [{3}]";
	String MSG_LOG_PERF = "User [{0}] Transaction [{1}.{2}] State [{3}] Message [{4}]";
	String MSG_LOG_TRACE = "User [{0}] Transaction [{1}.{2}] Message [{3}]";
	String MSG_LOG_WARN = "User [{0}] Transaction [{1}.{2}] Message [{3}]";
	String MSG_GENEVA_ERROR = "{0} Initialization Failed";

	
	MessageBean MSG_NO_RECON_DATA = new MessageBean(MessageBean.INFO, "There are currently no valid records found for reconciliation.");
}
