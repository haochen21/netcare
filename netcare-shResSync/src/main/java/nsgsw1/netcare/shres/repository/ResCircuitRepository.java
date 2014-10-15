package nsgsw1.netcare.shres.repository;

import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.shres.model.ResCircuit;

public interface ResCircuitRepository extends GenericRepository<ResCircuit> {

	ResCircuit findByNo(String no);
	
	Date findLastUpdateTime();
	
	Collection<String> findCircuitNoByLastUpdateTime(Date lastUpdateTime);
}
