package com.dolsoft.licenses.controller;

//import java.util.Random;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
	
	@GetMapping(value="/{licenseId}")
	//@RequestMapping(value="/{licenseId}", method = RequestMethod.GET)
	public ResponseEntity<License> getLicense(@PathVariable("licenseId") Long Id) {
		
		License license = licenseService.getLicense(Id);
	
		license.add(
				linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
						.getLicense(license.getLicenseId()))
						.withSelfRel(),
				linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
						.createLicense(license))
						.withRel("createLicense"),
				linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
						.updateLicense(license, Id))
						.withRel("updateLicense"),
				linkTo(WebMvcLinkBuilder.methodOn(LicenseController.class)
						.deleteLicense(Id))
						.withRel("deleteLicense"));
	
	return ResponseEntity.ok(license);
	}
	
	@PutMapping
	public ResponseEntity<String> updateLicense(
			@RequestBody License request,
			@PathVariable("Id")	Long Id)
			 {
		return ResponseEntity.ok(licenseService.updateLicense(request, Id).toString());
	}
	
	@PostMapping
	public ResponseEntity<License> createLicense(
			@RequestBody License request) {
		return ResponseEntity.ok(licenseService.createLicense(request));
	}
	
	@DeleteMapping(value="/{licenseId}")
	public ResponseEntity<String> deleteLicense(
			@PathVariable("licenseId") Long licenseId) {
		return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
	}
}
