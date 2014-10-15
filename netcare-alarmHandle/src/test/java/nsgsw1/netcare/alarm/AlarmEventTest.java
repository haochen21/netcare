package nsgsw1.netcare.alarm;

import java.util.Date;

import nsgsw1.netcare.alarm.config.HandleConfig;
import nsgsw1.netcare.alarm.jms.AlarmEventReceive;
import nsgsw1.netcare.model.alarm.AlarmEvent;
import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;
import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.alarm.constant.AlarmSeverity;
import nsgsw1.netcare.model.alarm.constant.AlarmType;
import nsgsw1.netcare.repository.config.MongoConfiguration;
import nsgsw1.netcare.service.config.ServiceConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AlarmEventTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MongoConfiguration.class);
		ctx.register(ServiceConfig.class);
		ctx.register(HandleConfig.class);
		ctx.refresh();

		try {
			Thread.sleep(5000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		AlarmEventReceive alarmEventReceive = ctx
				.getBean(AlarmEventReceive.class);

		AlarmEvent alarmEvent = new AlarmEvent();
		alarmEvent.setUid("Huawei/U2000XHP-2~59148588");
		//alarmEvent.setDeviceUid("EMS~Huawei/U2000XHP-3@ManagedElement~3146818@PTP~/rack=1/shelf=1/slot=2/domain=sdh/port=1@CTP~/sts3c_au4-j=1");
		alarmEvent.setDeviceUid("EMS~Huawei/U2000XHP-2@ManagedElement~3148733@PTP~/rack=1/shelf=1/slot=10/domain=sdh/port=1@CTP~/sts3c_au4-j=3");
		//alarmEvent.setName("T_ALOS");
		alarmEvent.setName("HP_TIM");
		alarmEvent.setEmsName("Huawei/U2000XHP-2");
		alarmEvent.setCategory(AlarmCategory.SDH);
		alarmEvent.setObjectType(AlarmObjectType.CTP);
		alarmEvent.setType(AlarmType.EQUIPMENTALARM);
		alarmEvent.setStatus(AlarmEventStatus.ACTIVE);
		//alarmEvent.setStatus(AlarmEventStatus.CLEARED);
		alarmEvent.setSeverity(AlarmSeverity.CRITICAL);
		alarmEvent
				.setAdditionalInfo("Direction = NA;Location = NA;MaintenanceStatus = NotInMaintenance;AlarmSerialNo = 170;AlarmReason = 追踪识别符失配;ProductName = OptiX OSN 9500;EquipmentName = JH41;AffirmState = TRUE;DetailInfo =告警参数(16进制) 0x06 0x00 0x01 0xff 0xff;HandlingSuggestion =\\(1)对端应发J0字节与本端应收J0字节不一致；\\(2)业务连接错误。;LocationInfo = 5-JH41-6(SDH-6)-RS:1;");
		alarmEvent.setEmsCreateTime(new Date());
		alarmEvent.setMeCreateTime(new Date());
		alarmEvent.setVendorName("Huawei");
		alarmEvent.setSyncAlarm(false);

		alarmEventReceive.addToAlarmEventQueue(alarmEvent);
	}

}
