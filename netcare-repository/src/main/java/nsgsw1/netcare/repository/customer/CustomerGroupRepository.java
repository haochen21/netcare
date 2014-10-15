package nsgsw1.netcare.repository.customer;

import nsgsw1.netcare.model.customer.CustomerGroup;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CustomerGroupRepository extends MongoRepository<CustomerGroup, ObjectId>,CustomerGroupRepositoryCustom{

	@Query("{ 'resId' : ?0 }")
	CustomerGroup findByResId(String resId);
}
