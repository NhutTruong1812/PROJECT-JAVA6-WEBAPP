package fwolves.assignment.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`User`")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(List<Authority> authority) {
		this.authority = authority;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * User properties
	 */

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "Username")
	private String username;

	@NotBlank
	@Column(name = "Password")
	private String password;

	@NotBlank
	@Column(name = "Fullname")
	private String fullname;

	@NotBlank
	@Column(name = "Email")
	private String email;

	@NotBlank
	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "RegisterDate")
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	@Column(name = "Activated")
	private Boolean activated;

	@Column(name = "Avatar")
	private String avatar;

	/**
	 * Relationship
	 */

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Authority> authority;

	@JsonIgnore
	@OneToOne(mappedBy = "user")
	private Cart cart;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Address> receivers;
 
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
