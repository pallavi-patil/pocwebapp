package com.capgemini.pocwebapp.service.api;

import java.util.List;

import com.capgemini.pocwebapp.dao.entity.Product;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;

public interface MasterDataService {

	List<Product> getAllProduct();
	
	Product findProductByID(String prodId);
	
	void addProduct(Product prod);
	
	void updateProduct(Product prod, String prodId);
	
	void deleteProdById(Integer prodId);

	List<ProductCategory> getAllProductCategory();
	
	void deleteProductByProdId(String prodId);
}