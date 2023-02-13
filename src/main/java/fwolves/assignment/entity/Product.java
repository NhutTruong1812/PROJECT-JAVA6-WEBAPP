package fwolves.assignment.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[Product]")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public List<PriceHistory> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceHistory> prices) {
		this.prices = prices;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
 

	/**
	 * Product properties
	 */
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "`Name`")
	private String name;

	@Column(name = "Banner")
	private String banner;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "Quantity")
	private Integer quantity;

	@Column(name = "`Description`")
	private String description;

	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;

	@Column(name = "Deleted")
	private Boolean deleted;

	/**
	 * Relationship
	 */

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductImage> images;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<PriceHistory> prices;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<CartItem> cartItems;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
 
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCate() {
		return this.category.getName();
	}

	public Double getPricezz() {
		Double temp = 0.0;
		try {
			temp = this.prices.get(this.prices.size() - 1).getPrice();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return temp;
	}
}
