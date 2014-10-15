package nsgsw1.netcare.repository.alarm.impl;

import java.util.Collection;
import java.util.Date;

import nsgsw1.netcare.model.alarm.CurrAlarm;
import nsgsw1.netcare.repository.alarm.CurrAlarmRepositoryCustom;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CurrAlarmRepositoryImpl implements CurrAlarmRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Collection<CurrAlarm> findAlarmsByEmsAndUpdateTime(String emsName,
			Date updateTime) {
		Query query = new Query(Criteria.where("me.ems.name").is(emsName)
				.and("updateTime").lt(updateTime));
		return mongoTemplate.find(query, CurrAlarm.class);
	}

	@Override
	public Collection<CurrAlarm> findAlarmsByUpdateTime(Date updateTime) {
		Query query = new Query(Criteria.where("updateTime").lt(updateTime));
		return mongoTemplate.find(query, CurrAlarm.class);
	}

	@Override
	public Collection<CurrAlarm> findByIds(Collection<ObjectId> ids) {
		Query query = new Query(Criteria.where("id").in(ids));
		return mongoTemplate.find(query, CurrAlarm.class);
	}

	@Override
	public long bizAlarmCountByCircuit(ObjectId circuitId) {
		Query query = new Query(Criteria.where("sncCircuitIds").is(circuitId));
		return mongoTemplate.count(query, CurrAlarm.class);
	}
}
