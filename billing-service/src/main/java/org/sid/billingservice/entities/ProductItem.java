package org.sid.billingservice.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ProductItem {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double quantity;
	private double price;
	private long productID;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	private Bill bill;
	@Transient
	private Product product;
}
