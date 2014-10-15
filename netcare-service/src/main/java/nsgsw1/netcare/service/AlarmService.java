package nsgsw1.netcare.service;

import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.model.alarm.AlarmKnowledge;
import nsgsw1.netcare.model.alarm.ClearInfo;
import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.model.alarm.Fault;
import nsgsw1.netcare.model.circuit.Circuit;

public interface AlarmService {

	Collection<AlarmKnowledge> findAllAlarmKnowledges();

	AlarmKnowledge saveAlarmKnowledge(AlarmKnowledge alarmKnowledge);

	Collection<CurrAlarm> findAlarmsByEmsAndUpdateTime(String emsName,
			Date updatetime);
	
	Collection<CurrAlarm> findAlarmsByUpdateTime(Date updateTime);

	CurrAlarm findCurrAlarmByUid(String uid);

	CurrAlarm saveCurrAlarm(CurrAlarm currAlarm);

	CurrAlarm clearCurrAlarm(ClearInfo clearInfo, CurrAlarm currAlarm);

	Fault createFault(Circuit circuit, CurrAlarm currAlarm);

	Fault clearFault(Circuit circuit, CurrAlarm currAlarm);
}
