package com.capgemini.pocwebapp.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.capgemini.pocwebapp.dao.entity.Product;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;
import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.service.api.MasterDataService;
import com.capgemini.pocwebapp.service.api.UserProfileService;
import com.capgemini.pocwebapp.service.api.UserService;

@RestController
@RequestMapping("/data")
@Secured("USER")
public class RestDataController {

	public static final Logger logger = LoggerFactory.getLogger(RestDataController.class);

	@Autowired
	MasterDataService dataService;

	public static final String REST_SERVICE_URI = "http://localhost:8091/pocwebapp";

	// -------------------Retrieve All
	// Users---------------------------------------------

	@RequestMapping(value = "/products/", method = RequestMethod.GET)

	public ResponseEntity<List<Product>> listAllProducts() {
		List<Product> prodList = dataService.getAllProduct();
		if (prodList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Product>>(prodList, HttpStatus.OK);
	}

	@RequestMapping(value = "/entity/{entityName}", method = RequestMethod.GET)

	public ResponseEntity<List<Product>> listAllEntity(@PathVariable("entityName") String entity) {
		System.out.println("Fetch Data for :" + entity);
		List<Product> prodList = dataService.getAllProduct();
		if (prodList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Product>>(prodList, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/categories/", method = RequestMethod.GET)

	public ResponseEntity<List<ProductCategory>> listAllProductCategory() {
		List<ProductCategory> prodList = dataService.getAllProductCategory();
		if (prodList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<ProductCategory>>(prodList, HttpStatus.OK);
	}

	/**
	 * Search product based on product Id
	 * 
	 * @param prodId
	 * @return Product
	 */
	@RequestMapping(value = { "/updateProduct/{prodId}" }, method = RequestMethod.GET)
	
	public ResponseEntity<Product> searchProduct(@PathVariable("prodId") String prodId) {
		System.out.println("Fetch Data for :" + prodId);

		Product prod = dataService.findProductByID(prodId);
		if (prod == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}

		return new ResponseEntity<Product>(prod, HttpStatus.OK);

	}

	@RequestMapping(value = "/editProduct/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody()
	public ResponseEntity<String> updateProduct( @RequestBody  Product product) {

		System.out.println("Fetch Data for :");
		//dataService.updateProduct(product, prodId);
		/*HttpHeaders hea
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		*///
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
