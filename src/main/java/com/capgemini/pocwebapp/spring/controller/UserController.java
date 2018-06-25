package com.capgemini.pocwebapp.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.pocwebapp.dao.entity.FileBucket;
import com.capgemini.pocwebapp.dao.entity.Product;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;
import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.dao.entity.UserInfo;
import com.capgemini.pocwebapp.dao.entity.UserProfile;
import com.capgemini.pocwebapp.service.api.UserProfileService;
import com.capgemini.pocwebapp.service.api.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	private static String UPLOAD_LOCATION = "D:\\log";

	 public static final String REST_SERVICE_URI = "http://localhost:8091/pocwebapp" ;
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		 
		/*List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", super.getPrincipal());*/
		
		List<User> users = new ArrayList();
		try {
			URI uri = new URI(REST_SERVICE_URI+"/api/user/");
			RestTemplate restTemplate = new RestTemplate(); 
			List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(uri, List.class);
			if(usersMap!=null){
				for(LinkedHashMap<String, Object> map : usersMap){
					System.out.println("User : id="+map.get("id")+", ssoId="+map.get("ssoId")+", firstName="+map.get("firstName")+", lastName="+map.get("lastName"));;
					User user = new User();
					user.setSsoId(map.get("ssoId").toString());
					user.setFirstName(map.get("firstName").toString());
					user.setLastName(map.get("lastName").toString());
					user.setEmail(map.get("email").toString());
					user.setId(Integer.valueOf(map.get("id").toString()));
					users.add(user);
				}
			}else{
				System.out.println("No user exist----------");
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", super.getPrincipal());
		return "userslist";
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		model.addAttribute("name", name);

		System.out.println("This is the username" + name);
		return "home";
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/userpermission" }, method = RequestMethod.GET)
	public String listRoles(ModelMap model) {

		List<User> usrprofile = userService.getallProfile();
		List<String[]> userP = new ArrayList<String[]>();
		for (User user : usrprofile) {
			String[] permission = new String[2];
			permission[0] = user.getFirstName();
			permission[1] = user.getUserProfiles().toString();
			userP.add(permission);
		}
		System.out.println(usrprofile);
		model.addAttribute("users", userP);
		model.addAttribute("loggedinuser", super.getPrincipal());
		return "userScreenPermission";
	}

	/**
	 * This method will return the usermenu right page
	 */
	@RequestMapping(value = { "/usermenuright" }, method = RequestMethod.GET)
	public String menuRight(ModelMap model) {

		return "userMenuRights";
	}

	/**
	 * This method will provide the medium to add a new role.
	 */
	@RequestMapping(value = { "/newRole" }, method = RequestMethod.GET)
	public String newRole(ModelMap model) {

		return "newRole";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", super.getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			List<User> users = userService.findAllUsers();
			model.addAttribute("users", users);
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing
		 * custom @Unique annotation and applying it on field [sso] of Model class
		 * [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill
		 * custom errors outside the validation framework as well while still using
		 * internationalized messages.
		 * 
		 */
		if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId",
					new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", super.getPrincipal());
		// return "success";
		return "registrationsuccess";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", super.getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */

	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, BindingResult result, ModelMap model,
			@PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}

		userService.updateUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", super.getPrincipal());
		return "registrationsuccess";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	// to open upload page

	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "redirect:/singleFileUploader";

	}

	@RequestMapping(value = { "/singleFileUploader" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String singleFileUploader(@Valid FileBucket fileBucket, BindingResult result, ModelMap mode,
			ModelMap model) {
		return "singleFileUploader";
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model)

	{
		List<User> users = userService.findAllUsers();
		List<String> ssoId = new ArrayList<>();
		// getting ssoID
		for (int j = 0; j < users.size(); j++) {
			ssoId.add(users.get(j).getSsoId());
		}

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "redirect:/singleFileUploader";
			/* return "singleFileUploader"; */
		} else {
			try {
				System.out.println("Fetching file");
				MultipartFile multipartFile = fileBucket.getFile();
				// Now do something with file...
				FileCopyUtils.copy(fileBucket.getFile().getBytes(),
						new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
				String fileName = multipartFile.getOriginalFilename();
				model.addAttribute("fileName", fileName);
				System.out.println("This is file name" + fileName);
				// to save the file
				InputStream inputStream = null;
				OutputStream outputStream = null;
				inputStream = multipartFile.getInputStream();
				File newFile = new File(UPLOAD_LOCATION + "\\" + fileName);
				System.out.println("This is the location" + newFile);
				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				outputStream = new FileOutputStream(newFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}

				// this is excel upload
				List<UserInfo> lstUser = new ArrayList<>();

				int i = 1;
				Workbook workbook;

				workbook = WorkbookFactory.create(new File(UPLOAD_LOCATION + "\\" + fileName));
				Sheet sheet = workbook.getSheetAt(0);
				Row row;
				while (i <= sheet.getLastRowNum()) {
					UserInfo user = new UserInfo();
					row = sheet.getRow(i++);
					user.setFirstName(row.getCell(0).getStringCellValue());
					user.setLastName(row.getCell(1).getStringCellValue());
					user.setUsername(row.getCell(2).getStringCellValue());
					user.setRole(row.getCell(3).getStringCellValue());
					user.setPassword(row.getCell(4).getStringCellValue());
					for (int j = 0; j < ssoId.size(); j++) {
						if (!ssoId.contains(user.getUsername())) {
							lstUser.add(user);
						}
					}
				}
				System.out.println(lstUser);
				workbook.close();
				userService.updateList(lstUser);
				model.addAttribute("lstUser", lstUser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "home";
		}
	}
	
	
	
/*	@SuppressWarnings("resource")
	@RequestMapping(value = "/uploadUsers", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String uploadUsers(@Valid FileBucket fileBucket, BindingResult result, ModelMap model)

	{
		List<User> users = userService.findAllUsers();
		List<String> ssoId = new ArrayList<>();
		// getting ssoID
		for (int j = 0; j < users.size(); j++) {
			ssoId.add(users.get(j).getSsoId());
		}

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "redirect:/singleFileUploader";
			 return "singleFileUploader"; 
		} else {
			try {
				System.out.println("Fetching file");
				MultipartFile multipartFile = fileBucket.getFile();
				// Now do something with file...
				FileCopyUtils.copy(fileBucket.getFile().getBytes(),
						new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
				String fileName = multipartFile.getOriginalFilename();
				model.addAttribute("fileName", fileName);
				System.out.println("This is file name" + fileName);
				// to save the file
				InputStream inputStream = null;
				OutputStream outputStream = null;
				inputStream = multipartFile.getInputStream();
				File newFile = new File(UPLOAD_LOCATION + "\\" + fileName);
				System.out.println("This is the location" + newFile);
				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				outputStream = new FileOutputStream(newFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}

				// this is excel upload
				List<User> lstUser = new ArrayList<>();

				int i = 1;
				Workbook workbook;

				workbook = WorkbookFactory.create(new File(UPLOAD_LOCATION + "\\" + fileName));
				Sheet sheet = workbook.getSheetAt(0);
				Row row;
				while (i <= sheet.getLastRowNum()) {
					User user = new User();
					row = sheet.getRow(i++);
					user.setFirstName(row.getCell(0).getStringCellValue());
					user.setLastName(row.getCell(1).getStringCellValue());
					user.setSsoId(row.getCell(2).getStringCellValue());
					user.setPassword(passwordEncoder.encode(row.getCell(3).getStringCellValue()));
					user.setEmail(row.getCell(5).getStringCellValue());
					//user.getUserProfiles().add((row.getCell(3).getStringCellValue());
					for (int j = 0; j < ssoId.size(); j++) {
						if (!ssoId.contains(user.getUsername())) {
							lstUser.add(user);
						}
					}
				}
				System.out.println(lstUser);
				workbook.close();
				userService.updateList(lstUser);
				model.addAttribute("lstUser", lstUser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "home";
		}
	}*/
}