package com.capgemini.pocwebapp.spring.config.security;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AppConfigurationEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.capgemini.pocwebapp.utils.AuthenticationConstants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = { "com.capgemini.pocwebapp" })
@PropertySource("classpath:/config/ldap/ldap_details.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	private String groupRoleAttribute = "cn";
    private String groupSearchBase = "";
    private String groupSearchFilter = "(uniqueMember={0})";
    private String rolePrefix = "ROLE_";
    private String userSearchBase = ""; // only for search
    private String userSearchFilter = "uid={0}"; // only for search
    private String[] userDnPatterns = {"uid={0},ou=people"};
    private LdapAuthoritiesPopulator ldapAuthoritiesPopulator;
    private UserDetailsContextMapper userDetailsContextMapper;
    
	@Autowired
	private Environment env;
	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;
	
	@Autowired
	@Qualifier("roleGranter")
	AuthorityGranter roleGranter;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		String authentication = env.getRequiredProperty(AuthenticationConstants.AUTHENTICATION);
		if(authentication.equalsIgnoreCase(AuthenticationConstants.LDAP_AUTH)) {
			
			auth.authenticationProvider(ldapAuthenticationProvider());
		}
		if(authentication.equalsIgnoreCase(AuthenticationConstants.DB_AUTH)) {
			
			auth.userDetailsService(userDetailsService);
			auth.authenticationProvider(dbAuthenticationProvider());
		}
	}
	
	/*@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception
    {
		//auth.userDetailsService(userDetailsService);
        DefaultSpringSecurityContextSource context = new DefaultSpringSecurityContextSource("ldap://localhost:389/dc=maxcrc,dc=com");
        context.afterPropertiesSet();
        context.setUserDn("cn=ADMIN,dc=maxcrc,dc=com");
        context.setPassword("secret");

        auth.ldapAuthentication()
                .contextSource(context)
                .userSearchFilter("uid={0}")
                .userSearchBase("ou=people")
                .groupSearchBase("ou=people")
                .groupSearchFilter("uniqueMember={0}");
    } */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().antMatchers("/", "/list")
		http.authorizeRequests().antMatchers("/", "/list","/home","/userpermission","/usermenuright","/listProduct", "/editProduct")
				.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
				.antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
				.access("hasRole('ADMIN') or hasRole('DBA')").and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password").and()
				.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
				.tokenValiditySeconds(86400).and().csrf().disable().exceptionHandling().accessDeniedPage("/Access_Denied");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	public DaoAuthenticationProvider dbAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}*/
	
	@Bean
	public DefaultJaasAuthenticationProvider dbAuthenticationProvider() {
		DefaultJaasAuthenticationProvider authenticationProvider = new DefaultJaasAuthenticationProvider();
		
		Map<String, String> options = new HashMap<String, String>();
		options.put(AuthenticationConstants.DEBUG, env.getRequiredProperty(AuthenticationConstants.DEBUG));		
		
		AppConfigurationEntry appConfigEntries[] = new AppConfigurationEntry[1];
		appConfigEntries[0] = new AppConfigurationEntry("com.capgemini.pocwebapp.spring.config.security.Login", javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options);
      
		Map<String, AppConfigurationEntry[]> mappedConfigurations = new HashMap();
		mappedConfigurations.put(AuthenticationConstants.APP_CONFIG_ENTRY_KEY, appConfigEntries);
		authenticationProvider.setConfiguration(new InMemoryConfiguration(mappedConfigurations));
		
		AuthorityGranter[] authorityGranter = new AuthorityGranter[1];
		authorityGranter[0] = roleGranter;		
		authenticationProvider.setAuthorityGranters(authorityGranter);
		
		return authenticationProvider;
	}
	

	@Bean
	public DefaultJaasAuthenticationProvider ldapAuthenticationProvider() {
		DefaultJaasAuthenticationProvider authenticationProvider = new DefaultJaasAuthenticationProvider();
		
	/*	Map<String, String> options = new HashMap<String, String>();
		options.put(AuthenticationConstants.DEBUG, env.getRequiredProperty(AuthenticationConstants.DEBUG));*/
		
		Map<String, String> options = new HashMap<String, String>();
        options.put(AuthenticationConstants.USER_PROVIDER, env.getRequiredProperty(AuthenticationConstants.USER_PROVIDER));
        options.put(AuthenticationConstants.USER_FILTER, env.getRequiredProperty(AuthenticationConstants.USER_FILTER));
        options.put(AuthenticationConstants.USE_SSL, env.getRequiredProperty(AuthenticationConstants.USE_SSL));
        //options.put("authIdentity", "uid={USERNAME}");
        options.put(AuthenticationConstants.DEBUG, env.getRequiredProperty(AuthenticationConstants.DEBUG));
		
		AppConfigurationEntry appConfigEntries[] = new AppConfigurationEntry[1];
        appConfigEntries[0] = new AppConfigurationEntry(env.getRequiredProperty(AuthenticationConstants.LDAP_LOGIN_MODULE), javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options);
		//appConfigEntries[0] = new AppConfigurationEntry("com.capgemini.pocwebapp.spring.config.security.Login", javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options);
      
		Map<String, AppConfigurationEntry[]> mappedConfigurations = new HashMap();
		mappedConfigurations.put(AuthenticationConstants.APP_CONFIG_ENTRY_KEY, appConfigEntries);
		authenticationProvider.setConfiguration(new InMemoryConfiguration(mappedConfigurations));
		
		AuthorityGranter[] authorityGranter = new AuthorityGranter[1];
		authorityGranter[0] = roleGranter;
		
		authenticationProvider.setAuthorityGranters(authorityGranter);
		return authenticationProvider;
	}
	
	/*public LdapAuthenticationProvider authenticationProvider() {
		
		 	BaseLdapPathContextSource contextSource = getContextSource();
		    LdapAuthenticator ldapAuthenticator = createLdapAuthenticator(contextSource);
		    LdapAuthoritiesPopulator authoritiesPopulator = getLdapAuthoritiesPopulator(contextSource);
		    LdapAuthenticationProvider ldapAuthenticationProvider = new LdapAuthenticationProvider(ldapAuthenticator, authoritiesPopulator);
		    try {
				ldapAuthenticationProvider.setAuthoritiesMapper(getAuthoritiesMapper());
			} catch (Exception e) {
				System.out.println("Error");
				e.printStackTrace();
			}
		    if (userDetailsContextMapper != null) {
		        ldapAuthenticationProvider.setUserDetailsContextMapper(userDetailsContextMapper);
		    }
		    return ldapAuthenticationProvider;
	}*/
	
	
	private GrantedAuthoritiesMapper getAuthoritiesMapper() throws Exception {
		SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
        simpleAuthorityMapper.setPrefix(rolePrefix);
        simpleAuthorityMapper.afterPropertiesSet();
		return simpleAuthorityMapper;
	}

	private LdapAuthoritiesPopulator getLdapAuthoritiesPopulator(BaseLdapPathContextSource contextSource) {
		if (ldapAuthoritiesPopulator != null) {
            return ldapAuthoritiesPopulator;
        }

        DefaultLdapAuthoritiesPopulator defaultAuthoritiesPopulator = new DefaultLdapAuthoritiesPopulator(
                contextSource, groupSearchBase);
        defaultAuthoritiesPopulator.setGroupRoleAttribute(groupRoleAttribute);
        defaultAuthoritiesPopulator.setGroupSearchFilter(groupSearchFilter);

        this.ldapAuthoritiesPopulator = defaultAuthoritiesPopulator;
        return defaultAuthoritiesPopulator;
	}

	private BaseLdapPathContextSource getContextSource() {
		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrl("ldap://localhost:389/ou=People,");
		ldapContextSource.setBase("dc=maxcrc,dc=com");
		ldapContextSource.setUserDn("cn=Manager");
		ldapContextSource.setPassword("secret");
		return ldapContextSource;
	}

	public LdapAuthenticator createLdapAuthenticator(BaseLdapPathContextSource contextSource) {
		BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource);
		LdapUserSearch userSearch = createUserSearch(contextSource);
        if (userSearch != null) {
        	bindAuthenticator.setUserSearch(userSearch);
        }
        if (userDnPatterns != null && userDnPatterns.length > 0) {
        	bindAuthenticator.setUserDnPatterns(userDnPatterns);
        }
		return bindAuthenticator;		
	}
	
	 private LdapUserSearch createUserSearch(BaseLdapPathContextSource contextSource) {
	        if (userSearchFilter == null) {
	            return null;
	        }
	        return new FilterBasedLdapUserSearch(userSearchBase, userSearchFilter,contextSource);
	    }
	 
	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
    public static void main(String[] args) {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
    	System.out.println(encoder.encode("admin"));
    }
}
