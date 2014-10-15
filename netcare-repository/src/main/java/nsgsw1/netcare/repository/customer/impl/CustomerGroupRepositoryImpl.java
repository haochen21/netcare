package nsgsw1.netcare.repository.customer.impl;

import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.customer.CustomerGroup;
import nsgsw1.netcare.repository.customer.CustomerGroupRepositoryCustom;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomerGroupRepositoryImpl implements
		CustomerGroupRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public CustomerGroup findAndInsert(ObjectId objectId) {
		Query query = new Query();
		Criteria criteria = new Criteria("id").is(objectId);
		query.addCriteria(criteria);
		Update update = new Update();
		update.set("fault", true);
		return mongoTemplate.findAndModify(query, update, FindAndModifyOptions
				.options().returnNew(true), CustomerGroup.class);
	}

	@Override
	public List<CustomerGroup> findByIds(Collection<ObjectId> ids) {
		Query query = new Query(Criteria.where("id").in(ids));
		return mongoTemplate.find(query, CustomerGroup.class);
	}

}
