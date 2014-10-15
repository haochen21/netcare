package nsgsw1.netcare.shres.repository;

import nsgsw1.netcare.shres.model.ResCustomerGroup;

import org.springframework.stereotype.Repository;

@Repository
public class ResCusGroupRepositoryImpl extends
		JpaGenericRepository<ResCustomerGroup> implements ResCusGroupRepository {

	public ResCusGroupRepositoryImpl() {
		super(ResCustomerGroup.class);
	}

}
