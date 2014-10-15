package nsgsw1.netcare.repository.alarm;

import java.util.Collection;

import nsgsw1.netcare.model.alarm.HisAlarm;

import org.bson.types.ObjectId;

public interface HisAlarmRepositoryCustom {

	Collection<HisAlarm> findByIds(Collection<ObjectId> ids);
}
