package nsgsw1.netcare.repository.circuit;

import org.bson.types.ObjectId;

public interface SncRepositoryCustom {

	void removeByCircuitId(ObjectId circuitId);

}
