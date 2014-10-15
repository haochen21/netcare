package nsgsw1.netcare.repository.customer;

import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.customer.CustomerGroup;

import org.bson.types.ObjectId;

public interface CustomerGroupRepositoryCustom {
	
	CustomerGroup findAndInsert(ObjectId objectId);
	
	List<CustomerGroup> findByIds(Collection<ObjectId> ids); 
}
