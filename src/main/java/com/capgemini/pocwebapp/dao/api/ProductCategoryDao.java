package com.capgemini.pocwebapp.dao.api;

import java.util.List;

import com.capgemini.pocwebapp.dao.entity.ProductCategory;

public interface ProductCategoryDao {
	List<ProductCategory> findAllProductCategory();
}
