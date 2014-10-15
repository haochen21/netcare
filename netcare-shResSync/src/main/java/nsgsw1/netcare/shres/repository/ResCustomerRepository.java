package nsgsw1.netcare.shres.repository;

import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.shres.model.ResCustomer;

public interface ResCustomerRepository extends GenericRepository<ResCustomer> {

	Collection<ResCustomer> findAllCustomerGroupRef();
	
	Collection<ResCustomer> findAllCustomerGroupRef(Date lastSyncTime);
	
	ResCustomer findCustomerGroupRef(Long id);
	
	Date findLastUpdateTime();
}