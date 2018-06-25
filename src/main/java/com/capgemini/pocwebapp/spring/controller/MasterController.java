package com.capgemini.pocwebapp.spring.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.capgemini.pocwebapp.beans.EditProductBean;
import com.capgemini.pocwebapp.dao.entity.Product;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;
import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.service.api.MasterDataService;


@Controller
@RequestMapping("/")
@SessionAttributes("categories")
/**
 * MasterController to handles all data related requests
 * @author pallapat
 *
 */
public class MasterController extends BaseController{

	@Autowired
	MasterDataService dataService;
	
	public static final String REST_SERVICE_URI = "http://localhost:8091/pocwebapp" ;
	
	
	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public String listProduct(ModelMap model) {

		 
		/*List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", super.getPrincipal());*/
		
		List<Product> prodList = new ArrayList();
		try {
			URI uri = new URI(REST_SERVICE_URI+"/data/products/");
			RestTemplate restTemplate = new RestTemplate(); 
			List<LinkedHashMap<String, Object>> productMap = restTemplate.getForObject(uri, List.class);
			if(productMap!=null){	
				
				for(LinkedHashMap<String, Object> prodEntity : productMap){
					System.out.println("Product : id="+prodEntity.get("id")+", prodId="+prodEntity.get("prod_id")+", name="+prodEntity.get("name")+", description="+prodEntity.get("description"));;
					Product prod = new Product();
					prod.setProdId(prodEntity.get("prodId").toString());
					prod.setName(prodEntity.get("name").toString());
					prod.setDescription(prodEntity.get("description").toString());
					prod.setQuantity(Integer.valueOf(prodEntity.get("quantity").toString()));
					prod.setId(Integer.valueOf(prodEntity.get("id").toString()));
					prod.setPrice(Integer.valueOf(prodEntity.get("price").toString()));
					LinkedHashMap<String, Object> prodCategory=(LinkedHashMap<String, Object>) prodEntity.get("prodCategory");
				
					ProductCategory category= new ProductCategory();
					if(prodCategory!=null && prodCategory.get("id")!=null) {
						category.setId(Integer.valueOf(prodCategory.get("id").toString()));
						category.setType(prodCategory.get("type").toString());
						category.setCatId(prodCategory.get("catId").toString());
					}
					prod.setProdCategory(category);
					prodList.add(prod);
				}
			}else{
				System.out.println("No user exist----------");
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("productList", prodList);
		model.addAttribute("loggedinuser", super.getPrincipal());
		return "productList";
	}


	/**
	 * This method updates existing Product details
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = { "/editProduct{prodId}" }, method = RequestMethod.GET)
	public String editProduct(@PathVariable("prodId") String prodId, ModelMap model) {
		
		System.out.println("Edit Product prodId:"+ prodId);
		URI uri;
		Product product=null;
		ProductCategory prodCats=null;
		EditProductBean prodBean=new EditProductBean();
		try {
			
			uri = new URI(REST_SERVICE_URI+"/data/updateProduct/"+prodId);			
			RestTemplate restTemplate = new RestTemplate(); 			
			product = restTemplate.getForObject(uri, Product.class);	
			prodBean.setProd(product);
			
			
			uri = new URI(REST_SERVICE_URI+"/data/categories/");
			
			ProductCategory pc =new ProductCategory();
			pc.setId(1);
			pc.setCatId("C01");
			pc.setType("Hardware");
			List<ProductCategory> list=new ArrayList();
			list.add(pc);
			
			ProductCategory pc2 =new ProductCategory();
			pc2.setId(2);
			pc2.setCatId("C02");
			pc2.setType("Software");
			
			//List<ProductCategory> list=new ArrayList();
			list.add(pc2);
			prodBean.setListCat(list);
			//URI uriCat = new URI(REST_SERVICE_URI+"/data/productCategories/");		
			//List<LinkedHashMap<String, ProductCategory>> productMap = restTemplate.getForObject(uri, List.class);
			
			if(product!=null) {
				
				//model.addAttribute("product", product);
				//model.addAttribute("category",prodCats);
				model.addAttribute("productBean", prodBean);
				model.addAttribute("edit", true);
				model.addAttribute("loggedinuser", super.getPrincipal());
				
			}else{
				
				System.out.println("No Product exist----------");
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return "updateProduct";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	
	@RequestMapping(value = { "/editProduct{prodId}" }, method = RequestMethod.POST)
	
	/*public String updateProduct(@ModelAttribute("productBean") EditProductBean productBean, BindingResult result,
			ModelMap model, @PathVariable String prodId) {
*/
	public String updateProduct(@Valid EditProductBean productBean, BindingResult result,		
			ModelMap model, @PathVariable String prodId) {
		
		
		if (result.hasErrors()) {
			return "updateProduct";
		}
	
			
			///URI uri = new URI(REST_SERVICE_URI+"/data/updateProduct/");
			RestTemplate restTemplate = new  RestTemplate();
			//restTemplate.put(uri, productBean.getProd());
			//URI uri =restTemplate.postForLocation(REST_SERVICE_URI+"/data/editProduct/", productBean.getProd(), Product.class);
						
		//	System.out.println(uri.toASCIIString());
	
			
			/*ProductCategory pc =new ProductCategory();
			pc.setId(2);
			pc.setCatId("C02");
			pc.setType("Software");
			productBean.getProd().setProdCategory(pc);*/
			dataService.updateProduct(productBean.getProd(), prodId);

		model.addAttribute("success", "Product updated successfully");
		model.addAttribute("loggedinuser", super.getPrincipal());
		return "productsuccess";
	}
	
	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/deleteProduct-{prodId}" }, method = RequestMethod.GET)
	public String deleteProduct(@PathVariable String prodId) {
		
		dataService.deleteProductByProdId(prodId);
		return "redirect:/products";
	}
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newproduct" }, method = RequestMethod.GET)
	public String newProduct(ModelMap model) {
		

		Product prod = new Product();
		EditProductBean productBean =new EditProductBean();
		productBean.setProd(prod);
		ProductCategory pc =new ProductCategory();
		pc.setId(1);
		pc.setCatId("C01");
		pc.setType("Hardware");
		List<ProductCategory> list=new ArrayList();
		list.add(pc);
		
		ProductCategory pc2 =new ProductCategory();
		pc2.setId(2);
		pc2.setCatId("C02");
		pc2.setType("Software");
		list.add(pc2);
		productBean.setListCat(list);
		if(prod!=null) {
			
			model.addAttribute("productBean", productBean);
			model.addAttribute("edit", false);
			model.addAttribute("loggedinuser", super.getPrincipal());
			
		}
		return "updateProduct";
	}
	
	@RequestMapping(value = { "/newproduct" }, method = RequestMethod.POST)
	public String newProduct(@Valid EditProductBean productBean, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			//List<User> users = userService.findAllUsers();
			//model.addAttribute("users", users);
			return "updateProduct";
		}
		System.out.println("Inside new Produc");
		if(productBean!=null) {
			System.out.println("newProduct:"+productBean.getProd().getName());
		}
		
			dataService.addProduct(productBean.getProd());

		model.addAttribute("success",
				"User " +productBean.getProd().getName() +" added  successfully");
		model.addAttribute("loggedinuser", super.getPrincipal());
		// return "success";
		return "productsuccess";

		//return "productList";
	}

	}
