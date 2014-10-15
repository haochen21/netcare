package nsgsw1.netcare.service.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.circuit.Channel;
import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.circuit.CircuitTextRoute;
import nsgsw1.netcare.model.circuit.ExclusionDay;
import nsgsw1.netcare.model.circuit.QueryCircuitModel;
import nsgsw1.netcare.model.circuit.Snc;
import nsgsw1.netcare.repository.circuit.CircuitRepository;
import nsgsw1.netcare.repository.circuit.CircuitTextRouteRepository;
import nsgsw1.netcare.repository.circuit.ExclusionDayRepository;
import nsgsw1.netcare.repository.circuit.SncRepository;
import nsgsw1.netcare.service.CircuitService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CircuitServiceImpl implements CircuitService {

	@Autowired
	CircuitRepository circuitRepository;

	@Autowired
	CircuitTextRouteRepository circuitTextRouteRepository;

	@Autowired
	SncRepository sncRepository;

	@Autowired
	ExclusionDayRepository exclusionDayRepository;

	@Override
	public Circuit findCircuitByNo(String no) {
		return circuitRepository.findByNo(no);
	}

	@Override
	public Circuit saveCircuit(Circuit circuit) {
		return circuitRepository.save(circuit);
	}

	@Override
	public List<Circuit> findCircuitByIds(Collection<ObjectId> ids) {
		return circuitRepository.findByIds(ids);
	}

	@Override
	public QueryCircuitModel findSncModelByDn(AlarmObjectType objectType,
			String dn) {
		QueryCircuitModel model = new QueryCircuitModel();
		List<Snc> querySncs = new ArrayList<>();
		if (objectType == AlarmObjectType.NE) {
			querySncs = sncRepository.findByMeDn(dn);
		} else if (objectType == AlarmObjectType.SHELF) {
			querySncs = sncRepository.findByShelfDn(dn);
		} else if (objectType == AlarmObjectType.SLOT) {
			querySncs = sncRepository.findBySlotDn(dn);
		} else if (objectType == AlarmObjectType.CIRCUITPACK) {
			querySncs = sncRepository.findByCardDn(dn);
		} else if (objectType == AlarmObjectType.PTP) {
			querySncs = sncRepository.findByPortDn(dn);
		} else if (objectType == AlarmObjectType.CTP) {
			querySncs = sncRepository.findByCtpDn(dn);
		}
		if (querySncs.size() > 0) {
			// 获取电路
			Collection<ObjectId> circuitIds = new HashSet<>();
			for (Snc snc : querySncs) {
				circuitIds.add(snc.getCircuitId());
			}
			List<Circuit> queryCircuits = circuitRepository.findByIdsAndStatus(
					circuitIds, true);
			model.setCircuits(queryCircuits);

			// 获取设备的信息
			Snc snc = querySncs.get(0);
			if (objectType == AlarmObjectType.NE) {
				if (snc.getaCtp().getMe().getDn().equals(dn)) {
					model.setMe(snc.getaCtp().getMe());
				} else if (snc.getzCtp().getMe().getDn().equals(dn)) {
					model.setMe(snc.getzCtp().getMe());
				}
			} else if (objectType == AlarmObjectType.SHELF) {
				if (snc.getaCtp().getShelf().getDn().equals(dn)) {
					model.setMe(snc.getaCtp().getMe());
					model.setShelf(snc.getaCtp().getShelf());
				} else if (snc.getzCtp().getShelf().getDn().equals(dn)) {
					model.setMe(snc.getzCtp().getMe());
					model.setShelf(snc.getzCtp().getShelf());
				}
			} else if (objectType == AlarmObjectType.SLOT) {
				if (snc.getaCtp().getSlot().getDn().equals(dn)) {
					model.setMe(snc.getaCtp().getMe());
					model.setShelf(snc.getaCtp().getShelf());
					model.setSlot(snc.getaCtp().getSlot());
				} else if (snc.getzCtp().getSlot().getDn().equals(dn)) {
					model.setMe(snc.getzCtp().getMe());
					model.setShelf(snc.getzCtp().getShelf());
					model.setSlot(snc.getzCtp().getSlot());
				}
			} else if (objectType == AlarmObjectType.CIRCUITPACK) {
				if (snc.getaCtp().getCard().getDn().equals(dn)) {
					model.setMe(snc.getaCtp().getMe());
					model.setShelf(snc.getaCtp().getShelf());
					model.setSlot(snc.getaCtp().getSlot());
					model.setCard(snc.getaCtp().getCard());
				} else if (snc.getzCtp().getCard().getDn().equals(dn)) {
					model.setMe(snc.getzCtp().getMe());
					model.setShelf(snc.getzCtp().getShelf());
					model.setSlot(snc.getzCtp().getSlot());
					model.setCard(snc.getzCtp().getCard());
				}
			} else if (objectType == AlarmObjectType.PTP) {
				if (snc.getaCtp().getPort().getDn().equals(dn)) {
					model.setMe(snc.getaCtp().getMe());
					model.setShelf(snc.getaCtp().getShelf());
					model.setSlot(snc.getaCtp().getSlot());
					model.setCard(snc.getaCtp().getCard());
					model.setPort(snc.getaCtp().getPort());
				} else if (snc.getzCtp().getPort().getDn().equals(dn)) {
					model.setMe(snc.getzCtp().getMe());
					model.setShelf(snc.getzCtp().getShelf());
					model.setSlot(snc.getzCtp().getSlot());
					model.setCard(snc.getzCtp().getCard());
					model.setPort(snc.getzCtp().getPort());
				}
			} else if (objectType == AlarmObjectType.CTP) {
				if (snc.getaCtp().getDn().equals(dn)) {
					model.setMe(snc.getaCtp().getMe());
					model.setShelf(snc.getaCtp().getShelf());
					model.setSlot(snc.getaCtp().getSlot());
					model.setCard(snc.getaCtp().getCard());
					model.setPort(snc.getaCtp().getPort());
					model.setCtp(snc.getaCtp());
				} else if (snc.getzCtp().getDn().equals(dn)) {
					model.setMe(snc.getzCtp().getMe());
					model.setShelf(snc.getzCtp().getShelf());
					model.setSlot(snc.getzCtp().getSlot());
					model.setCard(snc.getzCtp().getCard());
					model.setPort(snc.getzCtp().getPort());
					model.setCtp(snc.getzCtp());
				}
			}
		}
		return model;
	}

	@Override
	public QueryCircuitModel findChannelModelByDn(AlarmObjectType objectType,
			String dn) {
		QueryCircuitModel model = new QueryCircuitModel();
		List<Snc> querySncs = new ArrayList<>();
		if (objectType == AlarmObjectType.NE) {
			querySncs = sncRepository.findByChannelMeDn(dn);
		} else if (objectType == AlarmObjectType.SHELF) {
			querySncs = sncRepository.findByChannelShelfDn(dn);
		} else if (objectType == AlarmObjectType.SLOT) {
			querySncs = sncRepository.findByChannelSlotDn(dn);
		} else if (objectType == AlarmObjectType.CIRCUITPACK) {
			querySncs = sncRepository.findByChannelCardDn(dn);
		} else if (objectType == AlarmObjectType.PTP) {
			querySncs = sncRepository.findByChannelPortDn(dn);
		} else if (objectType == AlarmObjectType.CTP) {
			querySncs = sncRepository.findByChannelCtpDn(dn);
		}
		if (querySncs.size() > 0) {
			// 获取电路
			Collection<ObjectId> circuitIds = new HashSet<>();
			for (Snc snc : querySncs) {
				circuitIds.add(snc.getCircuitId());
			}
			List<Circuit> queryCircuits = circuitRepository.findByIdsAndStatus(
					circuitIds, true);
			model.setCircuits(queryCircuits);

			// 获取设备的信息
			Snc snc = querySncs.get(0);
			for (Channel channel : snc.getChannels()) {
				if (objectType == AlarmObjectType.NE) {
					if (channel.getChannelACtp().getMe().getDn().equals(dn)) {
						model.setMe(channel.getChannelACtp().getMe());
						break;
					} else if (channel.getChannelZCtp().getMe().getDn()
							.equals(dn)) {
						model.setMe(channel.getChannelZCtp().getMe());
						break;
					}
				} else if (objectType == AlarmObjectType.SHELF) {
					if (channel.getChannelACtp().getShelf().getDn().equals(dn)) {
						model.setMe(channel.getChannelACtp().getMe());
						model.setShelf(channel.getChannelACtp().getShelf());
						break;
					} else if (channel.getChannelZCtp().getShelf().getDn()
							.equals(dn)) {
						model.setMe(channel.getChannelZCtp().getMe());
						model.setShelf(channel.getChannelZCtp().getShelf());
						break;
					}
				} else if (objectType == AlarmObjectType.SLOT) {
					if (channel.getChannelACtp().getSlot().getDn().equals(dn)) {
						model.setMe(channel.getChannelACtp().getMe());
						model.setShelf(channel.getChannelACtp().getShelf());
						model.setSlot(channel.getChannelACtp().getSlot());
						break;
					} else if (channel.getChannelZCtp().getSlot().getDn()
							.equals(dn)) {
						model.setMe(channel.getChannelZCtp().getMe());
						model.setShelf(channel.getChannelZCtp().getShelf());
						model.setSlot(channel.getChannelZCtp().getSlot());
						break;
					}
				} else if (objectType == AlarmObjectType.CIRCUITPACK) {
					if (channel.getChannelACtp().getCard() != null
							&& channel.getChannelACtp().getCard().getDn()
									.equals(dn)) {
						model.setMe(channel.getChannelACtp().getMe());
						model.setShelf(channel.getChannelACtp().getShelf());
						model.setSlot(channel.getChannelACtp().getSlot());
						model.setCard(channel.getChannelACtp().getCard());
						break;
					} else if (channel.getChannelZCtp().getCard() != null
							&& channel.getChannelZCtp().getCard().getDn()
									.equals(dn)) {
						model.setMe(channel.getChannelZCtp().getMe());
						model.setShelf(channel.getChannelZCtp().getShelf());
						model.setSlot(channel.getChannelZCtp().getSlot());
						model.setCard(channel.getChannelZCtp().getCard());
						break;
					}
				} else if (objectType == AlarmObjectType.PTP) {
					if (channel.getChannelACtp().getPort() != null
							&& channel.getChannelACtp().getPort().getDn()
									.equals(dn)) {
						model.setMe(channel.getChannelACtp().getMe());
						model.setShelf(channel.getChannelACtp().getShelf());
						model.setSlot(channel.getChannelACtp().getSlot());
						model.setCard(channel.getChannelACtp().getCard());
						model.setPort(channel.getChannelACtp().getPort());
						break;
					} else if (channel.getChannelZCtp().getPort() != null
							&& channel.getChannelZCtp().getPort().getDn()
									.equals(dn)) {
						model.setMe(channel.getChannelZCtp().getMe());
						model.setShelf(channel.getChannelZCtp().getShelf());
						model.setSlot(channel.getChannelZCtp().getSlot());
						model.setCard(channel.getChannelZCtp().getCard());
						model.setPort(channel.getChannelZCtp().getPort());
						break;
					}
				} else if (objectType == AlarmObjectType.CTP) {
					if (channel.getChannelACtp() != null
							&& channel.getChannelACtp().getDn() != null
							&& channel.getChannelACtp().getDn().equals(dn)) {
						model.setMe(channel.getChannelACtp().getMe());
						model.setShelf(channel.getChannelACtp().getShelf());
						model.setSlot(channel.getChannelACtp().getSlot());
						model.setCard(channel.getChannelACtp().getCard());
						model.setPort(channel.getChannelACtp().getPort());
						model.setCtp(channel.getChannelACtp());
						break;
					} else if (channel.getChannelZCtp() != null
							&& channel.getChannelZCtp().getDn() != null
							&& channel.getChannelZCtp().getDn().equals(dn)) {
						model.setMe(channel.getChannelZCtp().getMe());
						model.setShelf(channel.getChannelZCtp().getShelf());
						model.setSlot(channel.getChannelZCtp().getSlot());
						model.setCard(channel.getChannelZCtp().getCard());
						model.setPort(channel.getChannelZCtp().getPort());
						model.setCtp(channel.getChannelZCtp());
						break;
					}
				}
			}
		}
		return model;
	}

	@Override
	public CircuitTextRoute findTextRouteByCircuitId(ObjectId circuitId) {
		return circuitTextRouteRepository.findByCircuitId(circuitId);
	}

	@Override
	public CircuitTextRoute saveCircuitTextRoute(
			CircuitTextRoute circuitTextRoute) {
		return circuitTextRouteRepository.save(circuitTextRoute);
	}

	@Override
	public void deleteSncByCircuitId(ObjectId circuitId) {
		sncRepository.removeByCircuitId(circuitId);
	}

	@Override
	public List<Snc> saveSncs(List<Snc> sncs) {
		return sncRepository.save(sncs);
	}

	@Override
	public Collection<ExclusionDay> findAllExclusionDay() {
		return exclusionDayRepository.findAll();
	}
}
