package nsgsw1.netcare.repository.security;

import nsgsw1.netcare.model.security.ResSyncRecord;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ResSyncRecordRepository extends
		MongoRepository<ResSyncRecord, ObjectId> {

	@Query("{ 'resType' : ?0 }")
	ResSyncRecord findByType(ResSyncRecord.Type type);
}
