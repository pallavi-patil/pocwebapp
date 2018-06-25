package com.capgemini.pocwebapp.beans;

import java.util.List;

import com.capgemini.pocwebapp.dao.entity.Product;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;

public class EditProductBean {
	
	
	private  Product prod;
	
	private List<ProductCategory> listCat;

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public List<ProductCategory> getListCat() {
		return listCat;
	}

	public void setListCat(List<ProductCategory> listCat) {
		this.listCat = listCat;
	}	
	
		

}
