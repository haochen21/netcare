package nsgsw1.netcare.shres.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import nsgsw1.netcare.shres.model.ResCustomer;
import nsgsw1.netcare.shres.model.ResCustomerGroup;

public interface ResCustomerService {

	Collection<ResCustomer> findAllCustomerGroupRef();

	Collection<ResCustomer> findAllCustomerGroupRef(Date lastSyncTime);

	ResCustomer findCustomerGroupRef(Long id);

	Date findLastUpdateTime();
	
	List<ResCustomerGroup> findAll();
}
