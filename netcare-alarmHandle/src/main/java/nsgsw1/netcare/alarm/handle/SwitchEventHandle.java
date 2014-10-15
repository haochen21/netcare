package nsgsw1.netcare.alarm.handle;

import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.circuit.QueryCircuitModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SwitchEventHandle extends AlarmHandleImpl {

	private final static Logger logger = LoggerFactory
			.getLogger(SwitchEventHandle.class);

	public SwitchEventHandle() {

	}

	@Override
	public void preProcess() {
		super.preProcess();
		if (preProcessOk == true) {
			if (alarmEvent.getObjectType() == AlarmObjectType.NE) {
				handleMe();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.PTP) {
				handlePort();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.CIRCUITPACK) {
				handleCard();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.SLOT) {
				handleSlot();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.SHELF) {
				handleShelf();
			} else {
				preProcessOk = false;
				return;
			}
			if (sncCircuits.size() > 0 || channelCircuits.size() > 0) {
				preProcessOk = true;
				setCustomers();
				setCustomerGroups();
			} else
				preProcessOk = false;
		}
	}

	private void handleMe() {
		if (logger.isDebugEnabled())
			logger.debug("SwitchEventHandle preProcess.objectType is Me");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.NE, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		me = sncModel.getMe();
	}

	private void handleShelf() {
		if (logger.isDebugEnabled())
			logger.debug("SwitchEventHandle preProcess.objectType is SHELF");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.SHELF, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		me = sncModel.getMe();
		shelf = sncModel.getShelf();
	}

	private void handleSlot() {
		if (logger.isDebugEnabled())
			logger.debug("SwitchEventHandle preProcess.objectType is SLOT");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.SLOT, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		me = sncModel.getMe();
		shelf = sncModel.getShelf();
		slot = sncModel.getSlot();
	}

	private void handleCard() {
		if (logger.isDebugEnabled())
			logger.debug("SwitchEventHandle preProcess.objectType is CARD");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.CIRCUITPACK, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		me = sncModel.getMe();
		shelf = sncModel.getShelf();
		slot = sncModel.getSlot();
		card = sncModel.getCard();
	}

	private void handlePort() {
		if (logger.isDebugEnabled())
			logger.debug("SwitchEventHandle preProcess.objectType is PORT");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.PTP, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		me = sncModel.getMe();
		shelf = sncModel.getShelf();
		slot = sncModel.getSlot();
		card = sncModel.getCard();
		port = sncModel.getPort();
	}
}
