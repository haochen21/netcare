package nsgsw1.netcare.repository.alarm;

import nsgsw1.netcare.model.alarm.HisFault;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HisFaultRepository extends MongoRepository<HisFault, ObjectId> {

}
