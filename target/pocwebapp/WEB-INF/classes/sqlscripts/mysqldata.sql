insert into users(username,password,enabled)
	values('admin','$2a$10$QQZt2uSGGB4qmnAIBpSj2ubFX4quwb4A48J/e6EQpKI/fG2JB9lIK',true);
insert into authorities(username,authority) 
	values('admin','ROLE_ADMIN');
    
    
show tables