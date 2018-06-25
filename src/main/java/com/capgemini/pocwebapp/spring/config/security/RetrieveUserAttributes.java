package com.capgemini.pocwebapp.spring.config.security;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
 
public class RetrieveUserAttributes {
 
    public static void main(String[] args) {
        RetrieveUserAttributes retrieveUserAttributes = new RetrieveUserAttributes();
        retrieveUserAttributes.getUserBasicAttributes("pallapat", retrieveUserAttributes.getLdapContext());
    }
 
    public LdapContext getLdapContext(){
        LdapContext ctx = null;
        try{
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "Simple");
            env.put(Context.SECURITY_PRINCIPAL, "uid=test,ou=people,dc=maxcrc,dc=com");
            env.put(Context.SECURITY_CREDENTIALS, "admin");
            env.put(Context.PROVIDER_URL, "ldap://localhost:389");
            ctx = new InitialLdapContext(env, null);
            System.out.println("Connection Successful.");
        }catch(NamingException nex){
            System.out.println("LDAP Connection: FAILED");
            nex.printStackTrace();
        }
        return ctx;
    }
 
    private void getUserBasicAttributes(String username, LdapContext ctx) {
        //User user=null;
        try {
 
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = { "distinguishedName",
                    "sn",
                    "givenname",
                    "mail",
                    "telephonenumber","memberOf"};
            constraints.setReturningAttributes(attrIDs);
            //First input parameter is search bas, it can be "CN=Users,DC=YourDomain,DC=com"
            //Second Attribute can be uid=username
            NamingEnumeration answer = ctx.search("DC=maxcrc,DC=com", "uid="
                    + username, constraints);
            if (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                System.out.println("distinguishedName "+ attrs.get("distinguishedName"));
                System.out.println("givenname "+ attrs.get("givenname"));
                System.out.println("sn "+ attrs.get("sn"));
                System.out.println("mail "+ attrs.get("mail"));

                System.out.println("memberOf "+ attrs.get("memberOf"));
                System.out.println("telephonenumber "+ attrs.get("telephonenumber"));
            }else{
                throw new Exception("Invalid User");
            }
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       // return user;
    }
 
}
