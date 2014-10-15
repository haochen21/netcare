package nsgsw1.netcare.alarm.handle;

import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.circuit.QueryCircuitModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AccessEventHandle extends AlarmHandleImpl {

	private final static Logger logger = LoggerFactory
			.getLogger(AccessEventHandle.class);

	public AccessEventHandle() {

	}

	@Override
	public void preProcess() {
		super.preProcess();
		if (preProcessOk == true) {
			if (alarmEvent.getObjectType() == AlarmObjectType.NE) {
				handleMe();
			} else if (alarmEvent.getObjectType() == AlarmObjectType.PTP) {
				handlePort();
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
			logger.debug("AccessEventHandle preProcess.objectType is ME");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.NE, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		me = sncModel.getMe();
	}

	private void handlePort() {
		if (logger.isDebugEnabled())
			logger.debug("AccessEventHandle preProcess.objectType is PORT");
		QueryCircuitModel sncModel = circuitService.findSncModelByDn(
				AlarmObjectType.PTP, alarmEvent.getDeviceUid());
		if (sncModel.getCircuits() != null && sncModel.getCircuits().size() > 0) {
			sncCircuits = sncModel.getCircuits();
		}
		me = sncModel.getMe();
		port = sncModel.getPort();
	}
}
