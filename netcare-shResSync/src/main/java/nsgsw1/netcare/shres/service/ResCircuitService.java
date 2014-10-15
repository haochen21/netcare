package nsgsw1.netcare.shres.service;

import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.shres.model.ResChannel;
import nsgsw1.netcare.shres.model.ResCircuit;
import nsgsw1.netcare.shres.model.ResCircuitTextRoute;
import nsgsw1.netcare.shres.model.ResSnc;

public interface ResCircuitService {

	ResCircuit findByNo(String no);

	Date findLastUpdateTime();

	Collection<String> findCircuitNoByLastUpdateTime(Date lastUpdateTime);

	ResCircuitTextRoute findByCircuitId(Long circuitId);

	Collection<ResSnc> findByCircuit(Long circuitId);

	Collection<ResChannel> findBySncId(Long sncId);
}
