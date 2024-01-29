package com.dolsoft.licenses.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dolsoft.licenses.model.License;
import com.dolsoft.licenses.repository.LicenseRepository;
import com.dolsoft.licenses.config.ServerConfig;

@Service
public class LicenseService {
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	MessageSource messages;
	
	@Autowired
	ServerConfig config;
	
	// Чтение лицензии из базы данных
	// read license for Id
	public License getLicense(Long Id) {
		License license = licenseRepository.findById(Id).get();
		return license.withComment(config.getProperty());
	}
	
	// Чтение всех лицензий из базы данных
	// read licenses 
	public List<License> getAllLicense(Long Id) {
		List<License> licenses = (List<License>)licenseRepository.findAll();
		return licenses;
	}
	
	// Создание лицензии
	// create operation
	public License createLicense(License license){
		
		if (license != null) {
			return licenseRepository.save(license);
		}
		
		return null;
	}
	
	// Обновление лицензии
	// update operation
	public License updateLicense(License license, Long licenseId){
		
		License licenseDB = licenseRepository.findById(licenseId).get();
		if (licenseDB != null) {
		
			if (license != null) {
				// обновление ProductName
		        if (Objects.nonNull(licenseDB.getProductName()) && 
		        		!"".equalsIgnoreCase(license.getProductName())) {
		            licenseDB.setProductName(license.getProductName());
		        }
		 
		        // обновление LicenseType
		        if (Objects.nonNull(licenseDB.getLicenseType()) && 
		        		!"".equalsIgnoreCase(license.getLicenseType())) {
		            licenseDB.setLicenseType(license.getLicenseType());
		        }
		        
		        // обновление OrganizationId
		        if (licenseDB.getOrganizationId() != license.getOrganizationId()) {
		            licenseDB.setOrganizationId(license.getOrganizationId());
		        }
		        
		        // обновление Description
		        if (Objects.nonNull(licenseDB.getDescription()) && 
		        		!"".equalsIgnoreCase(license.getDescription())) {
		            licenseDB.setDescription(license.getDescription());
		        }
		        
		     // обновление Comment
		        if (Objects.nonNull(licenseDB.getComment()) && 
		        		!"".equalsIgnoreCase(license.getComment())) {
		            licenseDB.setComment(license.getComment());
		        }
			}
			return licenseRepository.save(licenseDB);
		}
	
		return null;
	}
	
	// Удаление лицензии
	// delete operation
	public String deleteLicense(Long licenseId){
		
		String responseMessage = null;
		licenseRepository.deleteById(licenseId);
		responseMessage = String.format(messages.getMessage("license.delete.message", null, null),licenseId);
		return responseMessage;
		}

}
