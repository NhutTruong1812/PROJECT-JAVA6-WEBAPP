package fwolves.assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderDetail")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 256469779942622109L;

	/**
	 * OrderDetail properties
	 */

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "OrderId")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "PriceHistoryId")
	private PriceHistory priceHistory;

	@Column(name = "Quantity")
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
