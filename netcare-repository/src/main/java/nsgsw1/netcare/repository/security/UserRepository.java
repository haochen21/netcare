package nsgsw1.netcare.repository.security;

import nsgsw1.netcare.model.security.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId>,UserCustomerRepository {

}
