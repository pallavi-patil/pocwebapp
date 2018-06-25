package com.capgemini.pocwebapp.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
public class ProductCategory {

	@Id
	@Column(name="id")
	//@GeneratedValue (strategy= GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "cat_id", updatable = false)
	private String catId;
	
	@Column(name = "type")
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
*/
	public String getType() {
		return type;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
