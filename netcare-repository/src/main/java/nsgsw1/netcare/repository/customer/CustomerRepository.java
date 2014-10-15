package nsgsw1.netcare.repository.customer;

import nsgsw1.netcare.model.customer.Customer;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerRepository extends
		MongoRepository<Customer, ObjectId>, CustomerRepositoryCustom {

	@Query("{ 'resId' : ?0 }")
	Customer findByResId(String resId);
}
