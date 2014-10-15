package nsgsw1.netcare.repository.alarm;

import nsgsw1.netcare.model.alarm.AlarmInfo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AlarmInfoRepository extends
		MongoRepository<AlarmInfo, ObjectId> {

	@Query("{ 'uid' : ?0 }")
	AlarmInfo findByUid(String uid);
}
