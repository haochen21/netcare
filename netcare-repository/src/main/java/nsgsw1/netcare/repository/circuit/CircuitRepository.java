package nsgsw1.netcare.repository.circuit;

import nsgsw1.netcare.model.circuit.Circuit;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CircuitRepository extends MongoRepository<Circuit, ObjectId>,
		CircuitRepositoryCustom {

	@Query("{ 'no' : ?0 }")
	Circuit findByNo(String no);
}
