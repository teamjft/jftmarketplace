package com.jft.market.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String name;
	private String password;
	@Column(unique = true)
	private String email;
	private String gender;
	private Long phone;
	private String uuid;
	private Boolean enabled = Boolean.FALSE;
	@OneToOne(cascade = {javax.persistence.CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "user_id")
	private User user;

	public Customer(String name, Long phone) {
		this.name = name;
		this.phone = phone;
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	Set<PaymentInstrument> paymentInstrumentList = new HashSet<PaymentInstrument>();

	@OneToMany(mappedBy = "customer")
	private List<Product> products = new ArrayList<Product>();
}
