package nsgsw1.netcare.service.internal;

import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.customer.CustomerGroup;
import nsgsw1.netcare.repository.customer.CustomerGroupRepository;
import nsgsw1.netcare.repository.customer.CustomerRepository;
import nsgsw1.netcare.service.CustomerService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerGroupRepository customerGroupRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerGroup saveCustomerGroup(CustomerGroup customerGroup) {
		return customerGroupRepository.save(customerGroup);
	}

	@Override
	public CustomerGroup findCustomerGroupByResId(String resId) {
		return customerGroupRepository.findByResId(resId);
	}

	@Override
	public List<CustomerGroup> findCustomerGroupByIds(Collection<ObjectId> ids) {
		return customerGroupRepository.findByIds(ids);
	}

	@Override
	public Customer findCustomerById(ObjectId id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer findCustomerByResId(String resId) {
		return customerRepository.findByResId(resId);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> findCustomerByIds(Collection<ObjectId> ids) {
		return customerRepository.findByIds(ids);
	}

}
