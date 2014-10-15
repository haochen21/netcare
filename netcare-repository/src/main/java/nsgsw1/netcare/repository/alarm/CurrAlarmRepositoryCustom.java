package nsgsw1.netcare.repository.alarm;

import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.model.alarm.CurrAlarm;

import org.bson.types.ObjectId;

public interface CurrAlarmRepositoryCustom {

	Collection<CurrAlarm> findAlarmsByEmsAndUpdateTime(String emsName,
			Date updatetime);

	Collection<CurrAlarm> findAlarmsByUpdateTime(Date updateTime);

	Collection<CurrAlarm> findByIds(Collection<ObjectId> ids);

	long bizAlarmCountByCircuit(ObjectId circuitId);
}
