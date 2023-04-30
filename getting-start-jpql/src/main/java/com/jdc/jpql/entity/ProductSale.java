package com.jdc.jpql.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * 
 * join table for ManyToMany of between product and sale
 *
 */
@Entity
@Table(name = "product_sale")
public class ProductSale implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductSalePK id;

	@ManyToOne
	@MapsId("productId")
	private Product product;

	@ManyToOne
	@MapsId("saleId")
	private Sale sale;

	public ProductSalePK getId() {
		return id;
	}

	public void setId(ProductSalePK id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.id.setProductId(product.getId());
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
		this.id.setSaleId(sale.getId());
	}

}
