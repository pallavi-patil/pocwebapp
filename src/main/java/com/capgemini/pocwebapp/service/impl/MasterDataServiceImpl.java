package com.capgemini.pocwebapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pocwebapp.dao.api.ProductCategoryDao;
import com.capgemini.pocwebapp.dao.api.ProductDao;
import com.capgemini.pocwebapp.dao.api.UserProfileDao;
import com.capgemini.pocwebapp.dao.entity.Product;
import com.capgemini.pocwebapp.dao.entity.ProductCategory;
import com.capgemini.pocwebapp.dao.entity.User;
import com.capgemini.pocwebapp.service.api.MasterDataService;

@Service("dataService")
@Transactional

public class MasterDataServiceImpl implements MasterDataService {

	@Autowired
	ProductDao dao;
	
	@Autowired
	ProductCategoryDao prodCatDao;
	@Override
	public List<Product> getAllProduct() {
		return dao.findAllProducts();
		
	}

	static int count=3;
	@Override
	public Product findProductByID(String prodId) {
		// TODO Auto-generated method stub
		return dao.findByProdID(prodId);
	}

	@Override
	public void addProduct(Product prod) {
		prod.setId(count++);
		dao.saveProduct(prod);
		
	}

	@Override
	public void updateProduct(Product prod, String prodId) {
		Product prodEntity=null;
		if(prodId!=null) {
			prodEntity= dao.findByProdID(prodId);
		}else {
			prodEntity=dao.findByProdID(prod.getProdId());
		}
		
		if (prodEntity != null) {
			
			prodEntity.setName(prod.getName());
			prodEntity.setPrice(prod.getPrice());
			prodEntity.setDescription(prod.getDescription());
			prodEntity.setQuantity(prod.getQuantity());
			prodEntity.setProdCategory(prod.getProdCategory());
			
			}
		 dao.saveProduct(prodEntity);
		
		}
	

	@Override
	public void deleteProdById(Integer prodId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductCategory> getAllProductCategory() {
		// TODO Auto-generated method stub
		return  prodCatDao.findAllProductCategory();
	}

	@Override
	public void deleteProductByProdId(String prodId) {
		dao.deleteByProdId(prodId);
	}

}
