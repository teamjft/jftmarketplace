package com.jft.market.model;

import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.jft.market.api.CreditCardDataEncrypter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User extends TimestampedFieldObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	/*@Column(unique = true)
	private String username;*/
	@Column(name = "first_name")
	private String fname;
	@Column(name = "last_name")
	private String lname;
	private String password;
	@Column(unique = true)
	private String email;
	private String gender;
	@Column(unique = true)
	private String uuid;
	private Long phone;

	@ManyToMany(cascade = {CascadeType.PERSIST})
	@JoinTable(
			name = "user_roles",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	Set<Role> roles = new HashSet<>();

	@OneToOne(mappedBy = "user")
	private Customer customer;

	public String getPassword() {
		CreditCardDataEncrypter creditCardDataEncrypter = null;
		try {
			creditCardDataEncrypter = new CreditCardDataEncrypter("credit-card-password");
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return creditCardDataEncrypter.decrypt(this.password);
	}

	public void setPassword(String password) throws InvalidKeySpecException {
		CreditCardDataEncrypter creditCardDataEncrypter = new CreditCardDataEncrypter("credit-card-password");
		this.password = creditCardDataEncrypter.encrypt(password);
	}
}