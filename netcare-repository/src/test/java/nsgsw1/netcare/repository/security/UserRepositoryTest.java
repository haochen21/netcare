package nsgsw1.netcare.repository.security;

import static junit.framework.TestCase.assertEquals;

import java.util.Collection;

import nsgsw1.netcare.model.security.User;
import nsgsw1.netcare.repository.config.MongoConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testGetUserByPermission() throws Exception{
		Collection<User> users = userRepository.findByPermission("SYNC_CUSTOMER");
		assertEquals(1,users.size());
	}
}
