package nsgsw1.netcare.repository.customer;

import static junit.framework.TestCase.assertEquals;
import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.customer.CustomerGroup;
import nsgsw1.netcare.repository.config.MongoConfiguration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class CustomerGroupRepositoryTest {

	@Autowired
	CustomerGroupRepository customerGroupRepository;
	
	@Autowired
	CustomerRepository customerRepository;


	@Autowired
	MongoOperations mongo;	
	
	@Before
	public void setup() throws Exception {
		mongo.dropCollection("customerGroup");
		mongo.dropCollection("customer");
	}

	@After
	public void teardown() {
		mongo.dropCollection("customerGroup");
		mongo.dropCollection("customer");
	}

	@Test
	public void testSaveCustomerGroup() throws Exception {
		assertEquals(0, mongo.getCollection("customerGroup").count());
		CustomerGroup cusGroup = new CustomerGroup();
		cusGroup.setName("testSave");
		cusGroup = customerGroupRepository.save(cusGroup);
		cusGroup.setName("testSave1");
		cusGroup = customerGroupRepository.save(cusGroup);
		assertEquals(1, mongo.getCollection("customerGroup").count());
	}
	
	@Test
	public void testCustomerGroupFindAndUpdate() throws Exception {
		assertEquals(0, mongo.getCollection("customerGroup").count());
		CustomerGroup cusGroup = new CustomerGroup();
		cusGroup.setName("testFindAndUpdate");
		cusGroup = customerGroupRepository.save(cusGroup);
		cusGroup = customerGroupRepository.findAndInsert(cusGroup.getId());
		assertEquals(Boolean.TRUE,cusGroup.getFault());		
	}
	
	@Test
	public void testSaveCustomer() throws Exception {
		assertEquals(0, mongo.getCollection("customer").count());
		CustomerGroup cusGroup = new CustomerGroup();
		cusGroup.setName("testCustomerGroupInCustomer");
		cusGroup = customerGroupRepository.save(cusGroup);		
		Customer customer = new Customer();
		customer.setName("testSave");
		customer.getCustomerGroupIds().add(cusGroup.getId());		
		customer = customerRepository.save(customer);		
		assertEquals(1, mongo.getCollection("customer").count());
	}
	
}
