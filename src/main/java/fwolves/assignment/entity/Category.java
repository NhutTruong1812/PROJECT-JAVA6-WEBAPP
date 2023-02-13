package fwolves.assignment.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[Category]")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	/**
	 * Category properties
	 */
	@Id
	@Column(name = "Id")
	private String Id;

	@Column(name = "`Name`")
	private String name;

	@Column(name = "Deleted")
	private Boolean deleted;

	/**
	 * Relationship
	 */

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Product> products;

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
