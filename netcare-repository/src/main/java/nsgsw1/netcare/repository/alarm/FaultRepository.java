package nsgsw1.netcare.repository.alarm;

import java.util.List;

import nsgsw1.netcare.model.alarm.Fault;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FaultRepository extends MongoRepository<Fault, ObjectId> {

	@Query("{ 'circuitId' : ?0 }")
	Fault findByCircuitId(ObjectId circuitId);
	
	@Query("{ 'currAlarmIds' : ?0 }")
	List<Fault> findByCurrAlarmId(ObjectId currAlarmId);
}
