package nsgsw1.netcare.shres.service;

import java.util.Collection;

import nsgsw1.netcare.shres.model.ResAlarmKnowledge;
import nsgsw1.netcare.shres.repository.ResAlarmKnowledgeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ResAlarmServiceImpl implements ResAlarmService {

	@Autowired
	private ResAlarmKnowledgeRepository resAlarmKnowledgeRepository;

	@Override
	public Collection<ResAlarmKnowledge> findAllResAlarmKnowledge() {
		return resAlarmKnowledgeRepository.findAll();
	}

}
