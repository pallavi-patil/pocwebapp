package com.capgemini.pocwebapp.constants;

/**
 * @author awarhoka
 *
 */
public interface IApplicationConstants {

	String APP_DB_DERIVER = "mywebapp.mysql.driver";
	String APP_DB_URL = "mywebapp.mysql.jdbcUrl";
	String APP_DB_USERNAME = "mywebapp.mysql.username";
	String APP_DB_PASWORD = "mywebapp.mysql.password";

	String HBT_SHOW_SQL = "hibernate.show_sql";
	
	String HBT_USE_2ND_CACHE= "hibernate.cache.use_second_level_cache";
	String HBT_CACHE_RGN_FACTORY_CLS= "hibernate.cache.region.factory_class";
	String HBT_CACHE_PROVIDER= "hibernate.javax.cache.provider";
}
