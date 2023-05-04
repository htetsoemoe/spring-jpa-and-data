package com.jdc.jpql.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")

@NamedQuery(name = "Category.allCount", query = "select count(c) from Category c")
@NamedQuery(name = "Category.getAll", query = "select c from Category c")
@NamedQuery(name = "Category.updateNameById", query = "update Category c set c.name = :name where c.id = :id")
@NamedQuery(name = "Category.findByNameLike", query = "select c from Category c where lower(c.name) like ?1")
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String logo;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
