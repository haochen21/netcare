package nsgsw1.netcare.shres.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import nsgsw1.netcare.model.circuit.Channel;
import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.circuit.CircuitTextRoute;
import nsgsw1.netcare.model.circuit.Snc;
import nsgsw1.netcare.model.circuit.constant.CircuitBizStatus;
import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.res.Ctp;
import nsgsw1.netcare.model.res.constant.SncType;
import nsgsw1.netcare.service.CircuitService;
import nsgsw1.netcare.service.CustomerService;
import nsgsw1.netcare.shres.model.ResChannel;
import nsgsw1.netcare.shres.model.ResCircuit;
import nsgsw1.netcare.shres.model.ResCircuitTextRoute;
import nsgsw1.netcare.shres.model.ResSnc;
import nsgsw1.netcare.shres.service.ResCircuitService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SyncSingleCircuit implements Runnable {

	private String circuitNo;

	@Autowired
	private ResCircuitService resCircuitService;

	@Autowired
	private CircuitService circuitService;

	@Autowired
	private CustomerService customerService;

	private final static Logger logger = LoggerFactory
			.getLogger(SyncSingleCircuit.class);

	public SyncSingleCircuit() {

	}

	public void setCircuitNo(String circuitNo) {
		this.circuitNo = circuitNo;
	}

	@Override
	public void run() {
		logger.info("start sync circuit,no is:" + circuitNo);
		ResCircuit resCircuit = resCircuitService.findByNo(circuitNo);
		try {
			Circuit circuit = syncCircuit(resCircuit);
			syncCircuitTextRoute(resCircuit.getId(), circuit);
			syncSnc(resCircuit.getId(), circuit);
			logger.info("sync circuit end,no is:" + circuitNo);
		} catch (Exception ex) {
			logger.info("sync circuit fail,no is:" + circuitNo, ex);
		}

	}

	private Circuit syncCircuit(ResCircuit resCircuit) throws Exception {
		Circuit circuit = circuitService.findCircuitByNo(resCircuit.getNo());
		if (circuit == null) {
			circuit = new Circuit();
		}
		circuit.setNo(resCircuit.getNo());
		circuit.setGroupNo(resCircuit.getGroupNo());
		circuit.setStatus(resCircuit.getStatus());
		circuit.setRate(resCircuit.getRate());
		circuit.setBizState(resCircuit.getBizState());
		circuit.setBizProperty(resCircuit.getBizProperty());
		circuit.setBizStatus(CircuitBizStatus.getFieldTypeByOrdinal(resCircuit
				.getBizStatus().ordinal()));
		circuit.setUpdateTime(new Date());
		circuit.setResSyncResult(resCircuit.getResSyncResult());
		circuit.setResSyncLog(resCircuit.getResSyncLog());

		if (resCircuit.getaCustomer() != null) {
			Customer customer = customerService.findCustomerByResId(resCircuit
					.getaCustomer().getResId());
			if (customer != null) {
				circuit.setaCustomerName(customer.getName());
				circuit.setaCustomerId(customer.getId());
				circuit.getUserIds().addAll(customer.getUserIds());
			}
		}

		if (resCircuit.getzCustomer() != null) {
			Customer customer = customerService.findCustomerByResId(resCircuit
					.getzCustomer().getResId());
			if (customer != null) {
				circuit.setzCustomerName(customer.getName());
				circuit.setzCustomerId(customer.getId());
				circuit.getUserIds().addAll(customer.getUserIds());
			}
		}

		if (resCircuit.getResAMe() != null) {
			circuit.setaMe(ResClassConvert.INSTANCE.convertMe(resCircuit
					.getResAMe()));
		}

		if (resCircuit.getResAPort() != null) {
			circuit.setaPort(ResClassConvert.INSTANCE.convertPort(resCircuit
					.getResAPort()));
		}

		if (resCircuit.getResACtp() != null) {
			circuit.setaCtp(ResClassConvert.INSTANCE.convertCtp(resCircuit
					.getResACtp()));
		}

		if (resCircuit.getResZMe() != null) {
			circuit.setzMe(ResClassConvert.INSTANCE.convertMe(resCircuit
					.getResZMe()));
		}

		if (resCircuit.getResZPort() != null) {
			circuit.setzPort(ResClassConvert.INSTANCE.convertPort(resCircuit
					.getResZPort()));
		}

		if (resCircuit.getResZCtp() != null) {
			circuit.setzCtp(ResClassConvert.INSTANCE.convertCtp(resCircuit
					.getResZCtp()));
		}

		circuit = circuitService.saveCircuit(circuit);
		return circuit;
	}

	private void syncCircuitTextRoute(Long resCircuitId, Circuit circuit)
			throws Exception {
		ResCircuitTextRoute resCircuitTestRoute = resCircuitService
				.findByCircuitId(resCircuitId);
		if (resCircuitTestRoute != null) {
			CircuitTextRoute circuitTextRoute = circuitService
					.findTextRouteByCircuitId(circuit.getId());
			if (circuitTextRoute == null) {
				circuitTextRoute = new CircuitTextRoute();
			}
			circuitTextRoute.setNo(circuit.getNo());
			circuitTextRoute.setCircuitId(circuit.getId());
			circuitTextRoute.setUpdateTime(new Date());
			circuitTextRoute.setTextRoute(resCircuitTestRoute.getTextRoute());
			circuitService.saveCircuitTextRoute(circuitTextRoute);
			logger.info(resCircuitTestRoute.toString());
		}
	}

	private void syncSnc(Long resCircuitId, Circuit circuit) throws Exception {
		// É¾³ýµçÂ·µÄSNC
		circuitService.deleteSncByCircuitId(circuit.getId());
		List<Snc> sncs = new ArrayList<Snc>();
		Collection<ResSnc> resSncs = resCircuitService
				.findByCircuit(resCircuitId);
		for (ResSnc resSnc : resSncs) {
			Snc snc = new Snc();
			snc.setCircuitId(circuit.getId());
			snc.setType(SncType.getSncTypeByOrdinal(resSnc.getType().ordinal()));
			snc.setUpdateTime(new Date());

			Ctp aCtp = null;
			if (resSnc.getResACtp() != null) {
				aCtp = ResClassConvert.INSTANCE.convertCtp(resSnc.getResACtp());
			} else {
				aCtp = new Ctp();
			}
			aCtp.setMe(ResClassConvert.INSTANCE.convertMe(resSnc.getResAMe()));
			aCtp.setPort(ResClassConvert.INSTANCE.convertPort(resSnc
					.getResAPort()));
			if (resSnc.getResAPort().getShelf() != null) {
				aCtp.setShelf(ResClassConvert.INSTANCE.convertShelf(resSnc
						.getResAPort().getShelf()));
			}
			if (resSnc.getResAPort().getSlot() != null) {
				aCtp.setSlot(ResClassConvert.INSTANCE.convertSlot(resSnc
						.getResAPort().getSlot()));
			}
			if (resSnc.getResAPort().getCard() != null) {
				aCtp.setCard(ResClassConvert.INSTANCE.convertCard(resSnc
						.getResAPort().getCard()));
			}
			snc.setaCtp(aCtp);

			Ctp zCtp = null;
			if (resSnc.getResZCtp() != null) {
				zCtp = ResClassConvert.INSTANCE.convertCtp(resSnc.getResZCtp());
			} else {
				zCtp = new Ctp();
			}
			zCtp.setMe(ResClassConvert.INSTANCE.convertMe(resSnc.getResZMe()));
			zCtp.setPort(ResClassConvert.INSTANCE.convertPort(resSnc
					.getResZPort()));
			if (resSnc.getResZPort().getShelf() != null) {
				zCtp.setShelf(ResClassConvert.INSTANCE.convertShelf(resSnc
						.getResZPort().getShelf()));
			}
			if (resSnc.getResZPort().getSlot() != null) {
				zCtp.setSlot(ResClassConvert.INSTANCE.convertSlot(resSnc
						.getResZPort().getSlot()));
			}
			if (resSnc.getResZPort().getCard() != null) {
				zCtp.setCard(ResClassConvert.INSTANCE.convertCard(resSnc
						.getResZPort().getCard()));
			}
			snc.setzCtp(zCtp);

			if (resSnc.getResRegion() != null) {
				snc.setSncRegion(ResClassConvert.INSTANCE.convertRegion(resSnc
						.getResRegion()));
			}

			Collection<ResChannel> resChannels = resCircuitService
					.findBySncId(resSnc.getId());
			for (ResChannel resChannel : resChannels) {
				Channel channel = new Channel();
				channel.setPrimary(resChannel.getPrimary());
				channel.setProtection(resChannel.getProtection());
				channel.setProtectType(resChannel.getProtectType());
				channel.setSwitchType(resChannel.getSwitchType());
				channel.setStatus(resChannel.getStatus());
				channel.setVirtual(resChannel.getVirtual());
				Ctp channelACtp = null;
				if (resChannel.getResACtp() != null)
					channelACtp = ResClassConvert.INSTANCE
							.convertCtp(resChannel.getResACtp());
				else
					channelACtp = new Ctp();
				channelACtp.setMe(ResClassConvert.INSTANCE.convertMe(resChannel
						.getResAMe()));
				if (resChannel.getResAPort() != null) {
					channelACtp.setPort(ResClassConvert.INSTANCE
							.convertPort(resChannel.getResAPort()));
					if (resChannel.getResAPort().getShelf() != null) {
						channelACtp.setShelf(ResClassConvert.INSTANCE
								.convertShelf(resChannel.getResAPort()
										.getShelf()));
					}
					if (resChannel.getResAPort().getCard() != null) {
						channelACtp
								.setCard(ResClassConvert.INSTANCE
										.convertCard(resChannel.getResAPort()
												.getCard()));
					}
				}
				channel.setChannelACtp(channelACtp);

				Ctp channelZCtp = null;
				if (resChannel.getResZCtp() != null)
					channelZCtp = ResClassConvert.INSTANCE
							.convertCtp(resChannel.getResZCtp());
				else
					channelZCtp = new Ctp();
				channelZCtp.setMe(ResClassConvert.INSTANCE.convertMe(resChannel
						.getResZMe()));
				if (resChannel.getResZPort() != null) {
					channelZCtp.setPort(ResClassConvert.INSTANCE
							.convertPort(resChannel.getResZPort()));
					if (resChannel.getResZPort().getShelf() != null) {
						channelZCtp.setShelf(ResClassConvert.INSTANCE
								.convertShelf(resChannel.getResZPort()
										.getShelf()));
					}
					if (resChannel.getResZPort().getCard() != null) {
						channelZCtp
								.setCard(ResClassConvert.INSTANCE
										.convertCard(resChannel.getResZPort()
												.getCard()));
					}
				}
				channel.setChannelZCtp(channelZCtp);
				snc.getChannels().add(channel);
			}
			sncs.add(snc);
		}
		circuitService.saveSncs(sncs);
	}
}
