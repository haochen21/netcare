package nsgsw1.netcare.repository.alarm;

import nsgsw1.netcare.model.alarm.CurrAlarm;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CurrAlarmRepository extends
		MongoRepository<CurrAlarm, ObjectId>, CurrAlarmRepositoryCustom {

	@Query("{ 'uid' : ?0 }")
	CurrAlarm findByUid(String uid);
}
