package nsgsw1.netcare.alarm.jms;

import java.util.Date;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import nsgsw1.netcare.alarm.cache.EmsCache;
import nsgsw1.netcare.model.alarm.AlarmEvent;
import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmsSyncResultReceive implements MessageListener {

	private AlarmEventReceive alarmEventReceive;

	private EmsCache emsCache;

	private final static Logger logger = LoggerFactory
			.getLogger(EmsSyncResultReceive.class);

	public AlarmEventReceive getAlarmEventReceive() {
		return alarmEventReceive;
	}

	public void setAlarmEventReceive(AlarmEventReceive alarmEventReceive) {
		this.alarmEventReceive = alarmEventReceive;
	}

	public EmsCache getEmsCache() {
		return emsCache;
	}

	public void setEmsCache(EmsCache emsCache) {
		this.emsCache = emsCache;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				String emsName = mapMessage.getString("emsName");
				String emsNativeName = emsCache.getNativeEmsName(emsName);
				String operateName = mapMessage.getString("operate");
				boolean result = mapMessage.getBoolean("result");
				if (operateName.equals("syncEmsAlarm") && result) {
					AlarmEvent alarmEvent = new AlarmEvent();
					alarmEvent.setName("alarmSyncFinish");
					alarmEvent.setEmsName(emsNativeName);
					alarmEvent.setDeviceName(emsName);
					alarmEvent.setCategory(AlarmCategory.SDH);
					alarmEvent.setStatus(AlarmEventStatus.ACTIVE);
					alarmEvent.setUid(emsName + "~" + alarmEvent.getName()
							+ "/" + new Date().getTime());
					alarmEventReceive.addToSyncAlarmQueue(alarmEvent);
					logger.info("sync ems alarm finish,name is:"
							+ emsNativeName);
				} else if (operateName.equals("startSyncEmsAlarm")) {
					AlarmEvent alarmEvent = new AlarmEvent();
					alarmEvent.setName("alarmSyncStart");
					alarmEvent.setEmsName(emsNativeName);
					alarmEvent.setDeviceName(emsName);
					alarmEvent.setCategory(AlarmCategory.SDH);
					alarmEvent.setStatus(AlarmEventStatus.ACTIVE);
					alarmEvent.setUid(emsName + "~" + alarmEvent.getName()
							+ "/" + new Date().getTime());
					alarmEventReceive.addToSyncAlarmQueue(alarmEvent);
					logger.info("sync ems alarm start,name is:" + emsNativeName);
				}
			} catch (Exception ex) {
				logger.info("parse message error");
			}
		}

	}

}
