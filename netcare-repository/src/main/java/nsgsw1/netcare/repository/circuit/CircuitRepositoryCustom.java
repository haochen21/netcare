package nsgsw1.netcare.repository.circuit;

import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.circuit.constant.CircuitBizStatus;

import org.bson.types.ObjectId;

public interface CircuitRepositoryCustom {

	void updateExclusionDay(Collection<ObjectId> circuitIds,
			ObjectId exclusionDayId);

	List<Circuit> findByIds(Collection<ObjectId> ids);
	
	List<Circuit> findByIdsAndStatus(Collection<ObjectId> ids,boolean status);

	Circuit updateCircuitBizStatus(ObjectId id, CircuitBizStatus bizStatus);

	Circuit updateCircuitFault(ObjectId id, boolean hasFault);
}
