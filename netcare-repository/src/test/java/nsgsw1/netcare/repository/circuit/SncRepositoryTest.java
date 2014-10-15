package nsgsw1.netcare.repository.circuit;

import static junit.framework.TestCase.assertEquals;

import java.util.Collection;

import nsgsw1.netcare.model.circuit.Snc;
import nsgsw1.netcare.model.res.constant.SncType;
import nsgsw1.netcare.repository.config.MongoConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfiguration.class })
public class SncRepositoryTest {

	@Autowired
	SncRepository sncRepository;

	@Autowired
	MongoOperations mongo;

	public void testSave() throws Exception {
		Snc snc1 = new Snc();
		snc1.setType(SncType.DDF);

		Snc snc2 = new Snc();
		snc2.setType(SncType.DDF);

		sncRepository.save(snc1);
		sncRepository.save(snc2);

		assertEquals(2, mongo.getCollection("Snc").count());

	}

	public void testFindSncByMe() throws Exception {
		Collection<Snc> sncs = sncRepository
				.findByMeDn("EMS~Huawei/U2000XHP-3@ManagedElement~3146803");
		assertEquals(2, sncs.size());
	}
	
	@Test
	public void testFindChannelByMe() throws Exception {
		Collection<Snc> sncs = sncRepository
				.findByChannelMeDn("EMS~Huawei/U2000XHP-3@ManagedElement~3146803");
		assertEquals(1, sncs.size());
	}
}
