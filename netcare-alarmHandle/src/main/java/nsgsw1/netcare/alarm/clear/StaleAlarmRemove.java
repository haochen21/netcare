package nsgsw1.netcare.alarm.clear;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.alarm.jms.AlarmEventReceive;
import nsgsw1.netcare.model.alarm.AlarmEvent;
import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.model.alarm.constant.AlarmClearType;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;
import nsgsw1.netcare.service.AlarmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StaleAlarmRemove {

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private AlarmEventReceive alarmEventReceive;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final static Logger logger = LoggerFactory
			.getLogger(StaleAlarmRemove.class);

	@Scheduled(fixedDelay = 10800000)
	public void start() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -40);
		Collection<CurrAlarm> currAlarms = alarmService
				.findAlarmsByUpdateTime(calendar.getTime());
		logger.info("stale alarm time is:" + sdf.format(calendar.getTime())
				+ ",size is:" + currAlarms.size());
		currAlarms.stream().forEach(
				currAlarm -> {
					AlarmEvent clearAlarmEvent = new AlarmEvent();
					clearAlarmEvent.setUid(currAlarm.getUid());
					clearAlarmEvent.setName(currAlarm.getName());
					clearAlarmEvent.setCategory(currAlarm.getCategory());
					if (currAlarm.getMe().getEms() != null)
						clearAlarmEvent.setEmsName(currAlarm.getMe().getEms()
								.getNativeEmsName());
					clearAlarmEvent.setStatus(AlarmEventStatus.CLEARED);
					clearAlarmEvent.setMeCreateTime(new Date());
					clearAlarmEvent.setEmsCreateTime(new Date());
					clearAlarmEvent.setSyncAlarm(false);
					clearAlarmEvent.setClearType(AlarmClearType.SYSTEM);
					alarmEventReceive.addToAlarmEventQueue(clearAlarmEvent);
				});
	}
}
