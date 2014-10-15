package nsgsw1.netcare.shres.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import nsgsw1.netcare.shres.model.ResSnc;

@Repository
public class ResSncRepositoryImpl extends JpaGenericRepository<ResSnc>
		implements ResSncRepository {

	public ResSncRepositoryImpl() {
		super(ResSnc.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ResSnc> findByCircuit(Long circuitId) {
		Collection<ResSnc> sncs = new ArrayList<ResSnc>();
		Query query = em.createNamedQuery("ResSnc.findByCircuitId");
		query.setParameter("circuitId", circuitId);
		List<ResSnc> dbSncs = query.getResultList();
		if (dbSncs != null && dbSncs.size() > 0) {
			sncs.addAll(dbSncs);
		}
		return sncs;
	}

}
