package nsgsw1.netcare.repository.customer;

import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.customer.Customer;

import org.bson.types.ObjectId;

public interface CustomerRepositoryCustom {

	List<Customer> findByIds(Collection<ObjectId> ids);
}
