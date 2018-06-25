package com.capgemini.pocwebapp.dao.api;

import java.util.List;

import com.capgemini.pocwebapp.dao.entity.Product;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;

public interface ProductDao {


	public List<Product> findAllProducts();
	public Product findByProdID(String prodId);
	public void saveProduct(Product prod);
	public void deleteByProdId(String prodId);
}
