package nsgsw1.netcare.shres.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import nsgsw1.netcare.shres.model.ResCircuit;

import org.springframework.stereotype.Repository;

@Repository
public class ResCircuitRepositoryImpl extends JpaGenericRepository<ResCircuit>
		implements ResCircuitRepository {

	public ResCircuitRepositoryImpl() {
		super(ResCircuit.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResCircuit findByNo(String no) {
		Query query = em.createNamedQuery("ResCircuit.findByNo");
		query.setParameter("no", no);
		List<ResCircuit> circuits = query.getResultList();
		if (circuits != null && circuits.size() > 0)
			return circuits.get(0);
		else
			return null;
	}

	@Override
	public Date findLastUpdateTime() {
		Query query = em
				.createQuery("SELECT MAX(rcir.updateTime) FROM ResCircuit rcir");
		return (Date) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> findCircuitNoByLastUpdateTime(Date lastUpdateTime) {
		Collection<String> nos = new ArrayList<String>();
		String sql = "SELECT DISTINCT resCir.no from ResCircuit resCir where resCir.updateTime > :updateTime";
		Query query = em.createQuery(sql);
		query.setParameter("updateTime", lastUpdateTime);
		List<String> values = query.getResultList();
		if (values != null && values.size() > 0) {
			nos.addAll(values);
		}
		return nos;
	}

}
