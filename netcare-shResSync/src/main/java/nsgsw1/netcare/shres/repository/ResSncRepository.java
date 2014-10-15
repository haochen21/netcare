package nsgsw1.netcare.shres.repository;

import java.util.Collection;

import nsgsw1.netcare.shres.model.ResSnc;

public interface ResSncRepository extends GenericRepository<ResSnc> {
	
	Collection<ResSnc> findByCircuit(Long circuitId);
}
