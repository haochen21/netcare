package nsgsw1.netcare.shres.service;

import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.shres.model.ResChannel;
import nsgsw1.netcare.shres.model.ResCircuit;
import nsgsw1.netcare.shres.model.ResCircuitTextRoute;
import nsgsw1.netcare.shres.model.ResSnc;
import nsgsw1.netcare.shres.repository.ResChannelRepository;
import nsgsw1.netcare.shres.repository.ResCircuitRepository;
import nsgsw1.netcare.shres.repository.ResCircuitTextRouteRepository;
import nsgsw1.netcare.shres.repository.ResSncRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ResCircuitServiceImpl implements ResCircuitService {

	@Autowired
	private ResCircuitRepository resCircuitRepository;

	@Autowired
	private ResCircuitTextRouteRepository resCircuitTextRouteRepository;

	@Autowired
	private ResSncRepository resSncRepository;

	@Autowired
	private ResChannelRepository resChannelRepository;

	@Override
	public ResCircuit findByNo(String no) {
		return resCircuitRepository.findByNo(no);
	}

	@Override
	public Date findLastUpdateTime() {
		return resCircuitRepository.findLastUpdateTime();
	}

	@Override
	public Collection<String> findCircuitNoByLastUpdateTime(Date lastUpdateTime) {
		return resCircuitRepository
				.findCircuitNoByLastUpdateTime(lastUpdateTime);
	}

	@Override
	public ResCircuitTextRoute findByCircuitId(Long circuitId) {
		return resCircuitTextRouteRepository.findByCircuitId(circuitId);
	}

	@Override
	public Collection<ResSnc> findByCircuit(Long circuitId) {
		return resSncRepository.findByCircuit(circuitId);
	}

	@Override
	public Collection<ResChannel> findBySncId(Long sncId) {
		return resChannelRepository.findBySncId(sncId);
	}

}
