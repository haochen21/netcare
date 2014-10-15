package nsgsw1.netcare.shres.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import nsgsw1.netcare.shres.model.ResCircuitTextRoute;

@Repository
public class ResCircuitTextRouteRepositoryImpl extends
		JpaGenericRepository<ResCircuitTextRoute> implements
		ResCircuitTextRouteRepository {

	public ResCircuitTextRouteRepositoryImpl(){
		super(ResCircuitTextRoute.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResCircuitTextRoute findByCircuitId(Long circuitId) {
		Query query = em.createNamedQuery("ResCircuitTextRoute.findByCircuitId");
		query.setParameter("circuitId", circuitId);
		List<ResCircuitTextRoute> circuitTextRoutes = query.getResultList();
		if (circuitTextRoutes != null && circuitTextRoutes.size() > 0) {
			ResCircuitTextRoute circuitTextRoute = circuitTextRoutes.get(0);
			return circuitTextRoute;
		} else
			return null;
	}
}
