package nsgsw1.netcare.repository.security;

import java.util.Collection;

import nsgsw1.netcare.model.security.User;

public interface UserCustomerRepository {

	Collection<User> findByPermission(String name);
}
