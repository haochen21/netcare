package nsgsw1.netcare.repository.alarm;

import nsgsw1.netcare.model.alarm.AlarmKnowledge;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmKnowledgeRepository extends
		MongoRepository<AlarmKnowledge, ObjectId> {

}
