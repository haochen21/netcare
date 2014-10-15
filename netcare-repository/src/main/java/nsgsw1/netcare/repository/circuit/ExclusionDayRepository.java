package nsgsw1.netcare.repository.circuit;

import nsgsw1.netcare.model.circuit.ExclusionDay;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExclusionDayRepository extends
		MongoRepository<ExclusionDay, ObjectId> {
	
}
