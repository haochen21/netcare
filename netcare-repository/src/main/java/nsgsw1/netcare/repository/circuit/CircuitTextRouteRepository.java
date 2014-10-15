package nsgsw1.netcare.repository.circuit;

import nsgsw1.netcare.model.circuit.CircuitTextRoute;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CircuitTextRouteRepository extends
		MongoRepository<CircuitTextRoute, ObjectId> {

	@Query("{ 'circuitId' : ?0 }")
	CircuitTextRoute findByCircuitId(ObjectId circuitId);
}
