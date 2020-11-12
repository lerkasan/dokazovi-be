package com.softserveinc.dokazovi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "institution_entity")
@Table(name = "institutions")
public class InstitutionEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "institution_id")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private RegionEntity region;

	@Column(name = "address", nullable = false)
	private String address;

	@OneToMany(mappedBy = "mainInstitution")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<UserEntity> mainUsersInstitution = new HashSet<>();

	@ManyToMany(mappedBy = "institutions")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	Set<UserEntity> users = new HashSet<>();

	public InstitutionEntity(RegionEntity region, String name, String address) {
		this.name = name;
		this.region = region;
		this.address = address;
	}
}