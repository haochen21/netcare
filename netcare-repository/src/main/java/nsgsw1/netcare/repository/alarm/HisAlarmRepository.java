package nsgsw1.netcare.repository.alarm;

import nsgsw1.netcare.model.alarm.HisAlarm;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HisAlarmRepository extends
		MongoRepository<HisAlarm, ObjectId>, HisAlarmRepositoryCustom {

}
