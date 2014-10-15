package nsgsw1.netcare.repository.circuit.impl;

import nsgsw1.netcare.model.circuit.Snc;
import nsgsw1.netcare.repository.circuit.SncRepositoryCustom;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class SncRepositoryImpl implements SncRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void removeByCircuitId(ObjectId circuitId) {
		Query query = new Query();
		Criteria criteria = new Criteria("circuitId").is(circuitId);
		query.addCriteria(criteria);
		mongoTemplate.findAllAndRemove(query, Snc.class);
	}
}
