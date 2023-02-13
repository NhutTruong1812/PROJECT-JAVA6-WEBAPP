package fwolves.assignment.entity;

import java.io.Serializable;
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
@Table(name = "[Order]")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5681892237161641427L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	@Column(name = "Fullname")
	private String fullname;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "[Address]")
	private String address;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "StatusId")
	private Status status;

	@Column(name = "Note")
	String note;

	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
}
