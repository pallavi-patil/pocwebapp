package com.capgemini.pocwebapp.spring.config.security;

import java.rmi.ConnectException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LDAPAuthentication {

	static DirContext ldapContext;
	

	public String authenticate(String userName, String password)
			throws Exception {
				
			String str1 ="localhost"+":"+"389"; 
		    String str2 = "ou=people,dc=maxcrc,dc=com"; ///"cn=Robert Smith,ou=people,dc=maxcrc,dc=com"; 
		    String str3 = "Robert Smith"; // empid//uid:rjsmith
		    String str4 = "rJsmitH"; // user password
		    
		    try
		    {
		     
		      
		      Hashtable<String, String> ldapEnv = new Hashtable<String, String>(11);
		      ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");		      
		      ldapEnv.put("java.naming.provider.url", "LDAP://" + str1);
		      ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");      
		      ldapEnv.put(Context.SECURITY_PRINCIPAL,"UID="+userName+","+str2);//CN=ceadmin,CN=Users,DC=jsw,DC=com");
		      ldapEnv.put(Context.SECURITY_CREDENTIALS, password);
		   
		      
		      ldapContext = new InitialDirContext(ldapEnv);

		      
		      
		    }catch (javax.naming.AuthenticationException e) {
		    	System.out.println("Authenticate error");
				return "0";
			} catch (NamingException localNamingException)
		    {
		    	localNamingException.printStackTrace();
		    	return "0";
		    	
		    }
		   System.out.println("successfully authenticating user");
		  
		return userName;
		}
		
		public static void main(String[] args) {
			LDAPAuthentication ldapObj=new LDAPAuthentication();
			String isValid="0";
			try {
				//isValid=ldapObj.authenticate("rjsmith", "rJsmitH1");
				isValid=ldapObj.authenticate("pallapat", "admin");
				System.out.println("user validity:"+ isValid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}

}
