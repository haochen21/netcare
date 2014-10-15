package nsgsw1.netcare.alarm.handle;

import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.circuit.QueryCircuitModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SdhEventHandle extends AlarmHandleImpl {

	private final static Logger logger = LoggerFactory
			.getLogger(SdhEventHandle.class);

	public SdhEventHandle() {

	}

	@Override
	public void preProcess() {
		super.preProcess();
		if (preProcessOk == true) {
			if (alarmEvent.getObjectType() == AlarmObjectType.NE) {
				handleMe();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.SHELF) {
				handleShelf();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.CIRCUITPACK) {
				handleCard();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.PTP) {
				handlePort();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.CTP) {
				handleCtp();
			} else {
				preProcessOk = false;
				return;
			}
		}
		if (sncCircuits.size() > 0 || channelCircuits.size() > 0) {
			preProcessOk = true;
			setCustomers();
			setCustomerGroups();
		} else
			preProcessOk = false;
	}

	private void handleMe() {
		if (logger.isDebugEnabled())
			logger.debug("SdhEventHandle preProcess.objectType is ME");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.NE, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		QueryCircuitModel channelModel = circuitService.findChannelModelByDn(
				AlarmObjectType.NE, alarmEvent.getDeviceUid());
		if (channelModel.getCircuits() != null
				&& channelModel.getCircuits().size() > 0) {
			channelCircuits = channelModel.getCircuits();
		}
		if (sncModel.getMe() != null) {
			me = sncModel.getMe();
		} else if (channelModel.getMe() != null) {
			me = channelModel.getMe();
		}
	}

	private void handleShelf() {
		if (logger.isDebugEnabled())
			logger.debug("SdhEventHandle preProcess.objectType is SHELF");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.SHELF, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		QueryCircuitModel channelModel = circuitService.findChannelModelByDn(
				AlarmObjectType.SHELF, alarmEvent.getDeviceUid());
		if (channelModel.getCircuits() != null
				&& channelModel.getCircuits().size() > 0) {
			channelCircuits = channelModel.getCircuits();
		}
		if (sncModel.getShelf() != null) {
			me = sncModel.getMe();
			shelf = sncModel.getShelf();
		} else if (channelModel.getMe() != null) {
			me = channelModel.getMe();
			shelf = channelModel.getShelf();
		}
	}

	private void handleCard() {
		if (logger.isDebugEnabled())
			logger.debug("SdhEventHandle preProcess.objectType is CARD");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.CIRCUITPACK, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		QueryCircuitModel channelModel = circuitService.findChannelModelByDn(
				AlarmObjectType.CIRCUITPACK, alarmEvent.getDeviceUid());
		if (channelModel.getCircuits() != null
				&& channelModel.getCircuits().size() > 0) {
			channelCircuits = channelModel.getCircuits();
		}
		if (sncModel.getCard() != null) {
			me = sncModel.getMe();
			shelf = sncModel.getShelf();
			card = sncModel.getCard();
		} else if (channelModel.getMe() != null) {
			me = channelModel.getMe();
			shelf = channelModel.getShelf();
			card = channelModel.getCard();
		}
	}

	private void handlePort() {
		if (logger.isDebugEnabled())
			logger.debug("SdhEventHandle preProcess.objectType is PORT");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.PTP, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		QueryCircuitModel channelModel = circuitService.findChannelModelByDn(
				AlarmObjectType.PTP, alarmEvent.getDeviceUid());
		if (channelModel.getCircuits() != null
				&& channelModel.getCircuits().size() > 0) {
			channelCircuits = channelModel.getCircuits();
		}
		if (sncModel.getPort() != null) {
			me = sncModel.getMe();
			shelf = sncModel.getShelf();
			card = sncModel.getCard();
			port = sncModel.getPort();
		} else if (channelModel.getPort() != null) {
			me = channelModel.getMe();
			shelf = channelModel.getShelf();
			card = channelModel.getCard();
			port = channelModel.getPort();
		}
	}

	private void handleCtp() {
		if (logger.isDebugEnabled())
			logger.debug("SdhEventHandle preProcess.objectType is CTP");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.CTP, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		QueryCircuitModel channelModel = circuitService.findChannelModelByDn(
				AlarmObjectType.CTP, alarmEvent.getDeviceUid());
		if (channelModel.getCircuits() != null
				&& channelModel.getCircuits().size() > 0) {
			channelCircuits = channelModel.getCircuits();
		}
		if (sncModel.getCtp() != null) {
			me = sncModel.getMe();
			shelf = sncModel.getShelf();
			card = sncModel.getCard();
			port = sncModel.getPort();
			ctp = sncModel.getCtp();
		} else if (channelModel.getCtp() != null) {
			me = channelModel.getMe();
			shelf = channelModel.getShelf();
			card = channelModel.getCard();
			port = channelModel.getPort();
			ctp = channelModel.getCtp();
		}
	}
}
