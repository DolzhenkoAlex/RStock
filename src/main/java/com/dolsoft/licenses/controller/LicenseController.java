package com.dolsoft.licenses.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dolsoft.licenses.model.License;
import com.dolsoft.licenses.service.LicenseService;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@RequestMapping(value="v1/organization/{organizationId}/licenses")
public class LicenseController {

	@Autowired
	private LicenseService licenseService;
	
	//@GetMapping(value="/{licenseId}")
	@RequestMapping(value="/{licenseId}", method = RequestMethod.GET)
	public ResponseEntity<License> getLicense(
			@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		License license = licenseService.getLicense(licenseId,organizationId);
		
		license.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
				.getLicense(organizationId, license.getLicenseId()))
				.withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
				.createLicense(organizationId, license))
				.withRel("createLicense"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
				.updateLicense(organizationId, license))
				.withRel("updateLicense"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
				.deleteLicense(organizationId, license.getLicenseId()))
				.withRel("deleteLicense"));
		
	return ResponseEntity.ok(license);
	}
	
	@PutMapping
	public ResponseEntity<String> updateLicense(
			@PathVariable("organizationId")	String organizationId,
			@RequestBody License request) {
		return ResponseEntity.ok(licenseService.updateLicense(request,organizationId));
	}
	
	@PostMapping
	public ResponseEntity<String> createLicense(
			@PathVariable("organizationId") String organizationId,
			@RequestBody License request) {
		return ResponseEntity.ok(licenseService.createLicense(request,organizationId));
	}
	
	@DeleteMapping(value="/{licenseId}")
	public ResponseEntity<String> deleteLicense(
			@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		return ResponseEntity.ok(licenseService.deleteLicense(licenseId,organizationId));
	}
}
