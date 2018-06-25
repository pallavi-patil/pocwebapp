package com.capgemini.pocwebapp.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Product entity class
 * @author pallapat
 *
 */
@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "prod_Id")
	private String prodId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private int price;
	
	
	@OneToOne 
    @JoinColumn(name="cat_id" )
	private ProductCategory prodCategory;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer integer) {
		this.id = integer;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int  getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ProductCategory getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(ProductCategory prodCategory) {
		this.prodCategory = prodCategory;
	}
	
	
	
}
