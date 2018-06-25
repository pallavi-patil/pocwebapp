package com.capgemini.pocwebapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.capgemini.pocwebapp.dao.AbstractDao;
import com.capgemini.pocwebapp.dao.api.ProductDao;
import com.capgemini.pocwebapp.dao.entity.Product;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;
import com.capgemini.pocwebapp.dao.entity.User;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product>implements ProductDao {

	@Override
	public List<Product> findAllProducts() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<Product> prodList = (List<Product>) criteria.list();
		return prodList;
	}
	
	public Product findByProdID(String prodId) {
		//logger.info("ProdID : {}", prodId);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("prodId", prodId));
		Product product = (Product) crit.uniqueResult();
		if (product != null) {
			Hibernate.initialize(product.getProdCategory());
		}
		return product;
	}

	@Override
	public void saveProduct(Product prod) {
		getSession().save(prod);
	
	}
	
	public void deleteByProdId(String prodId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("prodId", prodId));
		Product prod = (Product) crit.uniqueResult();
		delete(prod);
	}


}
