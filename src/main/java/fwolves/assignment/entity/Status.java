package fwolves.assignment.entity;

import java.io.Serializable;
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
@Table(name = "Status")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2530127226903407596L;

	/**
	 * Status properties
	 */
	@Id
	@Column(name = "Id")
	private String id;

	@Column(name = "`Name`")
	private String name;

	/**
	 * Relationship
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "status")
	private List<Order> orders;
}
