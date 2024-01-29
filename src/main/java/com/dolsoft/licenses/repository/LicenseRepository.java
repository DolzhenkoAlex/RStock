package com.dolsoft.licenses.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dolsoft.licenses.model.License;

@Repository
public interface LicenseRepository extends CrudRepository<License, Long>{
	
	//public List<License> findByOrganizationId(int organizationId);
	//public License findByOrganizationIdAndLicenseId(int organizationId, String licenseId);

}
