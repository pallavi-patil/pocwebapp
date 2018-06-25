package com.capgemini.pocwebapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.capgemini.pocwebapp.dao.AbstractDao;
import com.capgemini.pocwebapp.dao.api.ProductCategoryDao;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;

@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends AbstractDao<Integer,ProductCategory>implements ProductCategoryDao{

	@Override
	public List<ProductCategory> findAllProductCategory() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<ProductCategory> prodCatList = (List<ProductCategory>) criteria.list();
		return prodCatList;
	}

}
