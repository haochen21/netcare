package nsgsw1.netcare.shres.repository;

import nsgsw1.netcare.shres.config.ShJPAConfig;
import nsgsw1.netcare.shres.model.ResCustomer;
import nsgsw1.netcare.shres.model.ResCustomerGroup;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ShJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ShCustomerRepositoryTest {

	@Autowired
	ResCusGroupRepository resCusGroupRepository;
	
	@Autowired
	ResCustomerRepository resCustomerRepository;

	@Test
	public void testGetOneCustomerGroup() {
		ResCustomerGroup shCusGroup = resCusGroupRepository.findOne(new Long(
				10001));
		Assert.assertNotNull(shCusGroup);
	}
	
	@Test
	public void testGetOneCustomer() {
		ResCustomer shCustomer = resCustomerRepository.findCustomerGroupRef(new Long(
				24342));
		Assert.assertNotNull(shCustomer);
		Assert.assertEquals(shCustomer.getResCustomerGroups().size(),1);
	}
}
