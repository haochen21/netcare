package nsgsw1.netcare.service;

import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.customer.CustomerGroup;

import org.bson.types.ObjectId;

public interface CustomerService {

	CustomerGroup findCustomerGroupByResId(String resId);

	CustomerGroup saveCustomerGroup(CustomerGroup customerGroup);

	List<CustomerGroup> findCustomerGroupByIds(Collection<ObjectId> ids);
	
	Customer findCustomerById(ObjectId id);

	Customer findCustomerByResId(String resId);

	Customer saveCustomer(Customer customer);

	List<Customer> findCustomerByIds(Collection<ObjectId> ids);
}
