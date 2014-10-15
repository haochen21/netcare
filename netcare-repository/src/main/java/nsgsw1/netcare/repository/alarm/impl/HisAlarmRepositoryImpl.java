package nsgsw1.netcare.repository.alarm.impl;

import java.util.Collection;

import nsgsw1.netcare.model.alarm.HisAlarm;
import nsgsw1.netcare.repository.alarm.HisAlarmRepositoryCustom;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class HisAlarmRepositoryImpl implements HisAlarmRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Collection<HisAlarm> findByIds(Collection<ObjectId> ids) {
		Query query = new Query(Criteria.where("id").in(ids));
		return mongoTemplate.find(query, HisAlarm.class);
	}

}
