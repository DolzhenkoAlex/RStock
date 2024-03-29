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
public class License extends RepresentationModel<License> implements Serializable {
	/**
	 * Класс лицензий программного обеспечения
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "license_id", nullable = false)
	private Long licenseId;
	
	private String description;
	
	@Column(name = "organization_id", nullable = false)
	private long organizationId;
	
	@Column(name = "product_name", nullable = false)
	private String productName;
	
	@Column(name = "license_type", nullable = false)
	private String licenseType;
	
	@Column(name="comment")
	private String comment;
	
	public License withComment(String comment){
		this.setComment(comment);
		return this;
	}
}
