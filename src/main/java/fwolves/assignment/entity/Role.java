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
@Table(name = "`Role`")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role {
	@Id
	@Column(name = "Id")
	private String id;

	@Column(name = "`Name`")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Authority> authorities;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static final String WATING = "WATING";

	public static final String DELIVERING = "DELIVERING";

	public static final String DELIVERED = "DELIVEREDDELIVERED";

	public static final String CANCELED = "CANCELED";

	public static final String CONFIRMED = "CONFIRMED";

	public static final String PACKING = "PACKING";

}
