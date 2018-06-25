package com.capgemini.pocwebapp.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.service.api.UserProfileService;
import com.capgemini.pocwebapp.service.api.UserService;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	// -------------------Retrieve All Users---------------------------------------------

		@RequestMapping(value = "/user/", method = RequestMethod.GET)
		public ResponseEntity<List<User>> listAllUsers() {
			List<User> users = userService.findAllUsers();
			if (users.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
}
