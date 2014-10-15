package nsgsw1.netcare.repository.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nsgsw1.netcare.model.security.Permission;
import nsgsw1.netcare.model.security.User;
import nsgsw1.netcare.repository.security.UserCustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class UserRepositoryImpl implements UserCustomerRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Collection<User> findByPermission(String name) {
		Collection<User> users = new ArrayList<User>();
		Permission permission = mongoTemplate.findOne(
				new Query(Criteria.where("name").is(name)), Permission.class);		
		List<User> dbUsers = mongoTemplate.find(
				new Query(Criteria.where("roles").in(permission.getRoles())), User.class);
        if(dbUsers!= null && dbUsers.size()>0){
        	users.addAll(dbUsers);
        }
		return users;
	}

}
