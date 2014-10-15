package nsgsw1.netcare.shres.repository;

import nsgsw1.netcare.shres.model.ResCircuitTextRoute;

public interface ResCircuitTextRouteRepository extends
		GenericRepository<ResCircuitTextRoute> {

	ResCircuitTextRoute findByCircuitId(Long circuitId);
}
