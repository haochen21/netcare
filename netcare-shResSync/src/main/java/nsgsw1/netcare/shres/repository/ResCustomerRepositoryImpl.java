package nsgsw1.netcare.shres.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import nsgsw1.netcare.shres.model.ResCustomer;

import org.springframework.stereotype.Repository;

@Repository
public class ResCustomerRepositoryImpl extends
		JpaGenericRepository<ResCustomer> implements ResCustomerRepository {

	public ResCustomerRepositoryImpl() {
		super(ResCustomer.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ResCustomer> findAllCustomerGroupRef() {
		Collection<ResCustomer> shCustomers = new ArrayList<ResCustomer>();
		String sql = "SELECT DISTINCT resCus from ResCustomer resCus JOIN FETCH resCus.resCustomerGroups";
		Query query = em.createQuery(sql, ResCustomer.class);
		List<ResCustomer> values = query.getResultList();
		if (values != null && values.size() > 0) {
			shCustomers.addAll(values);
		}
		return shCustomers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ResCustomer> findAllCustomerGroupRef(Date lastSyncTime) {
		Collection<ResCustomer> shCustomers = new ArrayList<ResCustomer>();
		String sql = "SELECT DISTINCT resCus from ResCustomer resCus JOIN FETCH resCus.resCustomerGroups where resCus.syncTime > :syncTime";
		Query query = em.createQuery(sql, ResCustomer.class);
		query.setParameter("syncTime", lastSyncTime);
		List<ResCustomer> values = query.getResultList();
		if (values != null && values.size() > 0) {
			shCustomers.addAll(values);
		}
		return shCustomers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResCustomer findCustomerGroupRef(Long id) {
		ResCustomer shCustomer = null;
		String sql = "SELECT DISTINCT resCus from ResCustomer resCus JOIN FETCH resCus.resCustomerGroups  WHERE resCus.id = :id";
		Query query = em.createQuery(sql, ResCustomer.class);
		query.setParameter("id", id);
		List<ResCustomer> values = query.getResultList();
		if (values != null && values.size() > 0) {
			shCustomer = values.get(0);
		}
		return shCustomer;
	}

	@Override
	public Date findLastUpdateTime() {
		Query query = em
				.createQuery("SELECT MAX(rc.syncTime) FROM ResCustomer rc");
		return (Date) query.getSingleResult();
	}

}
