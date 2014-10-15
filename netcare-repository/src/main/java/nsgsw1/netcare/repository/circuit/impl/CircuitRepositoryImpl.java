package nsgsw1.netcare.repository.circuit.impl;

import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.circuit.constant.CircuitBizStatus;
import nsgsw1.netcare.repository.circuit.CircuitRepositoryCustom;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CircuitRepositoryImpl implements CircuitRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void updateExclusionDay(Collection<ObjectId> circuitIds,
			ObjectId exclusionDayId) {
		Query query = new Query(Criteria.where("id").in(circuitIds));
		Update update = new Update()
				.addToSet("exclusionDayIds", exclusionDayId);
		mongoTemplate.updateMulti(query, update, Circuit.class);
	}

	@Override
	public List<Circuit> findByIds(Collection<ObjectId> ids) {
		Query query = new Query(Criteria.where("id").in(ids));
		return mongoTemplate.find(query, Circuit.class);
	}

	@Override
	public List<Circuit> findByIdsAndStatus(Collection<ObjectId> ids,
			boolean status) {
		Query query = new Query(Criteria.where("id").in(ids)
				.andOperator(Criteria.where("status").is(status)));
		return mongoTemplate.find(query, Circuit.class);
	}

	@Override
	public Circuit updateCircuitBizStatus(ObjectId id,
			CircuitBizStatus bizStatus) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("bizStatus", bizStatus);
		return mongoTemplate.findAndModify(query, update, FindAndModifyOptions
				.options().returnNew(true), Circuit.class);
	}

	@Override
	public Circuit updateCircuitFault(ObjectId id, boolean hasFault) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("hasFault", hasFault);
		return mongoTemplate.findAndModify(query, update, FindAndModifyOptions
				.options().returnNew(true), Circuit.class);
	}

}
