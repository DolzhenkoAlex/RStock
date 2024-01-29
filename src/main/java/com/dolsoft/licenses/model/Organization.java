package com.dolsoft.licenses.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import org.springframework.hateoas.RepresentationModel;


import org.springframework.data.rest.core.annotation.RestResource;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor(force=true)
@RestResource(rel="licenses", path="licenses")

public class Organization extends RepresentationModel<Organization> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "organization_id", nullable = false)
	Long id;
	
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}
