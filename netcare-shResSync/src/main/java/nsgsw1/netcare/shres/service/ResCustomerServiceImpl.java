package nsgsw1.netcare.shres.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nsgsw1.netcare.shres.model.ResCustomer;
import nsgsw1.netcare.shres.model.ResCustomerGroup;
import nsgsw1.netcare.shres.repository.ResCusGroupRepository;
import nsgsw1.netcare.shres.repository.ResCustomerRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ResCustomerServiceImpl implements ResCustomerService {

	@Autowired
	private ResCusGroupRepository resCusGroupRepository;

	@Autowired
	private ResCustomerRepository resCustomerRepository;

	@Override
	public Collection<ResCustomer> findAllCustomerGroupRef() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResCustomerServiceImpl() {

	}

	@Override
	public Collection<ResCustomer> findAllCustomerGroupRef(Date lastSyncTime) {
		return resCustomerRepository.findAllCustomerGroupRef(lastSyncTime);
	}

	@Override
	public ResCustomer findCustomerGroupRef(Long id) {
		return resCustomerRepository.findCustomerGroupRef(id);
	}

	@Override
	public Date findLastUpdateTime() {
		return resCustomerRepository.findLastUpdateTime();
	}

	@Override
	public List<ResCustomerGroup> findAll() {
		return resCusGroupRepository.findAll();
	}

}
